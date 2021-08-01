package com.grupo13.elasticSearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "payment_methods", createIndex = true)
public abstract class PaymentMethod {
    @Id
    @Field(type = FieldType.Auto)
    private String Id;

    @Field(type = FieldType.Text)
    private String name;

    private Purchase purchase;

    public Purchase getPurchase() {
        return purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentMethod() {}

    public PaymentMethod(String name) {
        this.name = name;
    }
}
