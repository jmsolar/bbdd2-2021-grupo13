package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.DeliveryMethod;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMethodRepository extends ElasticsearchRepository<DeliveryMethod, Long> {
    DeliveryMethod findByName(String name);
}
