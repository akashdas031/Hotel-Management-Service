package com.lcwd.ItemService.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handlerItemNotFound(ItemNotFoundException it){
		String message=it.getMessage();
		Map<String,Object> m=new HashMap<>();
		m.put("message", message);
		m.put("status", HttpStatus.NOT_FOUND);
		m.put("isSuccess", false);
		return new ResponseEntity<Map<String,Object>>(m,HttpStatus.NOT_FOUND);
	}
}
