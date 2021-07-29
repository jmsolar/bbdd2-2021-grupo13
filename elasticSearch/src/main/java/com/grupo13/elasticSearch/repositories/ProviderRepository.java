package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Provider;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProviderRepository extends ElasticsearchRepository<Provider, Long> {
}
