package ar.edu.unlp.info.bd2.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import javax.inject.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.bd2.exceptions.MLException;
import ar.edu.unlp.info.bd2.models.*;
import ar.edu.unlp.info.bd2.models.Provider;
import ar.edu.unlp.info.bd2.repositories.*;
import ar.edu.unlp.info.bd2.services.*;

@Transactional
public class SpringDataMLService implements MLService {
	
	@Inject
	private CategoryRepository categoryRepository;
	
	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	@Inject
	private CreditCardPaymentRepository creditCardPaymentRepository;
	
	public CreditCardPaymentRepository getCreditCardPaymentRepository() {
		return creditCardPaymentRepository;
	}

	@Inject
	private DeliveryMethodRepository deliveryMethodRepository;
	
	public DeliveryMethodRepository getDeliveryMethodRepository() {
		return deliveryMethodRepository;
	}
	
	@Inject
	private OnDeliveryPaymentRepository onDeliveryPaymentRepository;
	
	public OnDeliveryPaymentRepository getOnDeliveryPaymentRepository() {
		return onDeliveryPaymentRepository;
	}
	
	@Inject
	private PaymentMethodRepository paymentMethodRepository;
	
	public PaymentMethodRepository getPaymentMethodRepository() {
		return paymentMethodRepository;
	}
	
	@Inject
	private ProductOnSaleRepository productOnSaleRepository;
	
	public ProductOnSaleRepository getProductOnSaleRepository() {
		return productOnSaleRepository;
	}
	
	@Inject
	private ProductRepository productRepository;
	
	public ProductRepository getProductRepository() {
		return productRepository;
	}
	
	@Inject
	private ProviderRepository providerRepository;
	
	public ProviderRepository getProviderRepository() {
		return providerRepository;
	}
	
	@Inject
	private PurchaseRepository purchaseRepository;

	public PurchaseRepository getPurchaseRepository() {
		return purchaseRepository;
	}
	
