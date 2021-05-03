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
}