package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.OnDeliveryPayment;

@Repository
public interface OnDeliveryPaymentRepository extends CrudRepository<OnDeliveryPayment, Integer> {
	public OnDeliveryPayment findByName(String name);
	
	//@Query("SELECT paymentMethod FROM Purchase WHERE TYPE(paymentMethod) = OnDeliveryPayment ORDER BY (paymentMethod.promisedAmount - amount) DESC")
	@Query("SELECT ODP FROM Purchase PUR INNER JOIN PUR.paymentMethod ODP WHERE TYPE(ODP) = OnDeliveryPayment ORDER BY (ODP.promisedAmount - PUR.amount) DESC")
	public Page<OnDeliveryPayment> getMoreChangeOnDeliveryMethod(Pageable pageable);
}