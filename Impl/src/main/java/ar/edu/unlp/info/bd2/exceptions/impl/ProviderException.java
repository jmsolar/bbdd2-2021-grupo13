package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class ProviderException extends MLException {
	public void nameRequired() throws MLException {
		throw new MLException("Provider name is required");
	}
	
	public void cuitRequired() throws MLException {
		throw new MLException("Provider cuit is required");
	}
	
	public void cuitExist() throws MLException {
		throw new MLException("Provider cuit is already exist");
	}
}