package com.publishment_management.backend.exceptions;


public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public BaseException() {
		
	}
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.prepareErrorMessage());
	}
	
}
