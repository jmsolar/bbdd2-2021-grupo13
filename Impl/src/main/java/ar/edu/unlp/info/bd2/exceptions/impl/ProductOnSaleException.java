package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class ProductOnSaleException extends MLException {
	public void priceRequired() throws MLException {
		throw new MLException("Product on sale price is required");
	}
	
	public void initialDateRequired() throws MLException {
		throw new MLException("Product on sale initial date is required");
	}
}