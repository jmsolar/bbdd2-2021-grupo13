package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.Category;
import ar.edu.unlp.info.bd2.models.Product;
import org.springframework.data.domain.Page;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	public Product findByName(String name);
	
	@Query("SELECT POS.product FROM ProductOnSale POS WHERE POS.version = 0 GROUP BY POS.product HAVING COUNT(POS.version) > 1 AND (MAX(POS.price)-MIN(POS.price))/MIN(POS.price)*100 > 20")
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice();
	
	@Query("SELECT PRO FROM Product PRO ORDER BY PRO.weight DESC")
	public Page<Product> getHeaviestProduct(Pageable pageable);
	
	@Query("SELECT PROD FROM Product PROD WHERE PROD.id NOT IN (SELECT Distinct(PROD.id) FROM Purchase PUR INNER JOIN PUR.productOnSale POS INNER JOIN POS.product PROD)")
	public List<Product> getProductsNotSold();

	@Query("SELECT POS.product FROM ProductOnSale POS GROUP BY POS.product HAVING COUNT(POS.product)=1")
	public List<Product> getProductsOnePrice();
	
	@Query("SELECT PR FROM Product PR INNER JOIN PR.category CAT WHERE CAT.name = ?1")
	public List<Product> getProductForCategory(String  nameCategory);
	
	@Query("SELECT POS.product FROM ProductOnSale POS ORDER BY POS.price DESC")
	public List<Product> getTop3MoreExpensiveProducts(Pageable pageable);
	
}