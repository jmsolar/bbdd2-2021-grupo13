package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Document(indexName = "providers")
public class Provider {
    @Id
    @Field(type = FieldType.Auto)
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Long, name = "cuit")
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<ProductOnSale> getProductsOnSale() {
        return productsOnSale;
    }
    public void setProductsOnSale(Set<ProductOnSale> productsOnSale) {
        this.productsOnSale = productsOnSale;
    }

    @JsonCreator
    public Provider() {}

    @JsonCreator
    public Provider(
            @JsonProperty("name") String name,
            @JsonProperty("cuit") Long cuit) {
        this.name = name;
        this.cuit = cuit;
    }
}
