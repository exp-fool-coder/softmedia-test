package com.expfool.bookkeeper.api.Entities;

import com.expfool.bookkeeper.api.proto.BookkeeperProto;

public record GetClientStatisticResponse(
        String answer
) {

    public GetClientStatisticResponse(BookkeeperProto.GetClientStatisticResponse protoResponse) {
        this(
            protoResponse.getAnswer()
        );
    }


    public BookkeeperProto.GetClientStatisticResponse toProto() {
        var builder = BookkeeperProto.GetClientStatisticResponse.newBuilder();
        builder.setAnswer(answer);
        return builder.build();
    }
}
