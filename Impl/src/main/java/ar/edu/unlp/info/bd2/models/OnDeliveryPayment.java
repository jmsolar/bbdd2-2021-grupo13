package ar.edu.unlp.info.bd2.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "BD2_ON_DELIVERY_PAYMENT")
@DiscriminatorValue("on_delivery_payment")
public class OnDeliveryPayment extends PaymentMethod{
	@Column
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