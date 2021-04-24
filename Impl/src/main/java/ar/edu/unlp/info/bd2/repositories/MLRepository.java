package ar.edu.unlp.info.bd2.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.info.bd2.exceptions.MLException;
import ar.edu.unlp.info.bd2.models.Category;
import ar.edu.unlp.info.bd2.models.CreditCardPayment;
import ar.edu.unlp.info.bd2.models.DeliveryMethod;
import ar.edu.unlp.info.bd2.models.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.models.Product;
import ar.edu.unlp.info.bd2.models.ProductOnSale;
import ar.edu.unlp.info.bd2.models.Provider;
import ar.edu.unlp.info.bd2.models.Purchase;
import ar.edu.unlp.info.bd2.models.User;

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
	
	// Pueden cambiarse los metodos por sp?
	
	public Optional<User> getUserByEmail(String email) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_USER where email in :email", User.class).uniqueResultOptional();
	}
	
	public Optional<Provider> getProviderByCuit(long cuit) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PROVIDER where cuit in :cuit", Provider.class).uniqueResultOptional();
	}
	
	public Optional<Category> getCategoryByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_CATEGORY where name in :name", Category.class).uniqueResultOptional();
	}
	
	public Optional<Product> getProductByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PRODUCT where name in :name", Product.class).uniqueResultOptional();
	}
	
	public ProductOnSale getProductOnSaleById(Long id) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PRODUCT_ON_SALE where id = :id", ProductOnSale.class).uniqueResult();
	}
	
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_DELIVERY_METHOD where name in :name", DeliveryMethod.class).uniqueResultOptional();
	}

	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PAYMENT_METHOD where name in :name AND payment_type = 'credit_card_payment'", CreditCardPayment.class).uniqueResultOptional();
	}

	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PAYMENT_METHOD where name in :name AND payment_type = 'on_delivery_payment'", OnDeliveryPayment.class).uniqueResultOptional();
	}

	public Optional<Purchase> getPurchaseById(Long id) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PURCHASE where id in :name", Purchase.class).uniqueResultOptional();
	}
}