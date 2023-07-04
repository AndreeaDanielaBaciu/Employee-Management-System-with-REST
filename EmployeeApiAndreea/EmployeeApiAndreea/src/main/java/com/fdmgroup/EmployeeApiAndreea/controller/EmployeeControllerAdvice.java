package com.fdmgroup.EmployeeApiAndreea.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fdmgroup.EmployeeApiAndreea.exception.ResourceNotFoundException;

@RestControllerAdvice
public class EmployeeControllerAdvice {
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity
		.status(HttpStatus.NOT_FOUND)
		.body(ex.getMessage());
	}

}
