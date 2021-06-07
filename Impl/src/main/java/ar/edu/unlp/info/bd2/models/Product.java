package ar.edu.unlp.info.bd2.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "BD2_PRODUCT")
public class Product {
	@GeneratedValue
	@Column(name = "id_product")
	@Id
	public Long id;
	
	@Column
	private String name;
	
	@Column
	private Float weight;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER , cascade = CascadeType.REFRESH, orphanRemoval = true)
	private Set<ProductOnSale> productOnSale;
	
	@Version
	@Column(name = "version")
	private int version;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<ProductOnSale> getProductsOnSale() {
		return productOnSale;
	}
	public void setProductsOnSale(Set<ProductOnSale> productOnSale) {
		this.productOnSale = productOnSale;
	}
	
	public Product() {}
	
	public Product(String name, Float weight, Category category) {
		this.name = name;
		this.weight = weight;
		this.category = category;
		this.productOnSale = new HashSet<ProductOnSale>();
	}
}