package com.expfool.bookkeeper.api.Entities;

import com.expfool.bookkeeper.api.proto.BookkeeperProto;

import java.time.Instant;

public record GetClientStatisticRequest (
        String clientId,
        Instant startTime,
        Instant endTime
) {

    public GetClientStatisticRequest(BookkeeperProto.GetClientStatisticRequest protoRequest) {
        this(
            protoRequest.getClientId(),
            InstantUtils.protoTimestampToInstant(protoRequest.getStartDate()),
            InstantUtils.protoTimestampToInstant(protoRequest.getEndDate())
        );
    }


    public BookkeeperProto.GetClientStatisticRequest toProto() {
        var builder = BookkeeperProto.GetClientStatisticRequest.newBuilder();
        builder.setClientId(clientId);
        builder.setStartDate(InstantUtils.instantToProtoTimestamp(startTime));
        builder.setEndDate(InstantUtils.instantToProtoTimestamp(endTime));
        return builder.build();
    }
}
