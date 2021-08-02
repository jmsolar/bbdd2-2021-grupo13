package com.grupo13.elasticSearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Set;

@Document(indexName = "bd2")
public class User {
    @Id
    @Field(type = FieldType.Auto)
    private String Id;

    @Field(type = FieldType.Text)
    private String fullname;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Text)
    private String password;

    @Field(type = FieldType.Date)
    private Date dayOfBirth;

    private Set<Purchase> purchases;

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
        return Id;
    }
    public void setId(String id) {
        this.Id = id;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }
    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public User() {}

    public User (String email, String fullname, String password, Date dayOfBirth) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
    }
}
