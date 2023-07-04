package com.fdmgroup.EmployeeUIAndreea.exception;

import java.util.Arrays;
import java.util.List;

public class InvalidEmployeeException  extends RuntimeException {

	private static final long serialVersionUID = 9107429937010479034L;
	private final List<String> validationErrorMessages;
	
	public InvalidEmployeeException(String cvsErrors) {
		super("Invalid employee sent to API");
		this.validationErrorMessages = Arrays.asList(cvsErrors.split(","));
	}

	public List<String> getValidationErrorMessage(){
		return validationErrorMessages;
	}
}
