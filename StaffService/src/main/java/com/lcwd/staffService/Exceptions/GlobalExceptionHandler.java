package com.lcwd.staffService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.staffService.Helper.ExceptionMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StaffNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handlerStaffNotFoundException(StaffNotFoundException st){
		String message=st.getMessage();
		ExceptionMessage response = ExceptionMessage.builder().message(message).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ExceptionMessage>(response,HttpStatus.NOT_FOUND);
	}

}
