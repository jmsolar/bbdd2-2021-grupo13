package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "products_on_sale")
public class ProductOnSale {
    @Id
    @Field(type = FieldType.Auto)
    private String id;

    //@Field(type = FieldType.Nested, includeInParent = true)
    private Product product;

    //@Field(type = FieldType.Nested, includeInParent = true)
    private Provider provider;

    @Field(type = FieldType.Float, name = "price")
    private Float price;

    @Field(type = FieldType.Date, name = "initialDate")
    private Date initialDate;

    @Field(type = FieldType.Date, name = "finalDate")
    private Date finalDate;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Date getInitialDate() {
        return initialDate;
    }
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getFinalDate() {
        return finalDate;
    }
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    @JsonCreator
    public ProductOnSale() {}

    @JsonCreator
    public ProductOnSale(
            @JsonProperty("product") Product product,
            @JsonProperty("provider") Provider provider,
            @JsonProperty("price") Float price,
            @JsonProperty("initialDate") Date initialDate) {
        this.product = product;
        this.provider = provider;
        this.price = price;
        this.initialDate = initialDate;
    }
}
