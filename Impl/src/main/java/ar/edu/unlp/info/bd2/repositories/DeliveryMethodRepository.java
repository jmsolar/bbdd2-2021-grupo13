package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.models.DeliveryMethod;

public interface DeliveryMethodRepository extends CrudRepository<DeliveryMethod, Integer> {
	
}