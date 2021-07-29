package com.grupo13.elasticSearch.models;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

public class CreditCardPayment extends PaymentMethod {
    @Field(type = FieldType.Text)
    public String brand;

    @Field(type = FieldType.Long)
    public Long number;

    @Field(type = FieldType.Date)
    public Date expiry;

    @Field(type = FieldType.Integer)
    public Integer cvv;

    @Field(type = FieldType.Text)
    public String owner;

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

    public CreditCardPayment() {}

    public CreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv, String owner) {
        super(name);
        this.brand = brand;
        this.number = number;
        this.expiry = expiry;
        this.cvv = cvv;
        this.owner = owner;
    }
}
