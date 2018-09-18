package org.develop.app.batch.exceptions;

public class NoJobDefException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message = "The job requested is not defined";
	
	public NoJobDefException() {}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
