package com.expfool.bookkeeper.services;

import com.expfool.bookkeeper.dto.PaymentDto;
import com.expfool.bookkeeper.managers.PaymentManager;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerListeners {

    private final PaymentManager paymentManager;

    public KafkaConsumerListeners(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    @KafkaListener(
        id = "some_specific_consumers",
        topics = "paymentTopic",
        containerFactory = "paymentMessageListenerContainerFactory"
    )
    public void paymentMessageHandler(PaymentDto message) {
        paymentManager.recordPaymentDetails(message);
    }

}
