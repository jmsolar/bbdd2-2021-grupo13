package ar.edu.unlp.info.bd2.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BD2_PAYMENT_METHOD")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.INTEGER)
public class PaymentMethod {
	@GeneratedValue
	@Column(name = "id_payment_method")
	@Id
	public int Id;
	
	@Column
	public String name;
	
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
	
	public PaymentMethod(String name) {
		this.name = name;
	}
}