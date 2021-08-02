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
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{id}")
    public Optional<Purchase> findById(@PathVariable Long id) { return this.purchaseService.findById(id); }

    // purchase.get
    @PostMapping
    public Purchase create(@RequestBody final Purchase purchase, ProductOnSale productOnSale, User client, DeliveryMethod deliveryMethod,
                           PaymentMethod paymentMethod) throws ElasticSearchException {
        return this.purchaseService.create(purchase.getId());
    }
}
