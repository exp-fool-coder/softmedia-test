package com.expfool.bookkeeper.configuration;

import com.expfool.bookkeeper.managers.PaymentManager;
import com.expfool.bookkeeper.services.KafkaConsumerListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public PaymentManager paymentManager() {
        return new PaymentManager();
    }

    @Bean
    public KafkaConsumerListeners kafkaConsumerListeners(
        PaymentManager paymentManager
    ) {
        return new KafkaConsumerListeners(
            paymentManager
        );
    }
}
