package com.cg.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {
   
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<Object> exception(EmployeeNotFoundException exception) {
      return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
   }
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> exception(BadRequestException exception) {
      return new ResponseEntity<>("Requeted data is invalid", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
      return new ResponseEntity<>("Exeption occured while processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}