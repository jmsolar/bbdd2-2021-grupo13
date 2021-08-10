package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Purchase;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends ElasticsearchRepository<Purchase, String> {
    Optional<Purchase> findById(String id);

}