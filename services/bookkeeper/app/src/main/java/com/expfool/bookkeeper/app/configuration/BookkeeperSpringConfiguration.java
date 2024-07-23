package com.expfool.bookkeeper.app.configuration;

import com.expfool.bookkeeper.app.managers.PaymentManager;
import com.expfool.bookkeeper.app.repositories.PaymentRepository;
import com.expfool.bookkeeper.app.services.KafkaConsumerListeners;
import com.expfool.bookkeeper.app.services.OkvedService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
public class BookkeeperSpringConfiguration {

    @Bean
    public PaymentManager paymentManager(
        PaymentRepository paymentRepository,
        OkvedService okvedService,
        ElasticsearchOperations elasticsearchOperations
    ) {
        return new PaymentManager(
            paymentRepository,
            okvedService,
            elasticsearchOperations
        );
    }

    @Bean
    public KafkaConsumerListeners kafkaConsumerListeners(
        PaymentManager paymentManager
    ) {
        return new KafkaConsumerListeners(
            paymentManager
        );
    }

    @Bean
    public OkvedService okvedService(
        RestTemplateBuilder restTemplateBuilder
    ) {
        return new OkvedService(restTemplateBuilder);
    }
}
