package ar.edu.unlp.info.bd2.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_PRODUCT")

public class Product {
	@GeneratedValue
	@Column(name = "id_product")
	@Id
	public Long Id;
	
	@Column
	public String name;
	
	@Column
	public Float weight;
	
	@OneToOne(mappedBy="category")
	public Category category;
	
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
		return Id;
	}
	public void setId(Long id) {
		this.Id = id;
	}
	/*
	public List<ProductOnSale> getProductsOnSale() {
		// retorna la lista de productsOnSale donde el name del product sea igual al name del objeto llamante
	}
	*/
	
	public Product(String name, Float weight, Category category) {
		this.name = name;
		this.weight = weight;
		this.category = category;
	}
}