package com.grupo13.elasticSearch.utils;

import com.grupo13.elasticSearch.models.*;

import java.util.Date;
import java.util.Map;

import static com.grupo13.elasticSearch.utils.TranslateDate.FromLocalDateToDate;

public class Mapper {
    public static Category MapCategory(Map<String, Object> categoryMap) {
        var id = categoryMap.get("id").toString();
        var name = categoryMap.get("name").toString();

        return new Category(name);
    }

    public static DeliveryMethod MapDeliveryMethod(Map<String, Object> deliveryMethodMap) {
        var id = deliveryMethodMap.get("id").toString();
        var name = deliveryMethodMap.get("name").toString();
        var cost = Float.parseFloat(deliveryMethodMap.get("cost").toString());
        var startWeight = Float.parseFloat(deliveryMethodMap.get("startWeight").toString());
        var endWeight = Float.parseFloat(deliveryMethodMap.get("endWeight").toString());

        if (deliveryMethodMap.get("purchase") != null) {
            var purchase = deliveryMethodMap.get("purchase").toString();
        }

        return new DeliveryMethod(name, cost, startWeight, endWeight);
    }

    public static PaymentMethod MapPaymentMethod(Map<String, Object> paymentMethodMap) {
        var name = paymentMethodMap.get("name").toString();

        Float promisedAmount = null;
        String brand = null;
        Long number = null;
        Date expiry = null;
        Integer cvv = null;
        String owner = null;

        if (paymentMethodMap.get("promisedAmount") != null) {
            promisedAmount = Float.parseFloat(paymentMethodMap.get("promisedAmount").toString());
        }
        else {
            brand = paymentMethodMap.get("brand").toString();
            number = Long.parseLong(paymentMethodMap.get("number").toString());
            expiry = FromLocalDateToDate(paymentMethodMap.get("expiry").toString());
            cvv = Integer.parseInt(paymentMethodMap.get("cvv").toString());
            owner = paymentMethodMap.get("owner").toString();
        }

        return new PaymentMethod(name, promisedAmount, brand, number, expiry, cvv, owner);
    }

    public static Product MapProduct(Map<String, Object> productMap) {
        var name = productMap.get("name").toString();
        var weight = Float.parseFloat(productMap.get("weight").toString());
        var category = MapCategory((Map<String, Object>) productMap.get("category"));

        return new Product(name, weight, category);
    }

    public static ProductOnSale MapProductOnSale(Map<String, Object> productOnSaleMap) {
        var product = MapProduct((Map<String, Object>) productOnSaleMap.get("product"));
        var provider = MapProvider((Map<String, Object>) productOnSaleMap.get("provider"));
        var price = Float.parseFloat(productOnSaleMap.get("price").toString());
        var initialDate = FromLocalDateToDate(productOnSaleMap.get("initialDate").toString());
        if (productOnSaleMap.get("finalDate") != null) {
            var finalDate = FromLocalDateToDate(productOnSaleMap.get("finalDate").toString());
        }

        return new ProductOnSale(product, provider, price, initialDate);
    }

    public static Provider MapProvider(Map<String, Object> providerMap) {
        var name = providerMap.get("name").toString();
        var cuit = Long.parseLong(providerMap.get("cuit").toString());

        return new Provider(name, cuit);
    }

    public static Purchase MapPurchase(Map<String, Object> purchaseMap) {
        var productOnSale = MapProductOnSale((Map<String, Object>) purchaseMap.get("productOnSale"));
        var quantity = Integer.parseInt(purchaseMap.get("quantity").toString());
        var client = MapUser((Map<String, Object>) purchaseMap.get("client"));
        var deliveryMethod = MapDeliveryMethod((Map<String, Object>) purchaseMap.get("deliveryMethod"));
        var paymentMethod = MapPaymentMethod((Map<String, Object>) purchaseMap.get("paymentMethod"));
        var address = purchaseMap.get("address").toString();
        var coordX = Float.parseFloat(purchaseMap.get("coordX").toString());
        var coordY = Float.parseFloat(purchaseMap.get("coordY").toString());
        var dateOfPurchase = FromLocalDateToDate(purchaseMap.get("dateOfPurchase").toString());

        return new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
    }

    public static User MapUser(Map<String, Object> userMap) {
        var id = userMap.get("id").toString();
        var fullname = userMap.get("fullname").toString();
        var email = userMap.get("email").toString();
        var password = userMap.get("password").toString();
        var dayOfBirth = FromLocalDateToDate(userMap.get("dayOfBirth").toString());

        return new User(email, fullname, password, dayOfBirth);
    }
}
