package com.example.demo.exceptions;

public class InvalidateShoppingItemException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidateShoppingItemException(String myMessage) {
		super(myMessage);
	}
	
	public InvalidateShoppingItemException() {
		super();
	}

}
