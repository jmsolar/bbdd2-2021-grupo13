package ar.edu.unlp.info.bd2.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_PAYMENT_METHOD")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class PaymentMethod {
	@GeneratedValue
	@Column(name = "id_payment_method")
	@Id
	private int Id;
	
	@Column
	private String name;
	
	@OneToOne
	@JoinColumn(name = "id_product_on_sale")
	private Purchase purchase;
	
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@Version
	@Column(name = "version")
	private int version;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public PaymentMethod() {}
	
	public PaymentMethod(String name) {
		this.name = name;
	}
}