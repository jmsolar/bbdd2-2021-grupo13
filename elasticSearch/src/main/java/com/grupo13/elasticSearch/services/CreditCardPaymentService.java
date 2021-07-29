package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.repositories.CreditCardPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentService {
    private CreditCardPaymentRepository creditCardPaymentRepository;

    @Autowired
    public CreditCardPaymentService(CreditCardPaymentRepository creditCardPaymentRepository) {
        this.creditCardPaymentRepository = creditCardPaymentRepository;
    }
}
