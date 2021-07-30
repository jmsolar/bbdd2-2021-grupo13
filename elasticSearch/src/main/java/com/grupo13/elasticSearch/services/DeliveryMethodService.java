package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.DeliveryMethod;
import com.grupo13.elasticSearch.repositories.DeliveryMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryMethodService {
    private DeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    public DeliveryMethodService(DeliveryMethodRepository deliveryMethodRepository) {
        this.deliveryMethodRepository = deliveryMethodRepository;
    }

    /**
     * @param name nombre del método de delivery a buscar
     * @return
     */
    public Optional<DeliveryMethod> findByName(String name) {
        return this.deliveryMethodRepository.findByName(name);
    }

    /**
     * @param name nombre del método de delivery
     * @param cost precio del delivery
     * @param startWeight peso mínimo del producto admitido para este costo
     * @param endWeight peso máximo del producto admitido para este costo
     * @return el método de delivery creado
     * @throws ElasticSearchException
     */
    public DeliveryMethod create(String name, Float cost, Float startWeight, Float endWeight) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByName(name).isPresent()) ex.constraintViolation();

        DeliveryMethod newDeliveryMethod = new DeliveryMethod(name, cost, startWeight, endWeight);
        this.deliveryMethodRepository.save(newDeliveryMethod);

        return newDeliveryMethod;
    }
}
