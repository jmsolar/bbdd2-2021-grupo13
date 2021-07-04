package ar.edu.unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.DeliveryMethod;
import ar.edu.unlp.info.bd2.models.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.models.Product;
import ar.edu.unlp.info.bd2.models.ProductOnSale;
import ar.edu.unlp.info.bd2.models.Purchase;
import ar.edu.unlp.info.bd2.models.User;

import org.springframework.data.domain.Page;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
	
	@Query("FROM Purchase WHERE client.email = ?1")
	public List<Purchase> findByName(String name);	
	
	@Query("SELECT client FROM Purchase WHERE amount > ?1")
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount);
		
	@Query("SELECT PUR.productOnSale.product FROM Purchase PUR GROUP BY PUR.productOnSale.product ORDER BY COUNT(PUR.productOnSale.product) DESC")
	public Page<Product> getBestSellingProduct(Pageable pageable);
	
	public List<Purchase> findByDateOfPurchaseBetween(Date startDate, Date endDate);
	
	@Query("FROM Purchase WHERE productOnSale.provider.cuit = ?1")
	public List<Purchase> getPurchasesForProvider(long cuit);
}

