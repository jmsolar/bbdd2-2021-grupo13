package ar.edu.unlp.info.bd2.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import ar.edu.unlp.info.bd2.models.*;
import ar.edu.unlp.info.bd2.repositories.MLRepository;
import ar.edu.unlp.info.bd2.services.MLService;
import ar.edu.unlp.info.bd2.exceptions.*;
import ar.edu.unlp.info.bd2.exceptions.impl.*;

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
		CategoryException ex = new CategoryException();
		
		if (name == null|| name.trim().isEmpty()) ex.nameRequired();
		
		Optional<Category> categoryInDB = getCategoryByName(name);
		if (categoryInDB.isPresent()) ex.categoryExist();
		
		Category newCategory = new Category(name);
		this.getRepository().save(newCategory);
		
		return newCategory;
	}

	@Override
	public Product createProduct(String name, Float weight, Category category) throws MLException {
		ProductException ex = new ProductException();
		
		if (name == null|| name.trim().isEmpty()) ex.nameRequired();
		
		if (weight == null) ex.weightRequired();
		
		Optional<Product> productInDB = getProductByName(name);
		if (productInDB.isPresent()) ex.productExist();
		
		Product newProduct = new Product(name, weight, category);
		this.getRepository().save(newProduct);
		
		return newProduct;
	}

	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException {
		UserException ex = new UserException();
		
		if (email == null|| email.trim().isEmpty()) ex.emailRequired();
		if (fullname == null|| fullname.trim().isEmpty()) ex.fullnameRequired();
		if (password == null|| password.trim().isEmpty()) ex.passwordRequired();
		if (dayOfBirth == null) ex.dayOfBirthRequired();
		
		Optional<User> userInDB = getUserByEmail(email);
		if (userInDB.isPresent()) ex.emailExist();
		
		User newUser = new User(email, fullname, password, dayOfBirth);
		this.getRepository().save(newUser);
		
		return newUser;
	}

	@Override
	public Provider createProvider(String name, Long cuit) throws MLException {
		ProviderException ex = new ProviderException();
		
		if (name == null|| name.trim().isEmpty()) ex.nameRequired();
		if (cuit == null || cuit <= 0) ex.cuitRequired();
		
		Optional<Provider> providerDB = getProviderByCuit(cuit);
		if (providerDB.isPresent()) ex.cuitExist();
		
		Provider newProvider = new Provider(name, cuit);
		this.getRepository().save(newProvider);
		
		return newProvider;
	}

	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight)
			throws MLException {
		DeliveryMethodException ex = new DeliveryMethodException();
		
		if (name.isBlank() || name.isEmpty()) ex.nameRequired();
		if (cost == null ) ex.costRequired();
		if (startWeight == null) ex.startWeightRequired();
		if (endWeight == null) ex.endWeightRequired();
		
		Optional<DeliveryMethod> deliveryMethodInDB = getDeliveryMethodByName(name);
		if (deliveryMethodInDB.isPresent()) ex.nameExist();
		
		DeliveryMethod newDeliveryMethod = new DeliveryMethod(name, cost, startWeight, endWeight);
		this.getRepository().save(newDeliveryMethod);
		
		return newDeliveryMethod;
	}

	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv,
			String owner) throws MLException {
		CreditCardPaymentException ex = new CreditCardPaymentException();
		
		if (name == null|| name.trim().isEmpty()) ex.nameRequired();
		if (brand == null|| brand.trim().isEmpty()) ex.brandRequired();
		if (number == null ) ex.numberRequired();
		if (expiry == null) ex.expiryRequired();
		if (cvv == null) ex.cvvRequired();
		if (owner == null|| owner.trim().isEmpty()) ex.ownerRequired();
		
		Optional<CreditCardPayment> creditCardPaymentInDB = getCreditCardPaymentByName(name);
		if (creditCardPaymentInDB.isPresent()) ex.nameExist();
		
		CreditCardPayment newCreditCardPayment = new CreditCardPayment(name, brand, number, expiry, cvv, owner);
		this.getRepository().save(newCreditCardPayment);
		
		return newCreditCardPayment;
	}

	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		OnDeliveryPaymentException ex = new OnDeliveryPaymentException();
		
		if (name == null|| name.trim().isEmpty()) ex.nameRequired();
		if (promisedAmount == null) ex.promisedAmountRequired();
		
		Optional<OnDeliveryPayment> onDeliveryPaymentInDB = getOnDeliveryPaymentByName(name);
		if (onDeliveryPaymentInDB.isPresent()) ex.nameExist();
		
		OnDeliveryPayment newOnDeliveryPayment = new OnDeliveryPayment(name, promisedAmount);
		this.getRepository().save(newOnDeliveryPayment);
		
		return newOnDeliveryPayment;
	}

	@Override
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate)
			throws MLException {
		ProductOnSaleException ex = new ProductOnSaleException();
		
		if (price == null) ex.priceRequired();
		if (initialDate == null) ex.initialDateRequired();
		Optional<Provider> prov = this.getRepository().getProviderByCuit(provider.getCuit());
		if (prov == null) throw new MLException("El proveedor no existe");
		
		ProductOnSale prodOnSale = this.getRepository().getLastProductOnSaleById(product.getId(), provider.getCuit());
		if (prodOnSale != null && prodOnSale.getInitialDate().before(initialDate)) throw new MLException("La fecha de inicio es anterior a la de la última oferta");

		if (prodOnSale != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(initialDate);
			cal.add(Calendar.DATE, -1);
			prodOnSale.setFinalDate(cal.getTime());
			this.getRepository().update(prodOnSale);
		}
		
		ProductOnSale newProductOnSale = new ProductOnSale(product, provider, price, initialDate);
		this.getRepository().save(newProductOnSale);
			
		return newProductOnSale;
	}

	@Override
	public Purchase createPurchase(ProductOnSale productOnSale, Integer quantity, User client,
			DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, String address, Float coordX, Float coordY,
			Date dateOfPurchase) throws MLException {
		PurchaseException ex = new PurchaseException();
		
		if (quantity == null) ex.quantityRequired();
		if (address == null|| address.trim().isEmpty()) ex.addressRequired();
		if (coordX == null) ex.coordXRequired();
		if (coordY == null) ex.coordYRequired();
		if (dateOfPurchase == null) ex.dateOfPurchaseRequired();
		
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