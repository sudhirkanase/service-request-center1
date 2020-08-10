package com.wellsfargo.srca.task_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SrcaExceptionHandler {
	
	@ExceptionHandler(value = DataNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleGenericDataNotFoundException(DataNotFoundException e) {
		ErrorDetails error = new ErrorDetails("NOT_FOUND_ERROR", e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = SrcaException.class)
	public ResponseEntity<ErrorDetails> handleSRCAException(SrcaException e) {
		ErrorDetails error = new ErrorDetails("FIELD_REQUIRED_ERROR", e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
