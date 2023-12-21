package com.cg.api.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.cg.api.dto.EmployeeDTO;
import com.cg.api.service.EmployeeService;
import com.cg.api.util.EmployeeResponse;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
	
	@InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;
    
    
    @Test
    public void testGetAllEmployee()
    {
        when(employeeService.getAllEmployee()).thenReturn(getResponse());

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.getAllEmployee();
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>) responseEntity.getBody().getResponse();
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertEquals("John",empDTOList.get(0).getFirstName());
    }
    
    @Test
    public void testSearchEmployeeWithEmployeeId()
    {
        when(employeeService.getEmployeeByKey(Mockito.anyMap())).thenReturn(getResponse());

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.search(null,"John",null);
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>) responseEntity.getBody().getResponse();
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertEquals("John Peter",empDTOList.get(0).getFullName());
    }
    
    @Test
    public void testSearchEmployeeWithFirstName()
    {
        when(employeeService.getEmployeeByKey(Mockito.anyMap())).thenReturn(getResponse());

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.search(null,null,"Peter");
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>)responseEntity.getBody().getResponse();
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertEquals("John",empDTOList.get(0).getFirstName());
    }
    
    @Test
    public void testSearchEmployeeWithLastName()
    {
        when(employeeService.getEmployeeByKey(Mockito.anyMap())).thenReturn(getResponse());

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.search(123L,null,null);
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>)responseEntity.getBody().getResponse();
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertEquals("Peter",empDTOList.get(0).getLastName());
    }
    
    
    @Test
    public void testgetEmployeeSalGT_50000()
    {
        when(employeeService.getBySal(Mockito.anyLong())).thenReturn(getResponse());

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.getBySal(6000L);
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>)responseEntity.getBody().getResponse();
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertEquals(6000L,(long)empDTOList.get(0).getSalary());
    }
    
    
    @Test
    public void testgetEmployeeSalWithNull()
    {

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.getBySal(0L);
		EmployeeResponse reponse  =  responseEntity.getBody();
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertNull(reponse.getMessage());
    }
    
    
    @Test
    public void testDeleteEmployee()
    {
    	EmployeeResponse employeeResponse = new EmployeeResponse();
    	employeeResponse.setResponse("Employee Deleted");
    	employeeResponse.setResponse(1L);
        when(employeeService.delete(Mockito.anyLong())).thenReturn(employeeResponse);

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.deleteEmployee(1L);
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertEquals(1L,(long)responseEntity.getBody().getResponse());
    }
    
    @Test
    public void testSaveEmployee()
    {
    	EmployeeResponse employeeResponse = new EmployeeResponse();
        when(employeeService.save(Mockito.any())).thenReturn(employeeResponse);
        
        EmployeeDTO employee = new EmployeeDTO();

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.saveEmployee(employee);
        assertEquals(201,responseEntity.getStatusCodeValue());
    }
    
    @Test
    public void testUpdateEmployee()
    {
    	EmployeeResponse employeeResponse = new EmployeeResponse();
        when(employeeService.update(Mockito.any())).thenReturn(employeeResponse);
        
        EmployeeDTO employee = new EmployeeDTO();

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.update(employee);
        assertEquals(200,responseEntity.getStatusCodeValue());
    }
    
    private EmployeeResponse getResponse() {
    	EmployeeResponse response = new EmployeeResponse();
		List<EmployeeDTO> empDTOList = new ArrayList<>();
		
		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmpId(1L);
		employee.setFirstName("John");
		employee.setLastName("Peter");
		employee.setFullName("John Peter");
		employee.setEmail("john@gmail.com");
		employee.setPhone(12312314L);
		employee.setSalary(6000L);
		employee.setAddress("TEXAS");
		
		empDTOList.add(employee);
		response.setMessage("employee data proccessed successfulluy");
		response.setResponse(empDTOList);
		return response;
    }

}
