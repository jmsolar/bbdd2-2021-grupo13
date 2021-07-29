package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.repositories.OnDeliveryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnDeliveryPaymentService {
    private OnDeliveryPaymentRepository onDeliveryPaymentRepository;

    @Autowired
    public OnDeliveryPaymentService(OnDeliveryPaymentRepository onDeliveryPaymentRepository) {
        this.onDeliveryPaymentRepository = onDeliveryPaymentRepository;
    }
}
