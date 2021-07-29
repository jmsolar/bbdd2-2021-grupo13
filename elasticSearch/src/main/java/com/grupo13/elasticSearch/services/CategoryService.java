package com.grupo13.elasticSearch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.repositories.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * @param name nombre de la categoria a buscar
	 * @return
	 */
	public Optional<Category> findByName(String name) {
		return this.categoryRepository.findByName(name);
	}

	/**
	 * Crea y devuelve el id de la categoria creada
	 * @param category contiene la informacion para la creacion
	 * @return el id de la categoria creada
	 */
	public Long create(Category category) {
		if (!this.findByName(category.getName()).isPresent())
			this.categoryRepository.save(category);

		return this.findByName(category.getName()).get().getId();
	}
}