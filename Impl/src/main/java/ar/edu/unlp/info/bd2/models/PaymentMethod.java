package ar.edu.unlp.info.bd2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BD2_PAYMENT_METHOD")
public class PaymentMethod {
	@GeneratedValue
	@Column(name = "id_payment_method")
	@Id
	
	public String name;
	public int Id;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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