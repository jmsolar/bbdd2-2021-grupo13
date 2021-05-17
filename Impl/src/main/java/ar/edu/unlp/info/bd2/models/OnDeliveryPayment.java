package ar.edu.unlp.info.bd2.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class OnDeliveryPayment extends PaymentMethod{
	@Column
	public Float promisedAmount;

	public Float getPromisedAmount() {
		return promisedAmount;
	}

	public void setPromisedAmount(Float promisedAmount) {
		this.promisedAmount = promisedAmount;
	}
	
	public OnDeliveryPayment() {}
	
	public OnDeliveryPayment(String name, Float promisedAmount) {
		super(name);
		this.promisedAmount = promisedAmount;
	}
}