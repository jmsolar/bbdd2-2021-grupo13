package ar.edu.unlp.info.bd2.exceptions;

public class MLException extends Throwable {
	public String message;
	
	public MLException() {}
	
	public MLException(String string) {
		this.message = string;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public void categoryNotFound() throws MLException {
		throw new MLException("Category not found");
	}
	
	public void productNotFound() throws MLException {
		throw new MLException("Product doesn't exists");
	}
	
	public void userNotFound() throws MLException {
		throw new MLException("User doesn't exists");
	}
	
	public void providerNoyFound() throws MLException {
		throw new MLException("Provider doesn't exists");
	}
	
	public void deliveryMethodNotFound() throws MLException {
		throw new MLException("Delivery Method doesn't exists");
	}
	
	public void creditCardNotFound() throws MLException {
		throw new MLException("Credit Card Payment doesn't exists");
	}
	
	public void onDeliveryPaymentNotFound() throws MLException {
		throw new MLException("On Delivery Payment doesn't exists");
	}
	
	public void priceValidity() throws MLException {
		throw new MLException("Ya existe un precio para el producto con fecha de inicio de vigencia posterior a la fecha de inicio dada");
	}
	
	public void deliveryMethodInvalid() throws MLException {
		throw new MLException("método de delivery no válido");
	}
	
	public void constraintViolation() throws MLException {
		throw new MLException("Constraint Violation");
	}
}