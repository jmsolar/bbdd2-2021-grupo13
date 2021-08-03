package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Provider;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends ElasticsearchRepository<Provider, Long> {
    Provider findByCuit(Long cuit);
}
