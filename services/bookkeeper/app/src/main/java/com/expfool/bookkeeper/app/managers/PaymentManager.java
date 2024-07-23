package com.expfool.bookkeeper.app.managers;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.expfool.bookkeeper.app.repositories.PaymentRepository;
import com.expfool.bookkeeper.app.services.OkvedService;
import com.expfool.bookkeeper.app.dto.PaymentDto;
import com.expfool.bookkeeper.app.entities.Payment;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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
        payment.setClientId(paymentMessage.getClientId());
        payment.setOkvedCategory(category);
        payment.setOkvedCode(payment.getOkvedCode());
        payment.setAmount(paymentMessage.getAmount());
        payment.setSenderAccountNumber(paymentMessage.getSenderAccountNumber());
        payment.setReceiverAccountNumber(paymentMessage.getReceiverAccountNumber());
        payment.setSenderBIC(paymentMessage.getSenderBIC());
        payment.setReceiverBIC(paymentMessage.getReceiverBIC());

        return paymentRepository.save(payment);
    }

    public List<Payment> getClientPayments(String clientId, Instant startTime, Instant endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormat.date_time.getPattern());
        var formattedStartTime = formatter.format(startTime);
        var formattedEndTime = formatter.format(endTime);

        var timeQuery = QueryBuilders.range(q ->
            q.field("payment_time")
            .from(formattedEndTime)
            .to(formattedStartTime)
        );

        var matchQuery = QueryBuilders.match(q -> q.field("client_id").query(clientId));


        var searchQuery = QueryBuilders.bool(q -> q.must(timeQuery).must(matchQuery));

        var qwe = new NativeQueryBuilder().withQuery(searchQuery).build();

        SearchHits<Payment> payments = elasticsearchOperations
                .search(Objects.requireNonNull(qwe.getSpringDataQuery()), Payment.class);

        return payments.stream().map(SearchHit::getContent).toList();
    }
}
