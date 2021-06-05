package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	
}