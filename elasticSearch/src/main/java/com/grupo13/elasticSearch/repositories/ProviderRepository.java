package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Provider;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends ElasticsearchRepository<Provider, Long> {
    Optional<Provider> findByCuit(Long cuit);
}
