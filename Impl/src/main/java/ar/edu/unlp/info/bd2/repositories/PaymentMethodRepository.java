package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.models.PaymentMethod;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {
	
}