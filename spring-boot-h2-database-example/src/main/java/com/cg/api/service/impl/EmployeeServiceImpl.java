package com.cg.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.api.dto.EmployeeDTO;
import com.cg.api.model.Employee;
import com.cg.api.repository.EmployeeRepository;
import com.cg.api.service.EmployeeService;
import com.cg.api.util.EmployeeResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeResponse getAllEmployee() {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeDTO> empDTOList = new ArrayList<>();
		try {
			List<Employee> empList = employeeRepository.findAll();
			empList.stream().forEach(e -> {
				EmployeeDTO emp = new EmployeeDTO();
				emp.setEmpId(e.getEmpId());
				emp.setFirstName(e.getFirstName());
				emp.setLastName(e.getLastName());
				emp.setFullName(e.getFirstName() + " " + e.getLastName());
				emp.setPhone(e.getPhone());
				emp.setSalary(e.getSalary());
				emp.setEmail(e.getEmail());
				emp.setAddress(e.getAddress());
				empDTOList.add(emp);
			});
			response.setMessage("Employee Data Fectched successfully..!!");
			response.setResponse(empDTOList);
		} catch (Exception e) {
			response.setMessage("Exception occurred while fectchinhg the emplyee data..!!");
			response.setResponse(null);
		}
		return response;
	}

	@Override
	public EmployeeResponse getEmployeeByKey(Map<String, Object> map) {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeDTO> empList = new ArrayList<>();
		try {
			if (map.size() > 0) {
				map.keySet().forEach(key -> {
					if ("empId".equalsIgnoreCase(key)) {
						Optional<Employee> employee = employeeRepository.findById((Long) map.get(key));
						Employee e = employee.get();
						EmployeeDTO emp = new EmployeeDTO();
						emp.setEmpId(e.getEmpId());
						emp.setFirstName(e.getFirstName());
						emp.setLastName(e.getLastName());
						emp.setFullName(e.getFirstName() + " " + e.getLastName());
						emp.setPhone(e.getPhone());
						emp.setSalary(e.getSalary());
						emp.setEmail(e.getEmail());
						emp.setAddress(e.getAddress());
						empList.add(emp);
					} else if ("firstName".equalsIgnoreCase(key)) {
						List<Employee> employee = employeeRepository.findByFirstName((String) map.get(key));
						employee.stream().forEach(e -> {
							EmployeeDTO emp = new EmployeeDTO();
							emp.setEmpId(e.getEmpId());
							emp.setFirstName(e.getFirstName());
							emp.setLastName(e.getLastName());
							emp.setFullName(e.getFirstName() + " " + e.getLastName());
							emp.setPhone(e.getPhone());
							emp.setSalary(e.getSalary());
							emp.setEmail(e.getEmail());
							emp.setAddress(e.getAddress());
							empList.add(emp);
						});
					} else if ("lastName".equalsIgnoreCase(key)) {
						List<Employee> employee = employeeRepository.findByLastName((String) map.get(key));
						employee.stream().forEach(e -> {
							EmployeeDTO emp = new EmployeeDTO();
							emp.setEmpId(e.getEmpId());
							emp.setFirstName(e.getFirstName());
							emp.setLastName(e.getLastName());
							emp.setFullName(e.getFirstName() + " " + e.getLastName());
							emp.setPhone(e.getPhone());
							emp.setSalary(e.getSalary());
							emp.setEmail(e.getEmail());
							emp.setAddress(e.getAddress());
							empList.add(emp);
						});
					}

				});
				if (empList.size() > 0) {
					response.setMessage("Employee Data Fectched successfully..!!");
					response.setResponse(empList);
				} else {
					response.setMessage("No employee data found for given criteria..!!");
					response.setResponse(null);
				}
			}
		} catch (Exception e) {
			response.setMessage("Exception occurred while fectchinhg the emplyee data..!!");
			response.setResponse(null);
		}
		return response;
	}

	public EmployeeResponse save(EmployeeDTO dto) {
		EmployeeResponse response = new EmployeeResponse();
		try {
			Employee emp = new Employee();
			emp.setFirstName(dto.getFirstName());
			emp.setLastName(dto.getLastName());
			emp.setPhone(dto.getPhone());
			emp.setSalary(dto.getSalary());
			emp.setAddress(dto.getAddress());
			emp.setEmail(dto.getEmail());
			Employee e = employeeRepository.save(emp);
			response.setMessage("Employee saved successfully..!!");
			response.setResponse("Saved employee id is :" + e.getEmpId());
		} catch (Exception e) {
			response.setMessage("Exception occurred while saving the employee..!!");
			response.setResponse(null);
		}
		return response;
	}

	public EmployeeResponse delete(Long id) {
		EmployeeResponse response = new EmployeeResponse();
		try {
			employeeRepository.deleteById(id);
			response.setMessage("Employee deleted successfully..!!");
			response.setResponse("Deleted employee id is :" + id);
		} catch (Exception e) {
			response.setMessage("Exception occurred while deleting the employee..!!");
			response.setResponse(null);
		}
		return response;
	}

	@Override
	public EmployeeResponse getBySal(Long sal) {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeDTO> empList = new ArrayList<>();
		try {
			List<Employee> employee = employeeRepository.findBySalaryGreaterThanEqual(sal);
			employee.stream().forEach(e -> {
				EmployeeDTO emp = new EmployeeDTO();
				emp.setEmpId(e.getEmpId());
				emp.setFirstName(e.getFirstName());
				emp.setLastName(e.getLastName());
				emp.setFullName(e.getFirstName() + " " + e.getLastName());
				emp.setPhone(e.getPhone());
				emp.setSalary(e.getSalary());
				emp.setEmail(e.getEmail());
				emp.setAddress(e.getAddress());
				empList.add(emp);
			});
			empList.sort((EmployeeDTO e1, EmployeeDTO e2) -> e1.getFirstName().compareTo(e2.getFirstName()));
			response.setMessage("Employee Data Fectched successfully..!!");
			response.setResponse(empList);
		} catch (Exception e) {
			response.setMessage("Exception occurred while fectchinhg the emplyee data..!!");
			response.setResponse(null);
		}
		return response;
	}

	public EmployeeResponse update(EmployeeDTO dto) {
		EmployeeResponse response = new EmployeeResponse();
		try {
			Optional<Employee> employee = employeeRepository.findById(dto.getEmpId());
			if (employee.isPresent()) {
				Employee emp = new Employee();
				emp.setEmpId(dto.getEmpId());
				emp.setFirstName(dto.getFirstName());
				emp.setLastName(dto.getLastName());
				emp.setPhone(dto.getPhone());
				emp.setSalary(dto.getSalary());
				emp.setAddress(dto.getAddress());
				emp.setEmail(dto.getEmail());
				Employee e =  employeeRepository.save(emp);
				response.setMessage("Employee updated successfully..!!");
				response.setResponse("updated employee id is :" + e.getEmpId());
			} else {
				response.setMessage("No Record found with given employee Id..!!");
				response.setResponse(null);
			}
		} catch (Exception e) {
			response.setMessage("Exception occurred while saving the employee..!!");
			response.setResponse(null);
		}
		return response;
	}
}