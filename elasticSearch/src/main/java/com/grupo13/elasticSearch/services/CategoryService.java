package com.grupo13.elasticSearch.services;

import java.util.Optional;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.repositories.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * @param name nombre de la categoria a buscar
	 * @return Category
	 */
	public Optional<Category> findByName(String name) {
		return this.categoryRepository.findByName(name);
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
}