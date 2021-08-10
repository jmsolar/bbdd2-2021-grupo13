package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "products")
public class Product {
    @Id
    @Field(type = FieldType.Auto)
    public String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Float, name = "weight")
    private Float weight;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("productOnSale")
    private List<ProductOnSale> productOnSale;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getWeight() {
        return weight;
    }
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public List<ProductOnSale> getProductOnSale() {
        return productOnSale;
    }
    public void setProductOnSale(List<ProductOnSale> productOnSale) {
        this.productOnSale = productOnSale;
    }

    @JsonCreator
    public Product() {}

    @JsonCreator
    public Product(
            @JsonProperty("name") String name,
            @JsonProperty("weight") Float weight,
            @JsonProperty("category") Category category) {
        this.name = name;
        this.weight = weight;
        this.category = category;
        this.productOnSale = new ArrayList<ProductOnSale>();
    }
}
