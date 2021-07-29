package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.repositories.DeliveryMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryMethodService {
    private DeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    public DeliveryMethodService(DeliveryMethodRepository deliveryMethodRepository) {
        this.deliveryMethodRepository = deliveryMethodRepository;
    }
}
