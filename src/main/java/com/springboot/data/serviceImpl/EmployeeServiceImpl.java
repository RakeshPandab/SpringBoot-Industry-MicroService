package com.springboot.data.serviceImpl;

import com.springboot.data.entities.Employee;
import com.springboot.data.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.data.service.EmployeeService;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {
		
			employeeRepository.save(employee);

	}

	@Override
	public Employee getEmployeeById(long id) {
		Employee empResponse = null;
		
		empResponse = employeeRepository.getEmbloyeById(id);

		return empResponse;
	}

	@Override
	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	@Override
	public void deleteEmployeeByEmplId(Employee employeeDetails) {

		employeeRepository.delete(employeeDetails);

	}


}
