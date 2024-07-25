package com.expfool.bookkeeper.api.dto;

import com.expfool.bookkeeper.api.proto.BookkeeperProto;

import java.time.Instant;

public record Payment(
        String clientId,
        int amount,
        String okvedCategory,
        String okvedCode,
        String senderAccountNumber,
        String senderBic,
        String receiverAccountNumber,
        String receiverBic,
        Instant paymentTime
) {

    public Payment(BookkeeperProto.Payment paymentProto) {
        this(
            paymentProto.getClientId(),
            paymentProto.getAmount(),
            paymentProto.getOkvedCategory(),
            paymentProto.getOkvedCode(),
            paymentProto.getSenderAccountNumber(),
            paymentProto.getSenderBic(),
            paymentProto.getReceiverAccountNumber(),
            paymentProto.getReceiverBic(),
            InstantUtils.protoTimestampToInstant(paymentProto.getPaymentTime())
        );
    }

    public BookkeeperProto.Payment toProto() {
        var builder = BookkeeperProto.Payment.newBuilder();
        builder.setClientId(clientId);
        builder.setAmount(amount);
        builder.setOkvedCategory(okvedCategory);
        if (okvedCode != null) {
            builder.setOkvedCode(okvedCode);
        }
        builder.setSenderAccountNumber(senderAccountNumber);
        builder.setSenderBic(senderBic);
        builder.setReceiverAccountNumber(receiverAccountNumber);
        builder.setReceiverBic(receiverBic);
        builder.setPaymentTime(InstantUtils.instantToProtoTimestamp(paymentTime));
        return builder.build();
    }
}
