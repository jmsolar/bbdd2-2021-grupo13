package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class ProductException extends MLException {
	
	public void nameRequired() throws MLException {
		throw new MLException("Product name is required");
	}
	
	public void weightRequired() throws MLException {
		throw new MLException("Product weight is required");
	}
	
	public void productExist() throws MLException {
		throw new MLException("Product name is already exist");
	}
}