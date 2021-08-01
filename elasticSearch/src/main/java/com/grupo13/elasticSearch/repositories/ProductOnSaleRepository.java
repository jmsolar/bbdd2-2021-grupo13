package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.ProductOnSale;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductOnSaleRepository extends ElasticsearchRepository<ProductOnSale, String> {
    Optional<ProductOnSale> findById(Long id);

    ProductOnSale findLastById(String providerId, String productId);
}
