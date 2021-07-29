package com.grupo13.elasticSearch.services;

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
	
	public Optional<Category> findByName(String name) {
		return this.categoryRepository.findByName(name);
	}
	
	public void create(Category category) {
		if (!this.findByName(category.getName()).isPresent())
			this.categoryRepository.save(category);
	}
}