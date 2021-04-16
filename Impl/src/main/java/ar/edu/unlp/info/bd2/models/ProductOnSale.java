package ar.edu.unlp.info.bd2.models;

import java.util.Date;

public class ProductOnSale{
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public Product product;
	public Provider provider; 
	public Float price; 
	public Date initialDate;
	
	public ProductOnSale(Product product, Provider provider, Float price, Date initialDate) {
		this.product = product;
		this.provider = provider;
		this.price = price;
		this.initialDate = initialDate;
	}
}