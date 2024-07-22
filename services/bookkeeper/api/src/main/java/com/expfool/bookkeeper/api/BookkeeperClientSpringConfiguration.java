package com.expfool.bookkeeper.api;

import com.expfool.bookkeeper.api.Services.BookkeeperService;
import com.expfool.bookkeeper.api.proto.BookkeeperServiceGrpc;
import io.grpc.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookkeeperClientSpringConfiguration {

    @Bean
    public BookkeeperService bookkeeperService() {

        ManagedChannelBuilder<?> channelBuilder
                = Grpc.newChannelBuilderForAddress("localhost", 9090, InsecureChannelCredentials.create());

        return new BookkeeperService(
                BookkeeperServiceGrpc.newBlockingStub(channelBuilder.build())
        );
    }
}
