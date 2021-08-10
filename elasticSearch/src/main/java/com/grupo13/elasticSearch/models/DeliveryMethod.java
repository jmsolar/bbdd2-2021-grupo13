package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "delivery_methods")
public class DeliveryMethod {
    @Id
    @Field(type = FieldType.Auto)
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Float, name = "cost")
    private Float cost;

    @Field(type = FieldType.Float, name = "startWeight")
    private Float startWeight;

    @Field(type = FieldType.Float, name = "endWeight")
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @JsonCreator
    public DeliveryMethod() {}

    @JsonCreator
    public DeliveryMethod(
            @JsonProperty("name") String name,
            @JsonProperty("cost") Float cost,
            @JsonProperty("startWeight") Float startWeight,
            @JsonProperty("endWeight") Float endWeight) {
        this.name = name;
        this.cost = cost;
        this.startWeight = startWeight;
        this.endWeight = endWeight;
    }
}
