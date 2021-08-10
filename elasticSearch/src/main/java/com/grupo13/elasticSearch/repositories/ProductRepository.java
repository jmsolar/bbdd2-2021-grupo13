package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    Product findByName(String name);

    @Query("{\"sort\" : [{ \"name\" : {\"order\" : \"desc\"} }],\"bool\" : {\"must\" : {\"field\" : {\"weight \" : true}}}}")
    Page<Product> getHeaviestProduct(Pageable pageable);
}
