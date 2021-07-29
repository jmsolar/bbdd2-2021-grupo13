package com.grupo13.elasticSearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "productsOnSale", createIndex = true)
public class ProductOnSale {
    @Id
    @Field(type = FieldType.Auto)
    private Long Id;

    private Product product;

    private Provider provider;

    @Field(type = FieldType.Float)
    private Float price;

    @Field(type = FieldType.Date)
    private Date initialDate;

    @Field(type = FieldType.Date)
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
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        this.Id = id;
    }
    public Date getFinalDate() {
        return finalDate;
    }
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public ProductOnSale() {

    }

    public ProductOnSale(Product product, Provider provider, Float price, Date initialDate) {
        this.product = product;
        this.provider = provider;
        this.price = price;
        this.initialDate = initialDate;
    }
}
