package ar.edu.unlp.info.bd2.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		return this.sessionFactory.getCurrentSession().createQuery("SELECT POS FROM Provider PRO INNER JOIN PRO.productsOnSale POS WHERE PRO.id = ?1 AND POS.finalDate IS NULL AND POS.product.id = ?2", ProductOnSale.class).setParameter(1, idProvider).setParameter(2, idProduct).uniqueResult();
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
	// 1 - OK
	public List<Purchase> getAllPurchasesMadeByUser(String username) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR FROM Purchase PUR INNER JOIN PUR.client CLI WHERE CLI.email = ?1").setParameter(1, username).list();
	}
	// 2 - OK - MARQUITOS
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT CLI FROM Purchase PUR INNER JOIN PUR.client CLI WHERE PUR.amount > ?1").setParameter(1, amount).list();
	}
	
	// 3 - PARA CHARLAR
	//Obtiene los usuarios que han gastando más de amount entre todas sus compras en la plataforma
	public List<User>  getUsersSpendingMoreThan(Float amount) {		
		return null;	
	}
	
	// 6- FALLA LA IMPLEMENTACION DEL TEST. VER PORQUE Y DESPUES CORRER LA QUERY - MARQUITOS
	public List<User> getTopNUsersMorePurchase(int n) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT US FROM User INNER JOIN US.purchases PUR GROUP BY User.id_user ORDER BY COUNT(User.id_user) DESC").setMaxResults(n).list();		
	}
	
	// 5 - MATI
	public List<Product>  getTop3MoreExpensiveProducts() {
		return this.sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT Product INNER JOIN ProductOnSale ON (Product.id_product = ProductOnSale.id_product) ORDER BY ProductOnSale.price DESC LIMIT 3").list();
	}															  // SELECT DISTINCT PRO FROM Product PRO INNER JOIN	PRO.productOnSale PRO ORDER BY price DESC LIMIT 3").setParameter(1, amount).list();
															    // SELECT DISTINCT POS FROM ProductOnSale POS INNER JOIN POS.product PRO ORDER BY POS.price DESC LIMIT 3
	// 7 - OK - MARQUITOS
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Purchase WHERE dateOfPurchase >= ?1 AND dateOfPurchase <= ?2").setParameter(1, startDate).setParameter(2, endDate).list();
	}
	
	// 8 - OK - MARQUITOS
	public List<Product> getProductForCategory(Category category) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PR FROM Product PR INNER JOIN PR.category CAT WHERE CAT.name = ?1").setParameter(1, category.getName()).list();
	}
	
	// 9 - OK - MARQUITOS
	public List<Purchase>  getPurchasesForProvider(Long cuit){
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR FROM Purchase PUR INNER JOIN PUR.productOnSale POS INNER JOIN POS.provider PRO WHERE PRO.cuit = ?1").setParameter(1, cuit).list();
	}																
				
	// 14 - OK - MARQUITOS
	public List<Provider> getProvidersDoNotSellOn(Date day) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PRO FROM Provider PRO WHERE PRO.Id NOT IN (SELECT Distinct(POS.provider.Id) FROM Purchase PUR INNER JOIN PUR.productOnSale POS WHERE PUR.dateOfPurchase = ?1)").setParameter(1, day).list();			
	}
		
	// 15 - OK - MARQUITOS
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT Distinct(PUR.productOnSale) FROM Purchase PUR INNER JOIN PUR.productOnSale POS WHERE PUR.dateOfPurchase = ?1").setParameter(1, day).list();
	}
	
	// 16 - OK - MARQUITOS
	public List<Product> getProductsNotSold() {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PROD FROM Product PROD WHERE PROD.id NOT IN (SELECT Distinct(PROD.id) FROM Purchase PUR INNER JOIN PUR.productOnSale POS INNER JOIN POS.product PROD)").list();			
	}		
	
	// 17 - OK   MARQUITOS
	public DeliveryMethod getMostUsedDeliveryMethod() {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR.deliveryMethod FROM Purchase PUR GROUP BY PUR.deliveryMethod ORDER BY COUNT(PUR.deliveryMethod) DESC", DeliveryMethod.class).setMaxResults(1).getSingleResult();
	}  	
		
	// 18 - VER! - MARQUITOS
	public Product getMoreChangeOnDeliveryMethod() {
		return this.sessionFactory.getCurrentSession().createQuery("FROM OnDeliveryPayment ODP GROUP BY ODP.Id ORDER BY COUNT(ODP.promisedAmount) DESC", Product.class).setMaxResults(1).getSingleResult();
	}																	                     
															
	// 19 - OK - MARQUITOS
	public Product getHeaviestProduct() {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Product PRO ORDER BY PRO.weight DESC", Product.class).setMaxResults(1).getSingleResult();
	}

}