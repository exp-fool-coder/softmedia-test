package com.expfool.bookkeeper.api.Services;

import com.expfool.bookkeeper.api.Entities.GetClientStatisticRequest;
import com.expfool.bookkeeper.api.Entities.GetClientStatisticResponse;
import com.expfool.bookkeeper.api.proto.BookkeeperProto;
import com.expfool.bookkeeper.api.proto.BookkeeperServiceGrpc;
import io.grpc.StatusRuntimeException;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookkeeperService {

    public BookkeeperService(BookkeeperServiceGrpc.BookkeeperServiceBlockingStub grpc) {
        this.grpc = grpc;
    }

    private final BookkeeperServiceGrpc.BookkeeperServiceBlockingStub grpc;

    private static final long OTHER_OPERATIONS_TIMEOUT_MILLISECONDS = 10000L;

    public GetClientStatisticResponse getClientStatistic(GetClientStatisticRequest request) {
        try {
            BookkeeperProto.GetClientStatisticResponse response = grpc
                    .withDeadlineAfter(OTHER_OPERATIONS_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .getClientStatistic(request.toProto());
            return new GetClientStatisticResponse(response);
        } catch (StatusRuntimeException e) {
            //TODO: do something about this exception logging and returning empty object
            return new GetClientStatisticResponse(List.of());
        }
    }

}
