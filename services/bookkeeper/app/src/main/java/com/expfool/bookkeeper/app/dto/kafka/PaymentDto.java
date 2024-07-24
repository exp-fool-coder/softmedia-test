package com.expfool.bookkeeper.app.dto.kafka;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentDto extends AbstractDto {

    private String clientId;

    private String okvedCode;

    private Integer amount;

    private String senderAccountNumber;

    private String senderBIC;

    private String receiverAccountNumber;

    private String receiverBIC;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    LocalDateTime paymentTime;

}
