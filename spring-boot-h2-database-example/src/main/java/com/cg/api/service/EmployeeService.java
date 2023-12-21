
package com.cg.api.service;

import java.util.Map;

import com.cg.api.dto.EmployeeDTO;
import com.cg.api.util.EmployeeResponse;

public interface EmployeeService {

	public EmployeeResponse getAllEmployee();

	public EmployeeResponse getEmployeeByKey(Map<String,Object> id);
	
	public EmployeeResponse getBySal(Long sal);

	public EmployeeResponse save(EmployeeDTO Employee);

	public EmployeeResponse delete(Long id);
	
	public EmployeeResponse update(EmployeeDTO Employee);
}
