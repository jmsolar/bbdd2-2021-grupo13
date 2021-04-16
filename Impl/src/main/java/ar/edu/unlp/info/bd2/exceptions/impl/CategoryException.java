package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class CategoryException extends MLException {
	
	public void nameRequired() throws MLException {
		throw new MLException("Category name is required");
	}
	
	public void categoryNotFound() throws MLException {
		throw new MLException("Category not found");
	}
	
	public void categoryExist() throws MLException {
		throw new MLException("Category already exist");
	}
}