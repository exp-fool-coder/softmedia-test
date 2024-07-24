package com.expfool.statz.app.dto;

import com.expfool.statz.app.utils.MoneyFormatterUtils;

import java.time.Instant;

public record Payment(
    String clientId,
    String okvedCode,
    String senderAccountNumber,
    String senderBIC,
    String receiverAccountNumber,
    String receiverBIC,
    int amount,
    String formattedAmount,
    Instant paymentTime
) {

    public Payment(com.expfool.bookkeeper.api.dto.Payment payment) {
        this(
            payment.clientId(),
            payment.okvedCode(),
            payment.senderAccountNumber(),
            payment.senderBic(),
            payment.receiverAccountNumber(),
            payment.receiverBic(),
            payment.amount(),
            MoneyFormatterUtils.formatDefaultRubble(payment.amount()),
            payment.paymentTime()
        );
    }

}
