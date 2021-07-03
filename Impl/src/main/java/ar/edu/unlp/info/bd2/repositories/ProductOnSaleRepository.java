package ar.edu.unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.info.bd2.exceptions.MLException;
import ar.edu.unlp.info.bd2.models.*;


import ar.edu.unlp.info.bd2.models.ProductOnSale;

@Repository
public interface ProductOnSaleRepository extends CrudRepository<ProductOnSale, Long> {
	@Query("SELECT POS FROM ProductOnSale POS WHERE id = ?1")
	public ProductOnSale findByIdentificador(long id);
	
	@Query("FROM ProductOnSale POS JOIN FETCH POS.provider PRO WHERE PRO.id = ?1 AND POS.finalDate IS NULL AND POS.product.id = ?2")
	public ProductOnSale getLastProductOnSaleById(int providerId, Long productId);
	
	@Query("SELECT Distinct(PUR.productOnSale) FROM Purchase PUR INNER JOIN PUR.productOnSale POS WHERE PUR.dateOfPurchase = ?1")
	public List<ProductOnSale> getSoldProductsOn(Date day);
	
}