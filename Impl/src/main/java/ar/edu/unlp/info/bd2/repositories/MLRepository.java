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
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_USER where email in :email", User.class).uniqueResultOptional();
	}
	
	public Optional<Provider> getProviderByCuit(long cuit) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PROVIDER where cuit in :cuit", Provider.class).uniqueResultOptional();
	}
	
	public Optional<Category> getCategoryByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from Category WHERE name = :name", Category.class).uniqueResultOptional();
	}
	
	public Optional<Product> getProductByName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PRODUCT where name in :name", Product.class).uniqueResultOptional();
	}
	
	public ProductOnSale getProductOnSaleById(Long id) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PRODUCT_ON_SALE where id = :id", ProductOnSale.class).uniqueResult();
	}
	
	public ProductOnSale getLastProductOnSaleById(Long id, int idProvider) {
		return this.sessionFactory.getCurrentSession().createQuery("from BD2_PRODUCT_ON_SALE POS inner join BD2_PROVIDER PRO ON (POS.id_product_on_sale = PRO.productsOnSale) where POS.id_product_on_sale = :id and final_date IS NULL and PRO.id = :idProvider", ProductOnSale.class).uniqueResult();
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