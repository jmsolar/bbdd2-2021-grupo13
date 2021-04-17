package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class PurchaseException extends MLException {
	
	public void quantityRequired() throws MLException {
		throw new MLException("Purchase quantity is required");
	}
	
	public void addressRequired() throws MLException {
		throw new MLException("Purchase address is required");
	}
	
	public void coordXRequired() throws MLException {
		throw new MLException("Purchase coordX is required");
	}
	
	public void coordYRequired() throws MLException {
		throw new MLException("Purchase coordY is required");
	}
	
	public void dateOfPurchaseRequired() throws MLException {
		throw new MLException("Purchase date is required");
	}
	
	public void purchaseExist() throws MLException {
		throw new MLException("Purchase is already exist");
	}
}