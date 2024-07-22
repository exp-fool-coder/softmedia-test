package com.expfool.bookkeeper.app.services;

import com.expfool.bookkeeper.api.Entities.GetClientStatisticResponse;
import com.expfool.bookkeeper.api.proto.BookkeeperProto;
import com.expfool.bookkeeper.api.proto.BookkeeperServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookkeeperServiceGrpcImpl extends BookkeeperServiceGrpc.BookkeeperServiceImplBase {

    @Override
    public void getClientStatistic(
            BookkeeperProto.GetClientStatisticRequest request,
            StreamObserver<BookkeeperProto.GetClientStatisticResponse> responseObserver
    ) {
        GetClientStatisticResponse response = new GetClientStatisticResponse("123");
        responseObserver.onNext(response.toProto());
        responseObserver.onCompleted();
    }
}
