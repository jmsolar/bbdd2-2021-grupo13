package com.grupo13.elasticSearch.controllers;

import java.util.Optional;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/{name}")
	public Optional<Category> findByName(@PathVariable String name) {
		return this.categoryService.findByName(name);
	}

	@PostMapping
	public Category create(@PathVariable String name) throws ElasticSearchException {
		return this.categoryService.create(name);
	}
}