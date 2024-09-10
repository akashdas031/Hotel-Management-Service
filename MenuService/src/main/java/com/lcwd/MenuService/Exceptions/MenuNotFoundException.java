package com.lcwd.MenuService.Exceptions;

public class MenuNotFoundException extends RuntimeException{

	public MenuNotFoundException(String message) {
		super(message);
	}
	public MenuNotFoundException() {
		super("Menu with given id not available on the server...");
	}
}
