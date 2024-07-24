package com.expfool.bookkeeper.api;

import com.expfool.bookkeeper.api.Services.BookkeeperService;
import com.expfool.bookkeeper.api.proto.BookkeeperServiceGrpc;
import io.grpc.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookkeeperClientSpringConfiguration {

    @Value("${bookkeeper.grpc.host}")
    private String host;

    @Value("${bookkeeper.grpc.port}")
    private int port;

    @Bean
    public BookkeeperService bookkeeperService() {

        ManagedChannelBuilder<?> channelBuilder
                = Grpc.newChannelBuilderForAddress(host, port, InsecureChannelCredentials.create());

        return new BookkeeperService(
                BookkeeperServiceGrpc.newBlockingStub(channelBuilder.build())
        );
    }
}
