package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.DeliveryMethod;
import ar.edu.unlp.info.bd2.models.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.models.ProductOnSale;
import ar.edu.unlp.info.bd2.models.Purchase;
import ar.edu.unlp.info.bd2.models.User;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
	
	@Query("SELECT PUR FROM Purchase PUR INNER JOIN PUR.client CLI WHERE CLI.email = ?1")
	public List<Purchase> findByName(String name);	
	
	@Query("SELECT CLI FROM Purchase PUR INNER JOIN PUR.client CLI WHERE PUR.amount > ?1")
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount);
	
}