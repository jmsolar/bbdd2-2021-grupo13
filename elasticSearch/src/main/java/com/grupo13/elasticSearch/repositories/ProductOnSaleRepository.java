package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.ProductOnSale;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOnSaleRepository extends ElasticsearchRepository<ProductOnSale, String> {
    @Query("FROM ProductOnSale POS JOIN FETCH POS.provider PRO WHERE PRO.id = ?1 AND POS.finalDate IS NULL AND POS.product.id = ?2")
    ProductOnSale findLastById(String providerId, String productId);
}
