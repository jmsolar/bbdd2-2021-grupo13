package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.OnDeliveryPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface OnDeliveryPaymentRepository extends ElasticsearchRepository<OnDeliveryPayment, Long> {
    Optional<OnDeliveryPayment> findByName(String name);
}
