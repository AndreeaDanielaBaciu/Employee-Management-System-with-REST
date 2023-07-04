package com.fdmgroup.EmployeeApiAndreea.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fdmgroup.EmployeeApiAndreea.exception.ResourceNotFoundException;
import com.fdmgroup.EmployeeApiAndreea.model.Employee;
import com.fdmgroup.EmployeeApiAndreea.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepo;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	public EmployeeService(EmployeeRepository employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	/**
	 * Retrieves all employees.
	 *
	 * @return the list of all employees
	 */
	public List<Employee> findAll() {
		logger.info("Entering findAll() method");
		List<Employee> employees = employeeRepo.findAll();
		logger.info("Exiting findAll() method");
		return employees;
	}

	/**
	 * Retrieves an employee by their ID.
	 *
	 * @param id the ID of the employee to retrieve
	 * @return the employee with the specified ID
	 * @throws ResourceNotFoundException if no employee with the given ID is found
	 */
	public Employee findById(long id) {
		logger.info("Entering findById() method with id: {}", id);
		Optional<Employee> employeeOpt = employeeRepo.findById(id);
		if (employeeOpt.isEmpty()) {
			throw new ResourceNotFoundException("Employee with id: " + id + " not found.");
		}
		logger.info("Exiting findById() method");
		return employeeOpt.get();
	}

	/**
	 * Adds a new employee.
	 *
	 * @param employee the employee to add
	 * @return the added employee
	 */
	public Employee addEmployee(Employee employee) {
		logger.info("Entering addEmployee() method");
		Employee addedEmployee = employeeRepo.save(employee);
		logger.info("Exiting addEmployee() method");
		return addedEmployee;
	}

	/**
	 * Deletes an employee by their ID.
	 *
	 * @param id the ID of the employee to delete
	 * @throws ResourceNotFoundException if no employee with the given ID is found
	 */
	public void deleteById(long id) {
		logger.info("Entering deleteById() method with id: {}", id);
		if (!employeeRepo.existsById(id)) {
			throw new ResourceNotFoundException("Employee with id: " + id + " not found.");
		}
		employeeRepo.deleteById(id);
		logger.info("Exiting deleteById() method");
	}

	/**
	 * Updates an existing employee.
	 *
	 * @param employee the updated employee information
	 * @param id       the ID of the employee to update
	 * @return the updated employee
	 * @throws ResourceNotFoundException if no employee with the given ID is found
	 */
	public Employee updateEmployee(Employee employee, long id) {
		logger.info("Entering updateEmployee() method with id: {}", id);
		if (!employeeRepo.existsById(id)) {
			throw new ResourceNotFoundException("Employee with id: " + id + " not found.");
		}
		employee.setId(id);
		Employee updatedEmployee = employeeRepo.save(employee);
		logger.info("Exiting updateEmployee() method");
		return updatedEmployee;
	}

	public List<Employee> getByCountry(String country) {	
		return employeeRepo.findByCountry(country);
		 
	}
}
