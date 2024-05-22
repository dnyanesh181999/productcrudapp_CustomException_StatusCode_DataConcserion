package com.example.demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ProductNotSavedException.class)
	public ResponseEntity<APIError>ProductNotSavedExceptionHandler(ProductNotSavedException e,HttpServletRequest request){
		APIError error = new APIError();
		error.setDate(new Date());
		error.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		error.setHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<APIError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(NoDataPresentException.class)
	public ResponseEntity<APIError>NoDataPresentExceptionHandler(NoDataPresentException e,HttpServletRequest request){
		APIError error = new APIError(HttpStatus.NOT_FOUND.value(), e.getMessage(), new Date(), request.getRequestURI(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<APIError>(error,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<APIError>NoProductFoundExceptionHandler(NoProductFoundException e,HttpServletRequest request){
		APIError error = new APIError();
		error.setDate(new Date());
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		error.setHttpMessage(HttpStatus.NOT_FOUND);
		error.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<APIError>(error,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
		Map<String, String>error = new HashMap<String, String>();
		e.getBindingResult().getAllErrors().forEach((er)->{
			String fieldName=((FieldError)er).getField();
			String msg=er.getDefaultMessage();
			error.put(fieldName, msg);
		});
		
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.BAD_REQUEST);
	}
	
}
