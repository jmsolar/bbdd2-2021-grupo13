package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.CreditCardPayment;
import com.grupo13.elasticSearch.services.CreditCardPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/creditcardpayment")
public class CreditCardPaymentController {
    private final CreditCardPaymentService creditCardPaymentService;

    @Autowired
    public CreditCardPaymentController(CreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = creditCardPaymentService;
    }

    @GetMapping("/{name}")
    public Optional<CreditCardPayment> findByName(@PathVariable String name) {
        return this.creditCardPaymentService.findByName(name);
    }

    @PostMapping
    public CreditCardPayment create(@RequestBody final CreditCardPayment creditCardPayment) throws ElasticSearchException {
        return this.creditCardPaymentService.create(creditCardPayment.getName(), creditCardPayment.getBrand(), creditCardPayment.getNumber(), creditCardPayment.getExpiry(), creditCardPayment.getCvv(), creditCardPayment.getOwner());
    }
}
