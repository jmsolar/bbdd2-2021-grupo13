package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class DeliveryMethodException extends MLException {
	public void nameRequired() throws MLException {
		throw new MLException("Delivery method name is required");
	}
	
	public void costRequired() throws MLException {
		throw new MLException("Delivery method cost is required");
	}
	
	public void startWeightRequired() throws MLException {
		throw new MLException("Delivery method start weight is required");
	}
	
	public void endWeightRequired() throws MLException {
		throw new MLException("Delivery method end weight is required");
	}
	
	public void nameExist() throws MLException {
		throw new MLException("Delivery method name is already exist");
	}
}