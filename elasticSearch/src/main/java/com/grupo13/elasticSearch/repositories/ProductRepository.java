package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
