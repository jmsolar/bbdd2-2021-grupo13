package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Provider;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ProviderRepository extends ElasticsearchRepository<Provider, Long> {
    Optional<Provider> findByCuit(Long cuit);
}
