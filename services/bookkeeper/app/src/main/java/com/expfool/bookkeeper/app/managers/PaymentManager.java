package com.expfool.bookkeeper.app.managers;

import com.expfool.bookkeeper.app.repositories.PaymentRepository;
import com.expfool.bookkeeper.app.services.OkvedService;
import com.expfool.bookkeeper.app.dto.PaymentDto;
import com.expfool.bookkeeper.app.entities.Payment;

public class PaymentManager {

    private final PaymentRepository paymentRepository;
    private final OkvedService okvedService;

    public PaymentManager (PaymentRepository paymentRepository, OkvedService okvedService) {
        this.paymentRepository = paymentRepository;
        this.okvedService = okvedService;
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
}
