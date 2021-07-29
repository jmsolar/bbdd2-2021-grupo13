package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.services.OnDeliveryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ondeliverypayment")
public class OnDeliveryPaymentController {
    private OnDeliveryPaymentService onDeliveryPaymentService;

    @Autowired
    public OnDeliveryPaymentController(OnDeliveryPaymentService onDeliveryPaymentService) {
        this.onDeliveryPaymentService = onDeliveryPaymentService;
    }
}
