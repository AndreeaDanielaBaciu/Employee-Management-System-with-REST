package com.fdmgroup.EmployeeApiAndreea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.EmployeeApiAndreea.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByCountry(String country);

}
