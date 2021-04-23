package ar.edu.unlp.info.bd2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BD2_ON_DELIVERY_PAYMENT")
public class OnDeliveryPayment extends PaymentMethod{
	@GeneratedValue
	@Column(name = "id_on_delivery_payment")
	@Id
	
	public Float promisedAmount;

	public Float getPromisedAmount() {
		return promisedAmount;
	}

	public void setPromisedAmount(Float promisedAmount) {
		this.promisedAmount = promisedAmount;
	}
	
	public OnDeliveryPayment(String name, Float promisedAmount) {
		super(name);
		this.promisedAmount = promisedAmount;
	}
}