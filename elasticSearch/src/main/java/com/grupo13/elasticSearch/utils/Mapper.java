package com.grupo13.elasticSearch.utils;

import com.grupo13.elasticSearch.models.*;

import java.util.Date;
import java.util.Map;

import static com.grupo13.elasticSearch.utils.TranslateDate.FromLocalDateToDate;

public class Mapper {
    public static Category MapCategory(Map<String, Object> categoryMap) {
        var name = categoryMap.get("name").toString();

        Category categoryMapped = new Category(name);

        if (categoryMap.get("id") != null) {
            categoryMapped.setId(categoryMap.get("id").toString());
        }

        return categoryMapped;
    }

    public static DeliveryMethod MapDeliveryMethod(Map<String, Object> deliveryMethodMap) {
        var name = deliveryMethodMap.get("name").toString();
        var cost = Float.parseFloat(deliveryMethodMap.get("cost").toString());
        var startWeight = Float.parseFloat(deliveryMethodMap.get("startWeight").toString());
        var endWeight = Float.parseFloat(deliveryMethodMap.get("endWeight").toString());

        DeliveryMethod deliveryMethodMapped = new DeliveryMethod(name, cost, startWeight, endWeight);

        if (deliveryMethodMap.get("id") != null) {
            deliveryMethodMapped.setId(deliveryMethodMap.get("id").toString());
        }

        return deliveryMethodMapped;
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

        PaymentMethod paymentMethodMapped = new PaymentMethod(name, promisedAmount, brand, number, expiry, cvv, owner);

        if (paymentMethodMap.get("id") != null) {
            paymentMethodMapped.setId(paymentMethodMap.get("id").toString());
        }

        return paymentMethodMapped;
    }

    public static Product MapProduct(Map<String, Object> productMap) {
        var name = productMap.get("name").toString();
        var weight = Float.parseFloat(productMap.get("weight").toString());
        var category = MapCategory((Map<String, Object>) productMap.get("category"));

        Product productMapped = new Product(name, weight, category);

        if (productMap.get("id") != null) {
            productMapped.setId(productMap.get("id").toString());
        }

        return productMapped;
    }

    public static ProductOnSale MapProductOnSale(Map<String, Object> productOnSaleMap) {
        var product = MapProduct((Map<String, Object>) productOnSaleMap.get("product"));
        var provider = MapProvider((Map<String, Object>) productOnSaleMap.get("provider"));
        var price = Float.parseFloat(productOnSaleMap.get("price").toString());
        var initialDate = FromLocalDateToDate(productOnSaleMap.get("initialDate").toString());

        ProductOnSale posMapped = new ProductOnSale(product, provider, price, initialDate);

        if (productOnSaleMap.get("finalDate") != null) {
            posMapped.setFinalDate(FromLocalDateToDate(productOnSaleMap.get("finalDate").toString()));
        }

        if (productOnSaleMap.get("id") != null) {
            posMapped.setId(productOnSaleMap.get("id").toString());
        }

        return posMapped;
    }

    public static Provider MapProvider(Map<String, Object> providerMap) {
        var name = providerMap.get("name").toString();
        var cuit = Long.parseLong(providerMap.get("cuit").toString());

        Provider providerMapped = new Provider(name, cuit);

        if (providerMap.get("id") != null) {
            providerMapped.setId(providerMap.get("id").toString());
        }

        return providerMapped;
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

        Purchase purchaseMapped = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);

        if (purchaseMap.get("id") != null) {
            purchaseMapped.setId(purchaseMap.get("id").toString());
        }

        return purchaseMapped;
    }

    public static User MapUser(Map<String, Object> userMap) {
        var fullname = userMap.get("fullname").toString();
        var email = userMap.get("email").toString();
        var password = userMap.get("password").toString();
        var dayOfBirth = FromLocalDateToDate(userMap.get("dayOfBirth").toString());

        User userMapped = new User(email, fullname, password, dayOfBirth);

        if (userMap.get("id") != null) {
            userMapped.setId(userMap.get("id").toString());
        }

        return userMapped;
    }
}
