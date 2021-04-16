package ar.edu.unlp.info.bd2.models;

public class OnDeliveryPayment extends PaymentMethod{
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