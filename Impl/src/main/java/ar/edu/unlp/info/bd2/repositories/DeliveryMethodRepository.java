package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.DeliveryMethod;

@Repository
public interface DeliveryMethodRepository extends CrudRepository<DeliveryMethod, Integer> {
	
	public Page<DeliveryMethod> findByName(String name, Pageable page);
	
	@Query("SELECT PUR.deliveryMethod FROM Purchase PUR GROUP BY PUR.deliveryMethod ORDER BY COUNT(PUR.deliveryMethod) DESC")
	public Page<DeliveryMethod> getMostUsedDeliveryMethod(Pageable pageable);
}