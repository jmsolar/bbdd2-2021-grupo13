package ar.edu.unlp.info.bd2.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import ar.edu.unlp.info.bd2.exceptions.MLException;
import ar.edu.unlp.info.bd2.models.Category;
import ar.edu.unlp.info.bd2.models.CreditCardPayment;
import ar.edu.unlp.info.bd2.models.DeliveryMethod;
import ar.edu.unlp.info.bd2.models.OnDeliveryPayment;
import ar.edu.unlp.info.bd2.models.PaymentMethod;
import ar.edu.unlp.info.bd2.models.Product;
import ar.edu.unlp.info.bd2.models.ProductOnSale;
import ar.edu.unlp.info.bd2.models.Provider;
import ar.edu.unlp.info.bd2.models.Purchase;
import ar.edu.unlp.info.bd2.models.User;
import ar.edu.unlp.info.bd2.repositories.elasticSearch.ESRepository;
import ar.edu.unlp.info.bd2.services.*;

public class ESServiceImpl implements ESService {
	public ESRepository repository;
	
	public ESRepository getRepository() {
		return repository;
	}
	public void setRepository(ESRepository repository) {
		this.repository = repository;
	}
	
	public ESServiceImpl(ESRepository repository) {
		this.repository = repository;
	}
	@Override
	public List<Purchase> getAllPurchasesMadeByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getUsersSpendingMoreThan(Float amount) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Provider> getTopNProvidersInPurchases(int n) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Product> getTop3MoreExpensiveProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getTopNUsersMorePurchase(int n) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Product> getProductForCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Purchase> getPurchasesForProvider(Long cuit) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Product getBestSellingProduct() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Product> getProductsOnePrice() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Product> getProductWithMoreThan20percentDiferenceInPrice() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Provider getProviderLessExpensiveProduct() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Provider> getProvidersDoNotSellOn(Date day) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ProductOnSale> getSoldProductsOn(Date day) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Product> getProductsNotSold() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DeliveryMethod getMostUsedDeliveryMethod() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OnDeliveryPayment getMoreChangeOnDeliveryMethod() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Product getHeaviestProduct() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Category getCategoryWithLessProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Category createCategory(String name) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Product createProduct(String name, Float weight, Category category) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User createUser(String email, String fullname, String password, Date dayOfBirth) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Provider createProvider(String name, Long cuit) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DeliveryMethod createDeliveryMethod(String name, Float cost, Float startWeight, Float endWeight)
			throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CreditCardPayment createCreditCardPayment(String name, String brand, Long number, Date expiry, Integer cvv,
			String owner) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OnDeliveryPayment createOnDeliveryPayment(String name, Float promisedAmount) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProductOnSale createProductOnSale(Product product, Provider provider, Float price, Date initialDate)
			throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Purchase createPurchase(ProductOnSale productOnSale, Integer quantity, User client,
			DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, String address, Float coordX, Float coordY,
			Date dateOfPurchase) throws MLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Provider> getProviderByCuit(long cuit) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Category> getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProductOnSale getProductOnSaleById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<CreditCardPayment> getCreditCardPaymentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<OnDeliveryPayment> getOnDeliveryPaymentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Purchase> getPurchaseById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}