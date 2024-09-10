package com.lcwd.MenuService.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcepionHandler {

	@ExceptionHandler(MenuNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handlerMenuNotFoundException(MenuNotFoundException me){
		String message=me.getMessage();
		Map<String,Object> m=new HashMap<>();
		m.put("message", message);
		m.put("status", HttpStatus.NOT_FOUND);
		m.put("Success", false);
		return new ResponseEntity<Map<String,Object>>(m,HttpStatus.NOT_FOUND);
	}
}
