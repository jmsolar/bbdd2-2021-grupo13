package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.Provider;
import ar.edu.unlp.info.bd2.models.Purchase;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
	public Provider findByCuit(long cuit);
	
	@Query("SELECT PUR FROM Purchase PUR INNER JOIN PUR.productOnSale POS INNER JOIN POS.provider PRO WHERE PRO.cuit = ?1")
	public List<Purchase> getPurchasesForProvider(long cuit);
}