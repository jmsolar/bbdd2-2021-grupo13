package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.models.Product;
import com.grupo13.elasticSearch.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{name}")
    public Optional<Product> findByName(@PathVariable String name) {
        return this.productService.findByName(name);
    }

    @PostMapping
    public Product create(String name, Float weight, Category category) throws ElasticSearchException {
        return this.productService.create(name, weight, category);
    }
}
