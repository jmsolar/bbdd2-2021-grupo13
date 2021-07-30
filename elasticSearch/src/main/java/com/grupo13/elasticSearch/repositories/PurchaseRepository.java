package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Purchase;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface PurchaseRepository extends ElasticsearchRepository<Purchase, Long> {
    Optional<Purchase> findById(Long id);
}
