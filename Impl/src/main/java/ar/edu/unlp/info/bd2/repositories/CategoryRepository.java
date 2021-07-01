package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.Category;
import ar.edu.unlp.info.bd2.models.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	public Category findByName(String name);	
	
	
	@Query("SELECT PRO.category FROM Product PRO GROUP BY PRO.category ORDER BY COUNT(PRO.category) ASC")
	public Page<Category> getCategoryWithLessProducts(Pageable pageable);
	
}