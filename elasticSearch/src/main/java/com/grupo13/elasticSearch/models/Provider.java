package com.grupo13.elasticSearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Document(indexName = "providers", createIndex = true)
public class Provider {
    @Id
    @Field(type = FieldType.Auto)
    private Long Id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Long)
    private Long cuit;

    private Set<ProductOnSale> productsOnSale;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Set<ProductOnSale> getProductsOnSale() {
        return productsOnSale;
    }
    public void setProductsOnSale(Set<ProductOnSale> productsOnSale) {
        this.productsOnSale = productsOnSale;
    }

    public Provider() {}

    public Provider(String name, Long cuit) {
        this.name = name;
        this.cuit = cuit;
    }
}
