package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "payments_method")
public class PaymentMethod {
    @Id
    @Field(type = FieldType.Auto)
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Float, name= "promisedAmount")
    public Float promisedAmount;

    @Field(type = FieldType.Text, name = "brand")
    public String brand;

    @Field(type = FieldType.Long, name = "number")
    public Long number;

    @Field(type = FieldType.Date, name = "expiry")
    public Date expiry;

    @Field(type = FieldType.Integer, name = "cvv")
    public Integer cvv;

    @Field(type = FieldType.Text, name = "owner")
    public String owner;

    private Purchase purchase;

    public Purchase getPurchase() {
        return purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPromisedAmount() {
        return promisedAmount;
    }

    public void setPromisedAmount(Float promisedAmount) {
        this.promisedAmount = promisedAmount;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public Long getNumber() {
        return number;
    }
    public void setNumber(Long number) {
        this.number = number;
    }
    public Date getExpiry() {
        return expiry;
    }
    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
    public Integer getCvv() {
        return cvv;
    }
    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @JsonCreator
    public PaymentMethod() {}

    @JsonCreator
    public PaymentMethod(@JsonProperty("name") String name,
                         @JsonProperty("promisedAmount") Float promisedAmount,
                         @JsonProperty("brand") String brand,
                         @JsonProperty("number") Long number,
                         @JsonProperty("expiry") Date expiry,
                         @JsonProperty("cvv") Integer cvv,
                         @JsonProperty("owner") String owner) {
        this.name = name;
        this.promisedAmount = promisedAmount;
        this.brand = brand;
        this.number = number;
        this.expiry = expiry;
        this.cvv = cvv;
        this.owner = owner;
    }
}
