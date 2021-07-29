package com.grupo13.elasticSearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "deliveryMethods", createIndex = true)
public class DeliveryMethod {
    @Id
    @Field(type = FieldType.Auto)
    private Long Id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Float)
    private Float cost;

    @Field(type = FieldType.Float)
    private Float startWeight;

    @Field(type = FieldType.Float)
    private Float endWeight;

    private Purchase purchase;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(Float startWeight) {
        this.startWeight = startWeight;
    }

    public Float getEndWeight() {
        return endWeight;
    }

    public void setEndWeight(Float endWeight) {
        this.endWeight = endWeight;
    }
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        this.Id = id;
    }
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public DeliveryMethod() {}

    public DeliveryMethod(String name, Float cost, Float startWeight, Float endWeight) {
        this.name = name;
        this.cost = cost;
        this.startWeight = startWeight;
        this.endWeight = endWeight;
    }
}
