package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
		
	@Query("SELECT PUR.client FROM Purchase PUR GROUP BY PUR.client HAVING SUM(PUR.amount) > CAST(?1 AS float)")
	public List<User> getUsersSpendingMoreThan(Float amount);
	
	//@Query("SELECT client FROM Purchase GROUP BY client.Id ORDER BY COUNT(client.Id) DESC")
	@Query("SELECT US FROM Purchase PUR INNER JOIN PUR.client US GROUP BY PUR.client.Id ORDER BY COUNT(PUR.client.Id) DESC")
	public Page<User> getTopNUsersMorePurchase(Pageable pageable);
}