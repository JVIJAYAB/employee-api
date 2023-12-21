package com.cg.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.api.dto.EmployeeDTO;
import com.cg.api.service.EmployeeService;
import com.cg.api.util.EmployeeResponse;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<EmployeeResponse> getAllEmployee() {
		EmployeeResponse  response =employeeService.getAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/search")
	public ResponseEntity<EmployeeResponse> search(@RequestParam(required = false) Long empId,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
		Map<String,Object> map = new HashMap<>();
		if (empId != null) {
			map.put("empId", empId);
		} else if (firstName != null) {
			map.put("firstName", firstName);
		} else if (lastName != null) {
			map.put("lastName", lastName);
		} 
		EmployeeResponse response = employeeService.getEmployeeByKey(map);
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/getBySal/{sal}")
	public  ResponseEntity<EmployeeResponse> getBySal(@PathVariable("sal") Long sal) {
		EmployeeResponse response = new EmployeeResponse();
		if(sal>0) {
		 response = employeeService.getBySal(sal);
		} else {
			response.setMessage(null);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(response); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable("id") Long id) {
		EmployeeResponse response = employeeService.delete(id);
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeDTO Employee) {
		EmployeeResponse response = employeeService.save(Employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping
	public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeDTO Employee) {
		EmployeeResponse response = employeeService.update(Employee);
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
