package com.fdmgroup.EmployeeUIAndreea.exception;

public class GenericEmployeeException extends RuntimeException {

	private static final long serialVersionUID = 5979052192444415380L;
	public final String status;

	public GenericEmployeeException(String message, String status) {
		super(message);
		this.status = status;
	}
}
