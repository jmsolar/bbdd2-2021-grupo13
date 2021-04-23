package ar.edu.unlp.info.bd2.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BD2_PROVIDER")
public class Provider {
	@GeneratedValue
	@Column(name = "id_provider")
	@Id
	@OneToMany(mappedBy="productsOnSale")
	
	public int Id;
	public String name;
	public Long cuit;
	public List<ProductOnSale> productsOnSale = new ArrayList<ProductOnSale>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCuit() {
		return cuit;
	}

	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	public Provider(String name, Long cuit) {
		this.name = name;
		this.cuit = cuit;
	}
	
	public List<ProductOnSale> getProductsOnSale() {
		return productsOnSale;
	}

	public void setProductsOnSale(List<ProductOnSale> productsOnSale) {
		this.productsOnSale = productsOnSale;
	}
}