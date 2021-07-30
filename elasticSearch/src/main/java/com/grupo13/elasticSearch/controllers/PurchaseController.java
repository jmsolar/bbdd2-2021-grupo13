package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.*;
import com.grupo13.elasticSearch.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{id}")
    public Optional<Purchase> findById(@PathVariable Long id) { return this.purchaseService.findById(id); }

    @PostMapping
    public Purchase create(ProductOnSale productOnSale, Integer quantity, User client, DeliveryMethod deliveryMethod,
                           PaymentMethod paymentMethod, String address, Float coordX, Float coordY, Date dateOfPurchase) throws ElasticSearchException {
        return this.purchaseService.create(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
    }
}
