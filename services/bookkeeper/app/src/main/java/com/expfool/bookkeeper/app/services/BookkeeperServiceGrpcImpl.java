package com.expfool.bookkeeper.app.services;

import com.expfool.bookkeeper.api.Entities.GetClientStatisticRequest;
import com.expfool.bookkeeper.api.Entities.GetClientStatisticResponse;
import com.expfool.bookkeeper.api.proto.BookkeeperProto;
import com.expfool.bookkeeper.api.proto.BookkeeperServiceGrpc;
import com.expfool.bookkeeper.app.entities.Payment;
import com.expfool.bookkeeper.app.managers.PaymentManager;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class BookkeeperServiceGrpcImpl extends BookkeeperServiceGrpc.BookkeeperServiceImplBase {

    private final PaymentManager paymentManager;

    public BookkeeperServiceGrpcImpl(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    @Override
    public void getClientStatistic(
            BookkeeperProto.GetClientStatisticRequest request,
            StreamObserver<BookkeeperProto.GetClientStatisticResponse> responseObserver
    ) {
        var requestEntity = new GetClientStatisticRequest(request);
        List<Payment> payments = paymentManager.getClientPayments(
            requestEntity.clientId(),
            requestEntity.startTime(),
            requestEntity.endTime()
        );
        GetClientStatisticResponse response = new GetClientStatisticResponse(
                payments.stream().map(Payment::toApiPayment).toList()
        );
        responseObserver.onNext(response.toProto());
        responseObserver.onCompleted();
    }
}
