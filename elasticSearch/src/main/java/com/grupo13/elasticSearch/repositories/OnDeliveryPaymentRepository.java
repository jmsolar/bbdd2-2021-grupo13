package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.OnDeliveryPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OnDeliveryPaymentRepository extends ElasticsearchRepository<OnDeliveryPayment, Long> {
}
