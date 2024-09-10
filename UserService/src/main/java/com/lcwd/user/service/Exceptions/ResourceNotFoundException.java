package com.lcwd.user.service.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException() {
		super("Resource not found on service !!");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
	

}
