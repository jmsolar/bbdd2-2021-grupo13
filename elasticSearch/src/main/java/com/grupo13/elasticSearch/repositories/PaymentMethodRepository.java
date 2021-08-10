package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.PaymentMethod;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends ElasticsearchRepository<PaymentMethod, Long> {
    PaymentMethod findByName(String name);
}
