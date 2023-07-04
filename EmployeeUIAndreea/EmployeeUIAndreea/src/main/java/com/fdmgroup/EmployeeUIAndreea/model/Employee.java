package com.fdmgroup.EmployeeUIAndreea.model;

import java.math.BigDecimal;

public class Employee {

	// Attributes
	private long id;
	private String firstName;
	private String lastName;
	private BigDecimal salary;
	private String province;
	private String country;

	// Constructors
	public Employee(long id, String firstName, String lastName, BigDecimal salary, String province, String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.province = province;
		this.country = country;
	}

	public Employee() {
		super();
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
