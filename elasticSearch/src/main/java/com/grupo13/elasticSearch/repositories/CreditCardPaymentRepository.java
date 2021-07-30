package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.CreditCardPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface CreditCardPaymentRepository extends ElasticsearchRepository<CreditCardPayment, Long> {
    Optional<CreditCardPayment> findByName(String name);
}
