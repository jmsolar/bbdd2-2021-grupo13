package ar.edu.unlp.info.bd2.exceptions.impl;

import ar.edu.unlp.info.bd2.exceptions.MLException;

public class UserException extends MLException {
	
	public void emailRequired() throws MLException {
		throw new MLException("User email is required");
	}
	
	public void fullnameRequired() throws MLException {
		throw new MLException("User fullname is required");
	}
	
	public void passwordRequired() throws MLException {
		throw new MLException("User password is required");
	}
	
	public void dayOfBirthRequired() throws MLException {
		throw new MLException("User dayOfBirth is required");
	}
	
	public void emailExist() throws MLException {
		throw new MLException("Constraint Violation");
	}
}