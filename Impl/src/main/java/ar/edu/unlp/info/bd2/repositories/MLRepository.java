package ar.edu.unlp.info.bd2.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.info.bd2.exceptions.MLException;
import ar.edu.unlp.info.bd2.models.*;

@Transactional
public class MLRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Serializable save(Object object) throws MLException {
		Session session = null;
		
		try {
			session = this.sessionFactory.getCurrentSession();
			return session.save(object);
		}
		catch (Exception ex) {
			if (ex.getClass().equals(org.hibernate.exception.ConstraintViolationException.class)) {
				throw new MLException("Constraint violation");
			}
			else {
				throw new MLException("Object can't be save");
			}
		}
	}
	
	public void update(Object object) {
		Session session = null;
		
		try {
			session = this.sessionFactory.getCurrentSession();
			session.update(object);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public Optional<User> getUserByEmail(String email) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM User WHERE email = ?1", User.class).setParameter(1, email).uniqueResultOptional();
	}
	
	public Optional<Provider> getProviderByCuit(long cuit) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Provider WHERE cuit = ?1", Provider.class).setParameter(1, cuit).uniqueResultOptional();
	}
	
	public Optional<Category> getCategoryByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Category WHERE name = ?1", Category.class).setParameter(1, name).uniqueResultOptional();
	}
	
	public Optional<Product> getProductByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Product WHERE name = ?1", Product.class).setParameter(1, name).uniqueResultOptional();
	}
	
	public ProductOnSale getProductOnSaleById(Long id) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM ProductOnSale WHERE id_product_on_sale = ?1", ProductOnSale.class).setParameter(1, id).uniqueResult();
	}
	
	public ProductOnSale getLastProductOnSaleById(int idProvider, Long idProduct) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM ProductOnSale POS JOIN FETCH POS.provider PRO WHERE PRO.id = ?1 AND POS.finalDate IS NULL AND POS.product.id = ?2", ProductOnSale.class).setParameter(1, idProvider).setParameter(2, idProduct).uniqueResult();
	}	
	
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM DeliveryMethod WHERE name = ?1", DeliveryMethod.class).setParameter(1, name).uniqueResultOptional();
	}
	

	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM CreditCardPayment WHERE name = ?1 AND payment_type = ?2").setParameter(1, name).setParameter(2, 1).uniqueResultOptional();
	}

	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM OnDeliveryPayment WHERE name = ?1 AND payment_type = ?2").setParameter(1, name).setParameter(2, 2).uniqueResultOptional();
	}

	public Optional<Purchase> getPurchaseById(Long id) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Purchase WHERE id = ?1", Purchase.class).setParameter(1, id).uniqueResultOptional();
	}
	
	public List<Purchase> getAllPurchasesMadeByUser(String username) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR FROM Purchase PUR INNER JOIN PUR.client CLI WHERE CLI.email = ?1").setParameter(1, username).list();
	}
	
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT CLI FROM Purchase PUR INNER JOIN PUR.client CLI WHERE PUR.amount > ?1").setParameter(1, amount).list();
	}

	public List<User>  getUsersSpendingMoreThan(Float amount) {		
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR.client FROM Purchase PUR GROUP BY PUR.client HAVING SUM(PUR.amount) > CAST(?1 AS float)", User.class).setParameter(1, amount).list();	
	}
	
	public List<Provider> getTopNProvidersInPurchases(int n){
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR.productOnSale.provider FROM Purchase PUR GROUP BY PUR.productOnSale.provider ORDER BY SUM(PUR.quantity) DESC", Provider.class).setMaxResults(n).list();
	}
	
	public List<Product>  getTop3MoreExpensiveProducts() {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT POS.product FROM ProductOnSale POS ORDER BY POS.price DESC", Product.class).setMaxResults(3).list();
	}

	public List<User> getTopNUsersMorePurchase(int n) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT US FROM Purchase PUR INNER JOIN PUR.client US GROUP BY PUR.client.Id ORDER BY COUNT(PUR.client.Id) DESC", User.class).setMaxResults(n).list();		
	}
	
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Purchase WHERE dateOfPurchase >= ?1 AND dateOfPurchase <= ?2").setParameter(1, startDate).setParameter(2, endDate).list();
	}
	
	public List<Product> getProductForCategory(Category category) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PR FROM Product PR INNER JOIN PR.category CAT WHERE CAT.name = ?1").setParameter(1, category.getName()).list();
	}
	
	public List<Purchase>  getPurchasesForProvider(Long cuit){
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR FROM Purchase PUR INNER JOIN PUR.productOnSale POS INNER JOIN POS.provider PRO WHERE PRO.cuit = ?1").setParameter(1, cuit).list();
	}
	
	public Product getBestSellingProduct() {
		 return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR.productOnSale.product FROM Purchase PUR GROUP BY PUR.productOnSale.product ORDER BY COUNT(PUR.productOnSale.product) DESC", Product.class).setMaxResults(1).getSingleResult();
	}	
	
	public List<Product> getProductsOnePrice() {
		 return this.sessionFactory.getCurrentSession().createQuery("SELECT POS.product FROM ProductOnSale POS GROUP BY POS.product HAVING COUNT(POS.product)=1", Product.class).list();
	}	
	
	// 12
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice() {
		 return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR.productOnSale.product FROM Purchase PUR GROUP BY PUR.productOnSale.product ORDER BY COUNT(PUR.productOnSale.product) DESC", Product.class).list();
	}
	
	public Provider getProviderLessExpensiveProduct() {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT POS.provider FROM ProductOnSale POS WHERE POS.finalDate IS NULL GROUP BY POS.provider ORDER BY MIN(POS.price)", Provider.class).setMaxResults(1).getSingleResult();
	}
	public List<Provider> getProvidersDoNotSellOn(Date day) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PRO FROM Provider PRO WHERE PRO.Id NOT IN (SELECT Distinct(POS.provider.Id) FROM Purchase PUR INNER JOIN PUR.productOnSale POS WHERE PUR.dateOfPurchase = ?1)").setParameter(1, day).list();			
	}
		
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT Distinct(PUR.productOnSale) FROM Purchase PUR INNER JOIN PUR.productOnSale POS WHERE PUR.dateOfPurchase = ?1").setParameter(1, day).list();
	}
	
	public List<Product> getProductsNotSold() {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PROD FROM Product PROD WHERE PROD.id NOT IN (SELECT Distinct(PROD.id) FROM Purchase PUR INNER JOIN PUR.productOnSale POS INNER JOIN POS.product PROD)").list();			
	}		
	
	public DeliveryMethod getMostUsedDeliveryMethod() {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR.deliveryMethod FROM Purchase PUR GROUP BY PUR.deliveryMethod ORDER BY COUNT(PUR.deliveryMethod) DESC", DeliveryMethod.class).setMaxResults(1).getSingleResult();
	}  	
		
	public OnDeliveryPayment getMoreChangeOnDeliveryMethod() {
		return ((OnDeliveryPayment) this.sessionFactory.getCurrentSession().createQuery("SELECT ODP FROM Purchase PUR INNER JOIN PUR.paymentMethod ODP WHERE TYPE(ODP) = OnDeliveryPayment ORDER BY (ODP.promisedAmount - PUR.amount) DESC").setMaxResults(1).uniqueResult());
	}
															
	public Product getHeaviestProduct() {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Product PRO ORDER BY PRO.weight DESC", Product.class).setMaxResults(1).getSingleResult();
	}
		
	public Category testGetCategoryWithLessProducts() {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PRO.category FROM Product PRO GROUP BY PRO.category ORDER BY COUNT(PRO.category) ASC", Category.class).setMaxResults(1).getSingleResult();
	}

}