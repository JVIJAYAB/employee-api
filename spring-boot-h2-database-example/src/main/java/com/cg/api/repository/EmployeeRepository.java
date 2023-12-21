package com.cg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.api.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByFirstName(String fristName);
	
	List<Employee> findByLastName(String lastName);	
	
	List<Employee> findBySalaryGreaterThanEqual(Long salary);
	
	List<Employee> findByEmail(String email);
	
	List<Employee> findByPhone(Long phone);

}
