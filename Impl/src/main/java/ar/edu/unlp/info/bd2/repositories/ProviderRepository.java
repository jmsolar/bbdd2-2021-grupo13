package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	public Provider findByCuit(long cuit);
	
}