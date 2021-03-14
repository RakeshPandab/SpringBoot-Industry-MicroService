package com.springboot.data.service;

import com.springboot.data.customExceptionHandler.CustomException;
import com.springboot.data.entities.Employee;

import java.util.List;

public interface EmployeeService {
	
	public void saveEmployee(Employee employee);
	
	public Employee getEmployeeById(long id);

	public List<Employee> findAll();


	public void deleteEmployeeByEmplId(Employee employeeDetails);
}
