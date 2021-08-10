package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.PaymentMethod;
import com.grupo13.elasticSearch.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/paymentmethod")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/{name}")
    public Optional<PaymentMethod> findByName(@PathVariable String name) {
        return this.paymentMethodService.findByName(name);
    }

    @PostMapping
    public PaymentMethod create(@RequestBody final PaymentMethod paymentMethod) throws ElasticSearchException {
        if (paymentMethod.getPromisedAmount() == null)
            return this.paymentMethodService.create(paymentMethod.getName(), paymentMethod.getPromisedAmount());
        return this.paymentMethodService.create(paymentMethod.getName(), paymentMethod.getBrand(), paymentMethod.getNumber(), paymentMethod.getExpiry(), paymentMethod.getCvv(), paymentMethod.getOwner());
    }
}
