package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.ProductOnSale;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductOnSaleRepository extends ElasticsearchRepository<ProductOnSale, Long> {
}
