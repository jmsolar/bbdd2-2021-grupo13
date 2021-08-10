package com.grupo13.elasticSearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "purchases")
public class Purchase {
    @Id
    @Field(type = FieldType.Auto)
    private String id;

    //@Field(type = FieldType.Nested, includeInParent = true)
    private ProductOnSale productOnSale;

    //@Field(type = FieldType.Nested, includeInParent = true)
    private User client;

    //@Field(type = FieldType.Nested, includeInParent = true)
    private DeliveryMethod deliveryMethod;

    //@Field(type = FieldType.Nested, includeInParent = true)
    private PaymentMethod paymentMethod;

    @Field(type = FieldType.Integer, name = "quantity")
    private Integer quantity;

    @Field(type = FieldType.Text, name = "address")
    private String address;

    @Field(type = FieldType.Float, name = "coordX")
    private Float coordX;

    @Field(type = FieldType.Float, name = "coordY")
    private Float coordY;

    @Field(type = FieldType.Float, name = "amount")
    @JsonProperty("amount")
    private Float amount;

    @Field(type = FieldType.Date, name = "dateOfPurchase")
    private Date dateOfPurchase;

    public ProductOnSale getProductOnSale() {
        return productOnSale;
    }
    public void setProductOnSale(ProductOnSale productOnSale) {
        this.productOnSale = productOnSale;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getClient() {
        return client;
    }
    public void setClient(User client) {
        this.client = client;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }
    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Float getCoordX() {
        return coordX;
    }
    public void setCoordX(Float coordX) {
        this.coordX = coordX;
    }
    public Float getCoordY() {
        return coordY;
    }
    public void setCoordY(Float coordY) {
        this.coordY = coordY;
    }
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }
    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float setAmount() {
        Float priceProduct = this.getProductOnSale().getPrice();
        Float deliveryAmount = this.getDeliveryMethod().getCost();
        Float result = (priceProduct * this.getQuantity()) + deliveryAmount;

        return result;
    }

    public Float getAmount() {
        return amount;
    }

    @JsonCreator
    public Purchase() {}

    @JsonCreator
    public Purchase(
            @JsonProperty("productOnSale")  ProductOnSale productOnSale,
            @JsonProperty("quantity") Integer quantity,
            @JsonProperty("client") User client,
            @JsonProperty("deliveryMethod") DeliveryMethod deliveryMethod,
            @JsonProperty("paymentMethod") PaymentMethod paymentMethod,
            @JsonProperty("address") String address,
            @JsonProperty("coordX") Float coordX,
            @JsonProperty("coordY") Float coordY,
            @JsonProperty("dateOfPurchase") Date dateOfPurchase) {
        this.productOnSale = productOnSale;
        this.quantity = quantity;
        this.client = client;
        this.deliveryMethod = deliveryMethod;
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.coordX = coordX;
        this.coordY = coordY;
        this.dateOfPurchase = dateOfPurchase;
        this.amount = this.setAmount();
    }
}