package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.DeliveryMethod;
import com.grupo13.elasticSearch.services.DeliveryMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/deliverymethod")
public class DeliveryMethodController {
    private final DeliveryMethodService deliveryMethodService;

    @Autowired
    public DeliveryMethodController(DeliveryMethodService deliveryMethodService) {
        this.deliveryMethodService = deliveryMethodService;
    }

    @GetMapping("/{name}")
    public Optional<DeliveryMethod> findByName(@PathVariable String name) {
        return this.deliveryMethodService.findByName(name);
    }

    @PostMapping
    public DeliveryMethod create(@RequestBody final DeliveryMethod deliveryMethod) throws ElasticSearchException {
        return this.deliveryMethodService.create(deliveryMethod.getName());
    }
}
