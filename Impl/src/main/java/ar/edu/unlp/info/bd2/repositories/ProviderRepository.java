package ar.edu.unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.Provider;
import ar.edu.unlp.info.bd2.models.Purchase;
import org.springframework.data.domain.Page;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
	public Provider findByCuit(long cuit);
		
	@Query("SELECT POS.provider FROM ProductOnSale POS WHERE POS.finalDate IS NULL GROUP BY POS.provider ORDER BY MIN(POS.price)")
	public Page<Provider> getProviderLessExpensiveProduct(Pageable pageable);
	
	@Query("SELECT PRO FROM Provider PRO WHERE PRO.Id NOT IN (SELECT Distinct(POS.provider.Id) FROM Purchase PUR INNER JOIN PUR.productOnSale POS WHERE PUR.dateOfPurchase = ?1)")
	public List<Provider> getProvidersDoNotSellOn(Date day);
	
	@Query("SELECT PUR.productOnSale.provider FROM Purchase PUR GROUP BY PUR.productOnSale.provider ORDER BY SUM(PUR.quantity) DESC")
	public List<Provider> getTopNProvidersInPurchases(Pageable pageable);
}