package com.fdmgroup.EmployeeUIAndreea.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fdmgroup.EmployeeUIAndreea.exception.GenericEmployeeException;
import com.fdmgroup.EmployeeUIAndreea.exception.InvalidEmployeeException;

import feign.FeignException;


@ControllerAdvice
public class EmployeeControllerAdvice {
	
	@ExceptionHandler(GenericEmployeeException.class)
	public String handleGenericEmployeeException(GenericEmployeeException ex, Model model) {
		model.addAttribute("errorCode", ex.status);
		model.addAttribute("errorMessage", ex.getMessage());
		return "error-page";
	}
	
	
	@ExceptionHandler(InvalidEmployeeException.class)
	public String handleInvalidBookException(InvalidEmployeeException ex, Model model) {
		model.addAttribute("errorMessages", ex.getValidationErrorMessage());
		return "forward:/addBook";
	}
	
	@ExceptionHandler(FeignException.NotFound.class)
    public String handleNotFoundException(Model model, FeignException.NotFound ex) {
        model.addAttribute("errorMessage", "Employee not found");
        return "not-found";
    }

}
