package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.ProductOnSale;
import com.grupo13.elasticSearch.services.ProductOnSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/productonsale")
public class ProductOnSaleController {
    private final ProductOnSaleService productOnSaleService;

    @Autowired
    public ProductOnSaleController(ProductOnSaleService productOnSaleService) {
        this.productOnSaleService = productOnSaleService;
    }

    @GetMapping("/{id}")
    public Optional<ProductOnSale> findById(@PathVariable String id) {
        return this.productOnSaleService.findById(id);
    }

    @PostMapping
    public ProductOnSale create(@RequestBody final ProductOnSale productOnSale) throws ElasticSearchException {
        return this.productOnSaleService.create(productOnSale.getProduct(), productOnSale.getProvider(), productOnSale.getPrice(), productOnSale.getInitialDate());
    }
}
