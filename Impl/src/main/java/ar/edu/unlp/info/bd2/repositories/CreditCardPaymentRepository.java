package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.models.CreditCardPayment;

public interface CreditCardPaymentRepository extends CrudRepository<CreditCardPayment, Integer> {
	
}