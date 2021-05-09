package ar.edu.unlp.info.bd2.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import ar.edu.unlp.info.bd2.models.*;
import ar.edu.unlp.info.bd2.repositories.MLRepository;
import ar.edu.unlp.info.bd2.services.MLService;
import ar.edu.unlp.info.bd2.exceptions.*;;

public class MLServiceImpl implements MLService {
	public MLRepository repository;
	
	public MLRepository getRepository() {
		return repository;
	}
	public void setRepository(MLRepository repository) {
		this.repository = repository;
	}
	
	public MLServiceImpl(MLRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Category createCategory(String name) throws MLException {
		MLException ex = new MLException();
				
		Optional<Category> categoryInDB = getCategoryByName(name);
		if (categoryInDB.isPresent()) ex.categoryNotFound();
		
		Category newCategory = new Category(name);
		this.getRepository().save(newCategory);
		
		return newCategory;
	}

	@Override
	public Product createProduct(String name, Float weight, Category category) throws MLException {
		MLException ex = new MLException();
		
		Optional<Product> productInDB = getProductByName(name);
		if (productInDB.isPresent()) ex.constraintViolation();
		
		Product newProduct = new Product(name, weight, category);
		this.getRepository().save(newProduct);
		
		return newProduct;
	}

	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException {
		MLException ex = new MLException();
		
		Optional<User> userInDB = getUserByEmail(email);
		if (userInDB.isPresent()) ex.constraintViolation();
		
		User newUser = new User(email, fullname, password, dayOfBirth);
		this.getRepository().save(newUser);
		
		return newUser;
	}

	@Override
	public Provider createProvider(String name, Long cuit) throws MLException {
		MLException ex = new MLException();
		
		Optional<Provider> providerDB = getProviderByCuit(cuit);
		if (providerDB.isPresent()) ex.constraintViolation();
		
		Provider newProvider = new Provider(name, cuit);
		this.getRepository().save(newProvider);
		
		return newProvider;
	}

	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight)
			throws MLException {
		MLException ex = new MLException();
		
		DeliveryMethod newDeliveryMethod = new DeliveryMethod(name, cost, startWeight, endWeight);
		this.getRepository().save(newDeliveryMethod);
		
		return newDeliveryMethod;
	}

	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv,
			String owner) throws MLException {
		MLException ex = new MLException();
				
		Optional<CreditCardPayment> creditCardPaymentInDB = getCreditCardPaymentByName(name);
		if (creditCardPaymentInDB.isPresent()) ex.constraintViolation();
		
		CreditCardPayment newCreditCardPayment = new CreditCardPayment(name, brand, number, expiry, cvv, owner);
		this.getRepository().save(newCreditCardPayment);
		
		return newCreditCardPayment;
	}

	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		MLException ex = new MLException();
		
		Optional<OnDeliveryPayment> onDeliveryPaymentInDB = getOnDeliveryPaymentByName(name);
		if (onDeliveryPaymentInDB.isPresent()) ex.constraintViolation();
		
		OnDeliveryPayment newOnDeliveryPayment = new OnDeliveryPayment(name, promisedAmount);
		this.getRepository().save(newOnDeliveryPayment);
		
		return newOnDeliveryPayment;
	}

	@Override
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate)
			throws MLException {
		MLException ex = new MLException();
			
		ProductOnSale prodOnSale = this.getRepository().getLastProductOnSaleById(provider.getId(), product.getId());
		if (prodOnSale != null && prodOnSale.getInitialDate().after(initialDate)) ex.priceValidity();

		if (prodOnSale != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(initialDate);
			cal.add(Calendar.DATE, -1);
			prodOnSale.setFinalDate(cal.getTime());
			this.getRepository().update(prodOnSale);
		}
		
		ProductOnSale newProductOnSale = new ProductOnSale(product, provider, price, initialDate);
		product.getProductsOnSale().add(newProductOnSale);
		this.getRepository().save(newProductOnSale);
			
		return newProductOnSale;
	}

	@Override
	public Purchase createPurchase(ProductOnSale productOnSale, Integer quantity, User client,
			DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, String address, Float coordX, Float coordY,
			Date dateOfPurchase) throws MLException {
		MLException ex = new MLException();
		
		Float totalWeight = productOnSale.getProduct().getWeight() * quantity;
		if (totalWeight < deliveryMethod.getStartWeight() || totalWeight > deliveryMethod.getEndWeight()) ex.deliveryMethodInvalid();
		
		Purchase newPurchase = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
		this.getRepository().save(newPurchase);
		
		return newPurchase;
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return this.getRepository().getUserByEmail(email);
	}

	@Override
	public Optional<Provider> getProviderByCuit(long cuit) {
		return this.getRepository().getProviderByCuit(cuit);
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		return this.getRepository().getCategoryByName(name);
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		return this.getRepository().getProductByName(name);
	}

	@Override
	public ProductOnSale getProductOnSaleById(Long id) {
		return this.getRepository().getProductOnSaleById(id);
	}

	@Override
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		return this.getRepository().getDeliveryMethodByName(name);
	}

	@Override
	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		return this.getRepository().getCreditCardPaymentByName(name);
	}

	@Override
	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		return this.getRepository().getOnDeliveryPaymentByName(name);
	}

	@Override
	public Optional<Purchase> getPurchaseById(Long id) {
		return this.getRepository().getPurchaseById(id);
	}	
}