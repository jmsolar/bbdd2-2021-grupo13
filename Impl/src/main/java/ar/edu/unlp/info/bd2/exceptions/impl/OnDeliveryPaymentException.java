package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class OnDeliveryPaymentException extends MLException {
	
	public void nameRequired() throws MLException {
		throw new MLException("Delivery payment name is required");
	}
	
	public void promisedAmountRequired() throws MLException {
		throw new MLException("Delivery payment promised amount is required");
	}
	
	public void nameExist() throws MLException {
		throw new MLException("Delivery payment name is already exist");
	}
}