package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	public Product findByName(String name);
	
	@Query("SELECT POS.product FROM ProductOnSale POS WHERE POS.version = 0 GROUP BY POS.product HAVING COUNT(POS.version) > 1 AND (MAX(POS.price)-MIN(POS.price))/MIN(POS.price)*100 > 20")
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice();
}