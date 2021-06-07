package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.ProductOnSale;

@Repository
public interface ProductOnSaleRepository extends CrudRepository<ProductOnSale, Long> {
	
	@Query("SELECT POS FROM ProductOnSale POS WHERE id = ?1")
	public ProductOnSale findByIdentificador(long id);
	
	@Query("FROM ProductOnSale POS JOIN FETCH POS.provider PRO WHERE PRO.id = ?1 AND POS.finalDate IS NULL AND POS.product.id = ?2")
	public ProductOnSale getLastProductOnSaleById(int providerId, Long productId);
}