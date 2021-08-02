package com.grupo13.elasticSearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.Set;

@Document(indexName = "products", createIndex = true)
public class Product {
    @Id
    @Field(type = FieldType.Auto)
    public String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Float, name = "weight")
    private Float weight;

    private Category category;

    private Set<ProductOnSale> productOnSale;

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

    public Set<ProductOnSale> getProductsOnSale() {
        return productOnSale;
    }
    public void setProductsOnSale(Set<ProductOnSale> productOnSale) {
        this.productOnSale = productOnSale;
    }

    public Product() {}

    public Product(String name, Float weight, Category category) {
        this.name = name;
        this.weight = weight;
        this.category = category;
        this.productOnSale = new HashSet<ProductOnSale>();
    }
}
