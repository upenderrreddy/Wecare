package com.infy.wecare.exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.wecare.dto.ErrorMessage;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	Environment environment;
	
	@ExceptionHandler(value = Exception.class)
	String exceptionHandler(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(value = WecareException.class)
	ResponseEntity<ErrorMessage> exceptionHandler(WecareException e){
		ErrorMessage errorMessage =  new ErrorMessage(e.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.OK);
	}

}
