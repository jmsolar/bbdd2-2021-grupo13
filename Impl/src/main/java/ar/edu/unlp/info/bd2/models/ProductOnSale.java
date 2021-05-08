package ar.edu.unlp.info.bd2.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_PRODUCT_ON_SALE")
public class ProductOnSale{
	@GeneratedValue
	@Column(name = "id_product_on_sale")
	@Id
	private int Id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_product")
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_provider")
	private Provider provider;
	
	@Column
	private Float price; 
	
	@Column
	private Date initialDate;
	
	@Column
	private Date finalDate;	
	
	@Version
	@Column(name = "version")
	private int version;
	
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
	public Date getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	
	public ProductOnSale(Product product, Provider provider, Float price, Date initialDate) {
		this.product = product;
		this.provider = provider;
		this.price = price;
		this.initialDate = initialDate;
	}
}