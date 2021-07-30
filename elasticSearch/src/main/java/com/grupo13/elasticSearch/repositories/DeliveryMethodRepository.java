package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.DeliveryMethod;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface DeliveryMethodRepository extends ElasticsearchRepository<DeliveryMethod, Long> {
    Optional<DeliveryMethod> findByName(String name);
}
