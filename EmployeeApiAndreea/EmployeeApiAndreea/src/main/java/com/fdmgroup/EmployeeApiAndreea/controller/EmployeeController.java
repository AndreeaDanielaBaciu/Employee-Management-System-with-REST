package com.fdmgroup.EmployeeApiAndreea.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.EmployeeApiAndreea.model.Employee;
import com.fdmgroup.EmployeeApiAndreea.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("api/v1/employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	/**
	 * Retrieves all employees.
	 *
	 * @return the ResponseEntity containing the list of all employees
	 */
	@Operation(
		summary = "Retrieves all employees",
		description = "This API endpoint retrieves all the employees.",
		method = "GET",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
			)
		}
	)
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		logger.info("Entering getEmployees() method");
		List<Employee> employees = employeeService.findAll();
		logger.info("Exiting getEmployees() method");
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}

	/**
	 * Retrieves an employee by their ID.
	 *
	 * @param id the ID of the employee to retrieve
	 * @return the ResponseEntity containing the employee with the specified ID
	 */
	@Operation(
		summary = "Retrieves an employee by ID",
		description = "This API endpoint retrieves an employee by their ID.",
		method = "GET",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
			),
			@ApiResponse(
				responseCode = "404",
				description = "Employee not found"
			)
		}
	)
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
		logger.info("Entering getEmployee() method with id: {}", id);
		Employee employee = employeeService.findById(id);
		logger.info("Exiting getEmployee() method");
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	/**
	 * Adds a new employee.
	 *
	 * @param employee the employee to add
	 * @return the ResponseEntity containing the added employee
	 */
	@Operation(
		summary = "Adds a new employee",
		description = "This API endpoint adds a new employee.",
		method = "POST",
		responses = {
			@ApiResponse(
				responseCode = "201",
				description = "Employee created successfully",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
			),
			@ApiResponse(
				responseCode = "400",
				description = "Bad request"
			)
		}
	)
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		logger.info("Entering addEmployee() method");
		Employee addedEmployee = employeeService.addEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(addedEmployee.getId()).toUri();
		logger.info("Exiting addEmployee() method");
		return ResponseEntity.created(location).body(addedEmployee);
	}

	/**
	 * Edits an existing employee.
	 *
	 * @param id       the ID of the employee to edit
	 * @param employee the updated employee information
	 * @return the ResponseEntity containing the updated employee
	 */
	@Operation(
		summary = "Edits an existing employee",
		description = "This API endpoint edits an existing employee.",
		method = "PUT",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Employee updated successfully",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
			),
			@ApiResponse(
				responseCode = "400",
				description = "Bad request"
			),
			@ApiResponse(
				responseCode = "404",
				description = "Employee not found"
			)
		}
	)
	@PutMapping("/{id}")
	public ResponseEntity<Employee> editEmployee(@PathVariable long id, @Valid @RequestBody Employee employee) {
		logger.info("Entering editEmployee() method with id: {}", id);
		Employee updatedEmployee = employeeService.updateEmployee(employee, id);
		logger.info("Exiting editEmployee() method");
		return ResponseEntity.ok(updatedEmployee);
	}

	/**
	 * Deletes an employee by their ID.
	 *
	 * @param id the ID of the employee to delete
	 * @return the ResponseEntity with no content
	 */
	@Operation(
		summary = "Deletes an employee by ID",
		description = "This API endpoint deletes an employee by their ID.",
		method = "DELETE",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Employee deleted successfully"
			),
			@ApiResponse(
				responseCode = "404",
				description = "Employee not found"
			)
		}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
		logger.info("Entering deleteEmployee() method with id: {}", id);
		employeeService.deleteById(id);
		logger.info("Exiting deleteEmployee() method");
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * Handles MethodArgumentNotValidException.
	 *
	 * @param ex the exception to handle
	 * @return the ResponseEntity containing the error message
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidExeption(MethodArgumentNotValidException ex) {
		logger.error("MethodArgumentNotValidException occurred: {}", ex.getMessage());
		List<ObjectError> errors = ex.getAllErrors();
		StringBuilder sb = new StringBuilder();
		errors.forEach(error -> sb.append(error.getDefaultMessage()).append(","));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
	}
	
	
	
	@GetMapping("/country/{country}")
	public ResponseEntity<String> returnEmployeesByCountry(@PathVariable String country){
		List<Employee> employeesGetByCountry = employeeService.getByCountry(country);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
