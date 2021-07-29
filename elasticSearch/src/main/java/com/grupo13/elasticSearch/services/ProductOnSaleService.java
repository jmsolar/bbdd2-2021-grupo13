package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.repositories.ProductOnSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOnSaleService {
    private ProductOnSaleRepository productOnSaleRepository;

    @Autowired
    public ProductOnSaleService(ProductOnSaleRepository productOnSaleRepository) {
        this.productOnSaleRepository = productOnSaleRepository;
    }
}
