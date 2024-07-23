package com.expfool.bookkeeper.app.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


@Setter
@Getter
@Document(indexName = "payment")
public class Payment {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "client_id")
    private String clientId;

    @Field(type = FieldType.Text, name = "okved_category")
    private String okvedCategory;

    @Field(type = FieldType.Text, name = "okved_code")
    private String okvedCode;

    @Field(type = FieldType.Integer, name = "amount")
    private Integer amount;

    @Field(type = FieldType.Text, name = "sender_account_number")
    private String senderAccountNumber;

    @Field(type = FieldType.Text, name = "sender_bic")
    private String senderBIC;

    @Field(type = FieldType.Text, name = "receiver_account_number")
    private String receiverAccountNumber;

    @Field(type = FieldType.Text, name = "receiver_bic")
    private String receiverBIC;

    @Field(type = FieldType.Date, name = "payment_time", format = DateFormat.date_time)
    private Date paymentTime;

    public com.expfool.bookkeeper.api.Entities.Payment toApiPayment() {
        return new com.expfool.bookkeeper.api.Entities.Payment(
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
