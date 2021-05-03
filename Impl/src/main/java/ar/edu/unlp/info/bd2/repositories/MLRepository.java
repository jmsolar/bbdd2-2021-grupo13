package ar.edu.unlp.info.bd2.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.info.bd2.exceptions.MLException;
import ar.edu.unlp.info.bd2.models.*;

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
		return this.sessionFactory.getCurrentSession().createQuery("FROM ProductOnSale WHERE id = ?1", ProductOnSale.class).setParameter(1, id).uniqueResult();
	}
	
	public ProductOnSale getLastProductOnSaleById(Long id, int idProvider) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM ProductOnSale pos INNER JOIN Provider pro ON (pos.id_product_on_sale = pro.productsOnSale) where POS.id_product_on_sale = ?1 and final_date IS NULL and PRO.id = ?2", ProductOnSale.class).setParameter(1, id).setParameter(2, idProvider).uniqueResult();
	}	
	
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM DeliveryMethod WHERE name = ?1", DeliveryMethod.class).setParameter(1, name).uniqueResultOptional();
	}

	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM PaymentMethod WHERE name = ?1 AND payment_type = ?2", CreditCardPayment.class).setParameter(1, name).setParameter(2, 1).uniqueResultOptional();
	}

	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM PaymentMethod WHERE name = ?1 AND payment_type = ?2", OnDeliveryPayment.class).setParameter(1, name).setParameter(2, 2).uniqueResultOptional();
	}

	public Optional<Purchase> getPurchaseById(Long id) {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Purchase WHERE id = ?1", Purchase.class).setParameter(1, id).uniqueResultOptional();
	}
}