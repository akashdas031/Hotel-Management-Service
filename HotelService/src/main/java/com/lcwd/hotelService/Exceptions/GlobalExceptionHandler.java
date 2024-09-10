package com.lcwd.hotelService.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handlerResourceNotFoundException(ResourceNotFoundException ex){
		String msg=ex.getMessage();
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("status", HttpStatus.NOT_FOUND);
		m.put("success", false);
		m.put("message", msg);
		return new ResponseEntity<Map<String,Object>>(m,HttpStatus.NOT_FOUND);
		
	}
}
