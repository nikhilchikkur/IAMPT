package com.Utilities;

/**
 * This class has the getters and setters for the exception
 * 
 * @author AKaivalliam
 * @version 1.0
 *
 */

@SuppressWarnings("serial")
public class MPException extends Exception {

	private String message = null;

	public MPException() {
		super();
	}

	public MPException(String message) {
		super(message);
		this.message = message;
	}

	public MPException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
