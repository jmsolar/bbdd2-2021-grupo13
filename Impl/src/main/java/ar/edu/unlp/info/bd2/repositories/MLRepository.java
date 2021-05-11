package ar.edu.unlp.info.bd2.repositories;

import java.io.Serializable;
//import java.sql.Date;
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
	
	public List<Purchase> getAllPurchasesMadeByUser(String username) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PUR FROM Purchase PUR INNER JOIN PUR.client CLI WHERE CLI.email = ?1").setParameter(1, username).list();
	}
	
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT US FROM USER US INNER JOIN PURCHASE PUR WHERE PUR.AMOUNT > ?1").setParameter(1, amount).list();
	}
	
	public List<User>  getUsersSpendingMoreThan(Float amount) {
		// hacer
		return null;	
	}

	public List<Product> getProductForCategory(Category category) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT PR FROM PRODUCT PR INNER JOIN CATEGORY CAT WHERE CAT.NAME = ?1").setParameter(1, category).list();
	}
	// los clientes son los que realizan las compras, no los proveeedores: VER!
	public List<Purchase> getPurchasesForProvider(String cuit){
		return null;
	}
	
	// importa de la clase java.util.Date y no de java.SQL.Date
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT POS FROM ProductOnSale POS INNER JOIN Purchase PUR WHERE PUR.dateOfPurchase = ?1 AND POS.id = PUR.productOnSale").setParameter(1, day).list();
	}
	
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT Purchase PUR WHERE PUR.dateOfPurchase >= ?1 AND PUR.dateOfPurchase <= ?2  AND POS.id = PUR.productOnSale").setParameter(1, startDate).setParameter(2, endDate).list();
	}
	
}