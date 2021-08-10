package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Set;

@Document(indexName = "users")
public class User {
    @Id
    @Field(type = FieldType.Auto)
    private String id;

    @Field(type = FieldType.Text, name = "fullname")
    private String fullname;

    @Field(type = FieldType.Text, name = "email")
    private String email;

    @Field(type = FieldType.Text, name = "password")
    private String password;

    @Field(type = FieldType.Date, name = "dayOfBirth")
    private Date dayOfBirth;
/*
    @JsonProperty("purchases")
    private Set<Purchase> purchases;
*/
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDayOfBirth() {
        return dayOfBirth;
    }
    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
/*
    public Set<Purchase> getPurchases() {
        return purchases;
    }
    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }
*/
    @JsonCreator
    public User() {}

    @JsonCreator
    public User (
            @JsonProperty("email") String email,
            @JsonProperty("fullname") String fullname,
            @JsonProperty("password") String password,
            @JsonProperty("dayOfBirth") Date dayOfBirth) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
    }
}
