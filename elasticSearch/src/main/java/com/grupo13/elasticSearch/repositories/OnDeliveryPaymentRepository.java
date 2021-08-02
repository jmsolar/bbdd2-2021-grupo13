package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.OnDeliveryPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnDeliveryPaymentRepository extends ElasticsearchRepository<OnDeliveryPayment, Long> {
    OnDeliveryPayment findByName(String name);
}
