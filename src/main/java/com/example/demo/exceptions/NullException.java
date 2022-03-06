package com.example.demo.exceptions;

public class NullException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullException(String myMessage) {
		super(myMessage);
	}
	
	public NullException() {
		super();
	}
	

}
