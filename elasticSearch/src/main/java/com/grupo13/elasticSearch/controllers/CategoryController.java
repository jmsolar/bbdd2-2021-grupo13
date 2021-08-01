package com.grupo13.elasticSearch.controllers;

import java.util.Optional;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private final CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/{name}")
	public Optional<Category> findByName(@PathVariable String name) {
		return this.categoryService.findByName(name);
	}

	@PostMapping
	public Category create(@RequestBody final Category category) throws ElasticSearchException {
		return this.categoryService.create(category.getName());
	}
}