package com.fdmgroup.EmployeeUIAndreea.service;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.EmployeeUIAndreea.exception.WebClientExceptionFilter;
import com.fdmgroup.EmployeeUIAndreea.model.Employee;



@Service
public class WebClientEmployeeService {
	private static final String BASE_URL = "http://localhost:8088/api/v1/employees";
	private final WebClient webClient;
	
	public WebClientEmployeeService(WebClient.Builder builder) {
		super();
		this.webClient = builder
				.baseUrl(BASE_URL)
				.filter(ExchangeFilterFunction.ofResponseProcessor(WebClientExceptionFilter::filterFunction))
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	
	public List<Employee> getAllEmployees() {
		return webClient.get() // everything before the .retrieve() defines the request
				.retrieve()	   // everything after the .retrieve() defines how we want to process the request
				.bodyToFlux(Employee.class) // represents 1..n potential values
				.collectList()
				.block();
	}
	
	public Employee createEmployee(Employee employee) {
		return webClient.post()
				.bodyValue(employee)
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}
	
	public Employee getById(int id) {
		return webClient.get()
				.uri(builder -> builder.path("/{id}").build(id))
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}
	
	public Employee updateEmployee(int id, Employee employee) {
		return webClient.put()
				.uri(builder -> builder.path("/{id}").build(id))
				.bodyValue(employee)
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}
	
	public void deleteEmployeeById(int id) {
		webClient.delete()
			.uri(builder -> builder.path("/{id}").build(id))
			.retrieve()
			.toBodilessEntity()
			.block();
	}
}
