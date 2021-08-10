package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.*;
import com.grupo13.elasticSearch.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{id}")
    public Optional<Purchase> findById(@PathVariable String id) { return this.purchaseService.findById(id); }

    @PostMapping
    public Purchase create(ProductOnSale productOnSale, Integer quantity, User client, DeliveryMethod deliveryMethod,
                           PaymentMethod paymentMethod, String address, Float coordX, Float coordY, Date dateOfPurchase) throws ElasticSearchException {
        return this.purchaseService.create(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
    }

    @GetMapping("/getAllPurchasesMadeByUser/{username}")
    public List<Purchase> getAllPurchasesMadeByUser(@PathVariable String username) {
        return this.purchaseService.getAllPurchasesMadeByUser(username);
    }

    @GetMapping("/getPurchasesInPeriod/{startDate}/{endDate}")
    public List<Purchase> getPurchasesInPeriod(@PathVariable("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                @PathVariable("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) throws ParseException {
        return this.purchaseService.getPurchasesInPeriod(startDate, endDate);
    }

    @GetMapping("/getUsersSpendingMoreThanInPurchase/{amount}")
    public List<User> getUsersSpendingMoreThanInPurchase(@PathVariable Float amount) {
        return this.purchaseService.getUsersSpendingMoreThanInPurchase(amount);
    }
}
