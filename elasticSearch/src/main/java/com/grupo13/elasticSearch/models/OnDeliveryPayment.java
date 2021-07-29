package com.grupo13.elasticSearch.models;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class OnDeliveryPayment extends PaymentMethod {
    @Field(type = FieldType.Float)
    public Float promisedAmount;

    public Float getPromisedAmount() {
        return promisedAmount;
    }

    public void setPromisedAmount(Float promisedAmount) {
        this.promisedAmount = promisedAmount;
    }

    public OnDeliveryPayment() {}

    public OnDeliveryPayment(String name, Float promisedAmount) {
        super(name);
        this.promisedAmount = promisedAmount;
    }
}
