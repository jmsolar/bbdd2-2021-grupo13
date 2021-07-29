package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.services.CreditCardPaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditcardpayment")
public class CreditCardPaymentController {
    private CreditCardPaymentService creditCardPaymentService;

    public CreditCardPaymentController(CreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = creditCardPaymentService;
    }
}
