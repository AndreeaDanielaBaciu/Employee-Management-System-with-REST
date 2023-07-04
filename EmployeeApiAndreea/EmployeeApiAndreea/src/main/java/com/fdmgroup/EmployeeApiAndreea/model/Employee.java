package com.fdmgroup.EmployeeApiAndreea.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeegen")
	@SequenceGenerator(name = "employeegen", sequenceName = "EMPLOYEE_ID_SEQ", allocationSize = 1)
	private long id;

	@NotBlank(message = "First name must not be null or blank.")
	@Size(min = 3, max = 250, message = "First name must be longer than 2 characters and less than 250.")
	private String firstName;

	@NotBlank(message = "Last name must not be null or blank.")
	@Size(min = 2, max = 250, message = "Last name must be longer than 2 characters and less than 250.")
	private String lastName;

	
	@DecimalMax(value = "500000", message = "Salary must be less than 500,000")
	@DecimalMin(value = "20.500", message = "Salary must be more than 20,500.")
	private BigDecimal salary;

	@NotBlank(message = "Province must not be null or blank.")
	@Size(min = 2, max = 250, message = "Province must be longer than 2 characters and less than 250.")
	private String province;

	@NotBlank(message = "Country must not be null or blank.")
	@Size(min = 2, max = 250, message = "Country must be longer than 2 characters and less than 250.")
	private String country;

	// Constructors
	public Employee() {
		super();
	}

	public Employee(long id,
			@NotBlank(message = "First name must not be null or blank.") @Size(min = 3, max = 250, message = "First name must be longer than 2 characters and less than 250.") String firstName,
			@NotBlank(message = "Last name must not be null or blank.") @Size(min = 2, max = 250, message = "Last name must be longer than 2 characters and less than 250.") String lastName,
			@NotBlank(message = "Salary must not be null or blank.") @DecimalMax(value = "500000", message = "Salary must be less than 500,000") @DecimalMin(value = "20,500", message = "Salary must be more than 20,500.") BigDecimal salary,
			@NotBlank(message = "Province must not be null or blank.") @Size(min = 2, max = 250, message = "Province must be longer than 2 characters and less than 250.") String province,
			@NotBlank(message = "Country must not be null or blank.") @Size(min = 2, max = 250, message = "Country must be longer than 2 characters and less than 250.") String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.province = province;
		this.country = country;
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

	// Override methods
	@Override
	public int hashCode() {
		return Objects.hash(country, firstName, id, lastName, province, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(country, other.country) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(province, other.province)
				&& Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
				+ ", province=" + province + ", country=" + country + "]";
	}

}
