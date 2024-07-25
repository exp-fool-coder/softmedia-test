package com.expfool.bookkeeper.app.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Document(indexName = "payment")
public class Payment {

    @Id
    private String id;

    @Setter
    @Field(type = FieldType.Text, name = "client_id")
    private String clientId;

    @Setter
    @Field(type = FieldType.Text, name = "okved_category")
    private String okvedCategory;

    @Setter
    @Field(type = FieldType.Text, name = "okved_code")
    private String okvedCode;

    @Setter
    @Field(type = FieldType.Integer, name = "amount")
    private Integer amount;

    @Setter
    @Field(type = FieldType.Text, name = "sender_account_number")
    private String senderAccountNumber;

    @Setter
    @Field(type = FieldType.Text, name = "sender_bic")
    private String senderBIC;

    @Setter
    @Field(type = FieldType.Text, name = "receiver_account_number")
    private String receiverAccountNumber;

    @Setter
    @Field(type = FieldType.Text, name = "receiver_bic")
    private String receiverBIC;

    @Setter
    @Field(type = FieldType.Date, name = "payment_time", format = DateFormat.date_time)
    private Date paymentTime;

    public com.expfool.bookkeeper.api.dto.Payment toApiPayment() {
        return new com.expfool.bookkeeper.api.dto.Payment(
                clientId,
                amount,
                okvedCategory,
                okvedCode,
                senderAccountNumber,
                senderBIC,
                receiverAccountNumber,
                receiverBIC,
                paymentTime.toInstant()
        );
    }

}