	@Inject
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}
	
	public SpringDataMLService() {
	}
	
	@Override
	public Category createCategory(String name) throws MLException { 
		MLException ex = new MLException();
		
		Optional<Category> categoryInDB = this.getCategoryByName(name);
		if (categoryInDB.isPresent()) ex.categoryNotFound();
		
		Category newCategory = new Category(name);
		this.getCategoryRepository().save(newCategory);
		
		return newCategory;
	}
	
	@Override
	public Product createProduct(String name, Float weight, Category category) throws MLException { 
		MLException ex = new MLException();
		
		Optional<Product> productInDB = this.getProductByName(name);
		if (productInDB.isPresent()) ex.constraintViolation();
		
		Product newProduct = new Product(name, weight, category);
		this.getProductRepository().save(newProduct);
		
		return newProduct;
	} 
	
	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException { 
		MLException ex = new MLException();
		
		Optional<User> userInDB = this.getUserByEmail(email);
		if (userInDB.isPresent()) ex.constraintViolation();
		
		User newUser = new User(email, fullname, password, dayOfBirth);
		this.getUserRepository().save(newUser);
		
		return newUser;
	} 
	
	@Override
	public Provider createProvider(String name, Long cuit) throws MLException { 
		MLException ex = new MLException();
		
		Optional<Provider> providerDB = this.getProviderByCuit(cuit);
		if (providerDB.isPresent()) ex.constraintViolation();
		
		Provider newProvider = new Provider(name, cuit);
		this.getProviderRepository().save(newProvider);
		
		return newProvider;
	} 
	
	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight) throws MLException {	
		DeliveryMethod newDeliveryMethod = new DeliveryMethod(name, cost, startWeight, endWeight);
		this.getDeliveryMethodRepository().save(newDeliveryMethod);
		
		return newDeliveryMethod;
	} 
	
	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv, String owner)  throws MLException {
		CreditCardPayment newCreditCardPayment = new CreditCardPayment(name, brand, number, expiry, cvv, owner);
		this.getCreditCardPaymentRepository().save(newCreditCardPayment);
		
		return newCreditCardPayment;
	} 
	
	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		OnDeliveryPayment newOnDeliveryPayment = new OnDeliveryPayment(name, promisedAmount);
		this.getOnDeliveryPaymentRepository().save(newOnDeliveryPayment);
		
		return newOnDeliveryPayment; 
	}
	
	@Override
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate) throws MLException {
		MLException ex = new MLException();
			
		ProductOnSale prodOnSale = this.getProductOnSaleRepository().getLastProductOnSaleById(provider.getId(), product.getId());
		if (prodOnSale != null && prodOnSale.getInitialDate().after(initialDate)) ex.priceValidity();

		if (prodOnSale != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(initialDate);
			cal.add(Calendar.DATE, -1);
			prodOnSale.setFinalDate(cal.getTime());
			//this.getProductOnSaleRepository().update(prodOnSale);
		}
		
		ProductOnSale newProductOnSale = new ProductOnSale(product, provider, price, initialDate);
		product.getProductsOnSale().add(newProductOnSale);
		this.getProductOnSaleRepository().save(newProductOnSale);
			
		return newProductOnSale; } 
	
	@Override
	public Purchase createPurchase(ProductOnSale productOnSale, Integer quantity, User client, DeliveryMethod deliveryMethod,
			PaymentMethod paymentMethod, String address, Float coordX, Float coordY, Date dateOfPurchase) throws MLException {
		MLException ex = new MLException();
		
		Float totalWeight = productOnSale.getProduct().getWeight() * quantity;
		if (totalWeight < deliveryMethod.getStartWeight() || totalWeight > deliveryMethod.getEndWeight()) ex.deliveryMethodInvalid();
		
		Purchase newPurchase = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
		this.getPurchaseRepository().save(newPurchase);
		
		return newPurchase;} 
	
	@Override
	public Optional<User> getUserByEmail(String email) { 
		User user = this.getUserRepository().findByEmail(email);
		Optional<User> opt = Optional.ofNullable(user);
		
		return opt;
	} 

	@Override
	public Optional<Provider> getProviderByCuit(long cuit) {
		Provider provider = this.getProviderRepository().findByCuit(cuit);
		Optional<Provider> opt = Optional.ofNullable(provider);
		
		return opt;
	}
	
	@Override
	public Optional<Category> getCategoryByName(String name) { 
		Category category = this.getCategoryRepository().findByName(name);
		Optional<Category> opt = Optional.ofNullable(category);
		
		return opt;
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		Product product = this.getProductRepository().findByName(name);
		Optional<Product> opt = Optional.ofNullable(product);
		
		return opt;
	}
	
	@Override
	public ProductOnSale getProductOnSaleById(Long id) {
		ProductOnSale productOnSale = this.getProductOnSaleRepository().findByIdentificador(id);
		
		return productOnSale;
	}
	
	@Override
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		Page<DeliveryMethod> deliveryMethod = this.getDeliveryMethodRepository().findByName(name, PageRequest.of(0, 1));
		Optional<DeliveryMethod> opt = Optional.ofNullable(deliveryMethod.getContent().get(0));
		
		return opt;
	}
	
	@Override
	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		CreditCardPayment creditCardPayment =  this.getCreditCardPaymentRepository().findByName(name);
		Optional<CreditCardPayment> opt = Optional.ofNullable(creditCardPayment);
		
		return opt;
	}
	
	@Override
	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		OnDeliveryPayment onDeliveryPayment = this.getOnDeliveryPaymentRepository().findByName(name);
		Optional<OnDeliveryPayment> opt = Optional.ofNullable(onDeliveryPayment);
		
		return opt;
	}
	
	@Override
	public Optional<Purchase> getPurchaseById(Long id) {
		Optional<Purchase> purchase = this.getPurchaseRepository().findById(id);
		
		return purchase;
	}
	
	@Override
	public List<Purchase> getAllPurchasesMadeByUser(String username) {
		return null;
	}

	@Override
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {return null;}

	@Override
	public List<User> getUsersSpendingMoreThan(Float amount) {
		List<User> result = this.getUserRepository().getUsersSpendingMoreThan(amount);
		
		return result;
	}

	@Override
	public List<Provider> getTopNProvidersInPurchases(int n) {return null;}

	@Override
	public List<Product> getTop3MoreExpensiveProducts() {return null;}

	@Override
	public List<User> getTopNUsersMorePurchase(int n) {
		Page<User> result = this.getUserRepository().getTopNUsersMorePurchase(n, PageRequest.of(0, n));
		
		return result.getContent();
	}

	@Override
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {return null;}

	@Override
	public List<Product> getProductForCategory (Category category) {return null;}

	@Override
	public List<Purchase> getPurchasesForProvider(Long cuit) {
		return this.getProviderRepository().getPurchasesForProvider(cuit);
	}

	@Override
	public Product getBestSellingProduct() {return null;}

	@Override
	public List<Product> getProductsOnePrice() {return null;}

	@Override
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice() {
		return this.getProductRepository().getProductWithMoreThan20percentDiferenceInPrice();
	}

	@Override
	public Provider getProviderLessExpensiveProduct() {return null;}

	@Override
	public List<Provider> getProvidersDoNotSellOn(Date day) {return null;}

	@Override
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		return this.getProductOnSaleRepository().getSoldProductsOn(day);
	}

	@Override
	public List<Product> getProductsNotSold() {return null;}

	@Override
	public DeliveryMethod getMostUsedDeliveryMethod() {return null;}

	@Override
	public OnDeliveryPayment getMoreChangeOnDeliveryMethod() {
		Page<OnDeliveryPayment> result = this.getOnDeliveryPaymentRepository().getMoreChangeOnDeliveryMethod(PageRequest.of(0, 1));
		
		return result.getContent().get(0);
	}

	@Override
	public Product getHeaviestProduct() {return null;}

	@Override
	public Category getCategoryWithLessProducts() {return null;}	
}