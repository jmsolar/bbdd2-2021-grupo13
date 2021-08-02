package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.Provider;
import com.grupo13.elasticSearch.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {
    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/{cuit}")
    public Optional<Provider> findByCuit(@PathVariable Long cuit) {
        return  this.providerService.findByCuit(cuit);
    }

    @PostMapping
    public Provider create(@RequestBody final Provider provider) throws ElasticSearchException {
        return this.providerService.create(provider.getCuit());
    }
}
