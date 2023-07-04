package com.fdmgroup.EmployeeUIAndreea.service;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.EmployeeUIAndreea.model.Employee;



@FeignClient(name = "EMPLOYEE-API", path = "api/v1/employees")
@LoadBalancerClient(name = "EMPLOYEE-API")
public interface FeignClientEmployeeService {

	@GetMapping
	public List<Employee> getAllEmployees();

	@GetMapping("/{id}")
	public Employee getById(@PathVariable(value = "id") long id);

	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee);

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") long id, @RequestBody Employee employee);

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable(value = "id") long id);

	@GetMapping("/search-employees")
	List<Employee> findByFullNameContainsIgnoreCase(String searchTerm);
	
	
}