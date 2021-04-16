package ar.edu.unlp.info.bd2.exceptions;

public class MLException extends Throwable {
	public String string;
	
	public MLException() {}
	
	public MLException(String string) {
		this.string = string;
	}
	
}