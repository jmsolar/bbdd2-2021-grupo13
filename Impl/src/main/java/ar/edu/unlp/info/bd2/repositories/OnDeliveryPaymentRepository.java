package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.OnDeliveryPayment;

@Repository
public interface OnDeliveryPaymentRepository extends CrudRepository<OnDeliveryPayment, Integer> {
	public OnDeliveryPayment findByName(String name);
}