package com.expfool.bookkeeper.managers;

import com.expfool.bookkeeper.dto.PaymentDto;
import com.expfool.bookkeeper.entities.Payment;
import com.expfool.bookkeeper.repositories.PaymentRepository;

public class PaymentManager {

    private final PaymentRepository paymentRepository;

    public PaymentManager (PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment recordPaymentDetails(PaymentDto paymentMessage) {
        Payment payment = new Payment();

        payment.setClientId(paymentMessage.getClientId());
        payment.setOkvedCategory("temp_category");
        payment.setOkvedCode(payment.getOkvedCode());
        payment.setAmount(paymentMessage.getAmount());
        payment.setSenderAccountNumber(paymentMessage.getSenderAccountNumber());
        payment.setReceiverAccountNumber(paymentMessage.getReceiverAccountNumber());
        payment.setSenderBIC(paymentMessage.getSenderBIC());
        payment.setReceiverBIC(paymentMessage.getReceiverBIC());

        return paymentRepository.save(payment);
    }
}
