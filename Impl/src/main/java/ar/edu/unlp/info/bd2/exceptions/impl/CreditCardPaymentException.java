package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class CreditCardPaymentException extends MLException {
	public void nameRequired() throws MLException {
		throw new MLException("Credit card name is required");
	}
	
	public void brandRequired() throws MLException {
		throw new MLException("Credit card brand is required");
	}
	
	public void numberRequired() throws MLException {
		throw new MLException("Credit card number is required");
	}
	
	public void expiryRequired() throws MLException {
		throw new MLException("Credit card expiry is required");
	}
	
	public void cvvRequired() throws MLException {
		throw new MLException("Credit card cvv is required");
	}
	
	public void ownerRequired() throws MLException {
		throw new MLException("Credit card owner is required");
	}
	
	public void nameExist() throws MLException {
		throw new MLException("Credit card name is already exist");
	}
}