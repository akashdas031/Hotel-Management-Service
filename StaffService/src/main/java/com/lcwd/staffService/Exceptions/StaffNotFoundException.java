package com.lcwd.staffService.Exceptions;

public class StaffNotFoundException extends RuntimeException{

	public StaffNotFoundException(String message) {
		super(message);
	}
	
	public StaffNotFoundException() {
		super("Staff Not found...please check your credentials...!!!");
	}
}
