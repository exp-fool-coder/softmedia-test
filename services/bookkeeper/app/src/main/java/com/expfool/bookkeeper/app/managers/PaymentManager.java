package com.expfool.bookkeeper.app.managers;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.expfool.bookkeeper.app.repositories.PaymentRepository;
import com.expfool.bookkeeper.app.services.OkvedService;
import com.expfool.bookkeeper.app.dto.kafka.PaymentDto;
import com.expfool.bookkeeper.app.entities.Payment;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PaymentManager {

    private final PaymentRepository paymentRepository;
    private final OkvedService okvedService;
    private final ElasticsearchOperations elasticsearchOperations;

    public PaymentManager (PaymentRepository paymentRepository, OkvedService okvedService, ElasticsearchOperations elasticsearchOperations) {
        this.paymentRepository = paymentRepository;
        this.okvedService = okvedService;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public Payment recordPaymentDetails(PaymentDto paymentMessage) {
        String category;
        try {
            category = okvedService.getOkvedCategoryByCode(paymentMessage.getOkvedCode());
        } catch (Exception e) {
            //todo: something bad happened about okved request
            category = "";
        }

        Payment payment = new Payment();
        payment.setId(paymentMessage.getId().toString());
        payment.setClientId(paymentMessage.getClientId());
        payment.setOkvedCategory(category);
        payment.setOkvedCode(paymentMessage.getOkvedCode());
        payment.setAmount(paymentMessage.getAmount());
        payment.setSenderAccountNumber(paymentMessage.getSenderAccountNumber());
        payment.setReceiverAccountNumber(paymentMessage.getReceiverAccountNumber());
        payment.setSenderBIC(paymentMessage.getSenderBIC());
        payment.setReceiverBIC(paymentMessage.getReceiverBIC());
        payment.setPaymentTime(Date.from(paymentMessage.getPaymentTime().toInstant(ZoneOffset.UTC)));

        return paymentRepository.save(payment);
    }

    public List<Payment> getClientPayments(String clientId, Instant startTime, Instant endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
        var formattedStartTime = formatter.format(startTime);
        var formattedEndTime = formatter.format(endTime);

        var timeQuery = QueryBuilders.range(q ->
            q.field("payment_time")
            .from(formattedStartTime)
            .to(formattedEndTime).format("yyyy-MM-dd HH:mm:ss")
        );
        var matchQuery = QueryBuilders.match(q -> q.field("client_id").query(clientId));
        var searchQuery = QueryBuilders.bool(q -> q.must(timeQuery).must(matchQuery));
        var qwe = new NativeQueryBuilder().withQuery(searchQuery).build();
        SearchHits<Payment> payments = elasticsearchOperations.search(qwe, Payment.class);

        return payments.stream().map(SearchHit::getContent).toList();
    }
}
