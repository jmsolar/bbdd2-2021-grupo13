package ar.edu.unlp.info.bd2.models;

public class PaymentMethod {
	public String name;

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