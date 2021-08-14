package com.grupo13.elasticSearch.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.repositories.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	private RestHighLevelClient client;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * @param name nombre de la categoria a buscar
	 * @return Category
	 */

	public Optional<Category> findByName(String name) {
		Category category = this.categoryRepository.findByName(name);
		Optional<Category> opt = Optional.ofNullable(category);

		return opt;
	}

	/**
	 * Crea y devuelve una nueva Catogoria
	 * @param name nombre del producto a ser creado
	 * @return la categoria creada
	 * @throws ElasticSearchException
	 */
	public Category create(String name) throws ElasticSearchException {
		ElasticSearchException ex = new ElasticSearchException();

		if (this.findByName(name).isPresent()) ex.constraintViolation();

		Category newCategory = new Category(name);
		this.categoryRepository.save(newCategory);

		return newCategory;
	}

	/**
	 *
	 * @return la categoría con menor cantidad de productos
	 */
	public Category getCategoryWithLessProducts() {
		Category category = new Category();
		try {
			SearchRequest searchRequest = new SearchRequest("products");
			List<BucketOrder> bucketOrderList = new ArrayList<>();
			bucketOrderList.add(BucketOrder.aggregation("_count", true));
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0);
			searchSourceBuilder.aggregation(AggregationBuilders.terms("products").field("category.name.keyword").order(bucketOrderList));
			searchRequest.source(searchSourceBuilder);
			SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
			Terms terms = res1.getAggregations().get("products");
			category = this.categoryRepository.findByName(terms.getBuckets().get(0).getKeyAsString());
		}
		catch (Exception e) {}

		return category;
	}
}