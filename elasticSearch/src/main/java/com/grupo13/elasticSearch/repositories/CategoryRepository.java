package com.grupo13.elasticSearch.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.grupo13.elasticSearch.models.Category;

@Repository
public interface CategoryRepository extends ElasticsearchRepository<Category, String> {
	Category findByName(String name);
}