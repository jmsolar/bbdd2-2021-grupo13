package ar.edu.unlp.info.bd2.models;

import java.util.ArrayList;
import java.util.Date;
//Preguntar!!
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BD2_PRODUCT_ON_SALE")
public class ProductOnSale{
	@GeneratedValue
	@Column(name = "id_product_on_sale")
	@Id
	@OneToMany(mappedBy="historicProductsOnSale")
	@OneToOne(mappedBy="provider")
	//@OneToOne(mappedBy="product")
	
	public int Id;
	public Product product;
	public Provider provider; 
	public Float price; 
	public Date initialDate;
	public List<ProductOnSale> historicProductsOnSale = new ArrayList<ProductOnSale>();
	
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
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public List<ProductOnSale> getProductsOnSale() {
		return historicProductsOnSale;
	}	
	public void setHistoricProductsOnSale(List<ProductOnSale> historicProductsOnSale) {
		this.historicProductsOnSale = historicProductsOnSale;
	}
	
	public ProductOnSale(Product product, Provider provider, Float price, Date initialDate) {
		this.product = product;
		this.provider = provider;
		this.price = price;
		this.initialDate = initialDate;
	}
}