package com.expfool.bookkeeper.api.dto;

import com.google.protobuf.Timestamp;

import java.time.Instant;

public class InstantUtils {

    public static Timestamp instantToProtoTimestamp(Instant instant) {
        var builder = Timestamp.newBuilder();
        builder.setSeconds(instant.getEpochSecond());
        builder.setNanos(instant.getNano());
        return builder.build();
    }

    public static Instant protoTimestampToInstant(Timestamp timestamp) {
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
    }
}
