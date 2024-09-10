package com.lcwd.ItemService.Exceptions;

public class ItemNotFoundException extends RuntimeException{
	
	public ItemNotFoundException(String message) {
		super(message);
	}
	public ItemNotFoundException() {
		super("Item not available on server...");
	}

}
