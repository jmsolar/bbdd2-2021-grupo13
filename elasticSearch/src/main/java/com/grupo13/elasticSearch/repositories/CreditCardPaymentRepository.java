package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.CreditCardPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardPaymentRepository extends ElasticsearchRepository<CreditCardPayment, Long> {
    CreditCardPayment findByName(String name);
}
