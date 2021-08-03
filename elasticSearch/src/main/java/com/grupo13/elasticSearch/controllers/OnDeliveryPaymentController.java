package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.OnDeliveryPayment;
import com.grupo13.elasticSearch.services.OnDeliveryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ondeliverypayment")
public class OnDeliveryPaymentController {
    private final OnDeliveryPaymentService onDeliveryPaymentService;

    @Autowired
    public OnDeliveryPaymentController(OnDeliveryPaymentService onDeliveryPaymentService) {
        this.onDeliveryPaymentService = onDeliveryPaymentService;
    }

    @GetMapping("/name")
    public Optional<OnDeliveryPayment> findByName(@PathVariable String name) {
        return this.onDeliveryPaymentService.findByName(name);
    }

    @PostMapping
    public OnDeliveryPayment create(@RequestBody final OnDeliveryPayment onDeliveryPayment) throws ElasticSearchException {
        return this.onDeliveryPaymentService.create(onDeliveryPayment.getName(), onDeliveryPayment.getPromisedAmount());
    }
}
