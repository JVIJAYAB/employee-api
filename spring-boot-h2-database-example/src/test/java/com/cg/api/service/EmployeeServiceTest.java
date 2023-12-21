package com.cg.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cg.api.dto.EmployeeDTO;
import com.cg.api.model.Employee;
import com.cg.api.repository.EmployeeRepository;
import com.cg.api.service.impl.EmployeeServiceImpl;
import com.cg.api.util.EmployeeResponse;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	@InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;
    
    
    @Test
    public void testGetAllEmployee()
    {
        when(employeeRepository.findAll()).thenReturn(getEmployeeList());

        EmployeeResponse response = employeeService.getAllEmployee();
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>) response.getResponse();
        assertEquals("John",empDTOList.get(0).getFirstName());
    }
    
    
    @Test
    public void testGetEmployeeByKey_empId()
    {
        when(employeeRepository.findById(Mockito.anyLong())).thenReturn(getOptionalEmployee());
        Map<String,Object> map = new HashMap();
        map.put("empId", 1L);
        EmployeeResponse response = employeeService.getEmployeeByKey(map);
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>) response.getResponse();
        assertEquals(Long.valueOf("1"),empDTOList.get(0).getEmpId());
    }
    
    @Test
    public void testGetEmployeeByKey_firstName()
    {
        when(employeeRepository.findByFirstName(Mockito.anyString())).thenReturn(getEmployeeList());
        Map<String,Object> map = new HashMap();
        map.put("firstName", "John");
        EmployeeResponse response = employeeService.getEmployeeByKey(map);
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>) response.getResponse();
        assertEquals("John",empDTOList.get(0).getFirstName());
    }
    
    @Test
    public void testGetEmployeeByKey_lastName()
    {
    	  when(employeeRepository.findByLastName(Mockito.anyString())).thenReturn(getEmployeeList());
        Map<String,Object> map = new HashMap();
        map.put("lastName", "Peter");
        EmployeeResponse response = employeeService.getEmployeeByKey(map);
		List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>) response.getResponse();
        assertEquals("Peter",empDTOList.get(0).getLastName());
    }
    
    
    
    @Test
    public void testSaveEmployee_exception()
    {
    	EmployeeDTO employeeDto = new EmployeeDTO();
    	employeeDto.setEmpId(1L);
    	employeeDto.setFirstName("John");
    	employeeDto.setLastName("Peter");
		employeeDto.setFullName("John Peter");
		employeeDto.setEmail("john@gmail.com");
		employeeDto.setPhone(12312314L);
		employeeDto.setSalary(6000L);
		employeeDto.setAddress("TEXAS");
    	
    	when(employeeRepository.save(Mockito.any())).thenReturn(getEmployeeList());
        EmployeeResponse response = employeeService.save(employeeDto);
        assertEquals("Exception occurred while saving the employee..!!",response.getMessage());
    }
    
    @Test
    public void testSaveEmployee()
    {
    	EmployeeDTO employeeDto = new EmployeeDTO();
    	employeeDto.setEmpId(1L);
    	employeeDto.setFirstName("John");
    	employeeDto.setLastName("Peter");
		employeeDto.setFullName("John Peter");
		employeeDto.setEmail("john@gmail.com");
		employeeDto.setPhone(12312314L);
		employeeDto.setSalary(6000L);
		employeeDto.setAddress("TEXAS");
		
		Employee employee = new Employee();
		employee.setEmpId(1L);
		employee.setFirstName("John");
		employee.setLastName("Peter");
		employee.setEmail("john@gmail.com");
		employee.setPhone(12312314L);
		employee.setSalary(6000L);
		employee.setAddress("TEXAS");
    	
    	when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        EmployeeResponse response = employeeService.save(employeeDto);
        assertEquals("Employee saved successfully..!!",response.getMessage());
    }
    
    @Test
    public void testDeleteEmployee()
    {
    	doNothing().when(employeeRepository).deleteById(Mockito.anyLong());
        EmployeeResponse response = employeeService.delete(1L);
        assertEquals("Employee deleted successfully..!!",response.getMessage());
    }
    
    @Test
    public void testGetBySal()
    {
    	when(employeeRepository.findBySalaryGreaterThanEqual(Mockito.anyLong())).thenReturn(getEmployeeList());
        EmployeeResponse response = employeeService.getBySal(6000L);
        List<EmployeeDTO> empDTOList =  (List<EmployeeDTO>) response.getResponse();
        assertTrue(empDTOList.size()>0);
    }
    
    @Test
    public void testUpdateEmployee()
    {
    	EmployeeDTO employeeDto = new EmployeeDTO();
    	employeeDto.setEmpId(1L);
    	employeeDto.setFirstName("John");
    	employeeDto.setLastName("Peter");
		employeeDto.setFullName("John Peter");
		employeeDto.setEmail("john@gmail.com");
		employeeDto.setPhone(12312314L);
		employeeDto.setSalary(6000L);
		employeeDto.setAddress("TEXAS");
		
		Employee employee = new Employee();
		employee.setEmpId(1L);
		employee.setFirstName("John");
		employee.setLastName("Peter");
		employee.setEmail("john@gmail.com");
		employee.setPhone(12312314L);
		employee.setSalary(6000L);
		employee.setAddress("TEXAS");
		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(getOptionalEmployee());
    	when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        EmployeeResponse response = employeeService.update(employeeDto);
        assertEquals("Employee updated successfully..!!",response.getMessage());
    }
    
    
    private List<Employee> getEmployeeList() {
		List<Employee> empList = new ArrayList<>();
		
		Employee employee = new Employee();
		employee.setEmpId(1L);
		employee.setFirstName("John");
		employee.setLastName("Peter");
		employee.setEmail("john@gmail.com");
		employee.setPhone(12312314L);
		employee.setSalary(6000L);
		employee.setAddress("TEXAS");
		
		empList.add(employee);
		
		return empList;
    }
    
    private Optional<Employee> getOptionalEmployee(){
    	
    	   Employee employee = new Employee();
    		employee.setEmpId(1L);
    		employee.setFirstName("John");
    		employee.setLastName("Peter");
    		employee.setEmail("john@gmail.com");
    		employee.setPhone(12312314L);
    		employee.setSalary(6000L);
    		employee.setAddress("TEXAS");
    		
    		return Optional.of(employee);
    }

}
