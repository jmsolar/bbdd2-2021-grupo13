package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.CreditCardPayment;

@Repository
public interface CreditCardPaymentRepository extends CrudRepository<CreditCardPayment, Integer> {
	public CreditCardPayment findByName(String name);
}