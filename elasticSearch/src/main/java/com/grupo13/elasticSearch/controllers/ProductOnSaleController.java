package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.services.ProductOnSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productonsale")
public class ProductOnSaleController {
    private ProductOnSaleService productOnSaleService;

    @Autowired
    public ProductOnSaleController(ProductOnSaleService productOnSaleService) {
        this.productOnSaleService = productOnSaleService;
    }
}
