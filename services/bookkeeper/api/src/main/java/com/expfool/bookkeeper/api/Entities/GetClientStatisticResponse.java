package com.expfool.bookkeeper.api.Entities;

import com.expfool.bookkeeper.api.proto.BookkeeperProto;

import java.util.List;

public record GetClientStatisticResponse(
        List<Payment> payments
) {

    public GetClientStatisticResponse(BookkeeperProto.GetClientStatisticResponse protoResponse) {
        this(
            protoResponse.getPaymentsList().stream().map(Payment::new).toList()
        );
    }


    public BookkeeperProto.GetClientStatisticResponse toProto() {
        var builder = BookkeeperProto.GetClientStatisticResponse.newBuilder();
        builder.addAllPayments(payments.stream().map(Payment::toProto).toList());
        return builder.build();
    }
}
