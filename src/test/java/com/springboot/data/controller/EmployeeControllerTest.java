package com.springboot.data.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springboot.data.customExceptionHandler.CustomException;
import com.springboot.data.entities.Employee;
import static org.mockito.Mockito.when;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.data.application.SpringBootApplication;
import com.springboot.data.repo.EmployeeRepository;
import com.springboot.data.serviceImpl.EmployeeServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
@TestPropertySource(locations = "classpath:application-mock.properties")
public class EmployeeControllerTest {

	@Autowired
	EmployeeController employeeController;

	@Autowired
	@InjectMocks
	EmployeeServiceImpl employeeService;

	@Autowired
	@MockBean
	EmployeeRepository employeeRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}




	@Test
	public void given_ValidEmpId_when_calling_getEmployee_then_return_employeeDetails() {

		try {

			ResponseEntity<Object> response = employeeController.getEmployee((long) 1001);

			assertNotNull(response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			Employee employeeDetails = converEmployeeData(response);

			assertEquals(1001, employeeDetails.getId());
			assertEquals("Ravi", employeeDetails.getFirstName());
			assertEquals("Teja", employeeDetails.getLastName());
			assertEquals("Java", employeeDetails.getDepartment());
			assertEquals("raviteja@gmail.com", employeeDetails.getEmailId());
			assertEquals("Hyderabad-INDIA", employeeDetails.getAddress());
          
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}

	}

	
	@Test
	public void given_ValidEmpId_when_calling_getEmployee_then_return_employeeDetails2() {

		try {

			ResponseEntity<Object> response = employeeController.getEmployee((long) 1002);

			assertNotNull(response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			Employee employeeDetails = converEmployeeData(response);

			assertEquals(1002, employeeDetails.getId());
			assertEquals("Mahesh", employeeDetails.getFirstName());
			assertEquals("Babu", employeeDetails.getLastName());
			assertEquals("Java-Spring", employeeDetails.getDepartment());
			assertEquals("maheshbabu@gmail.com", employeeDetails.getEmailId());
			assertEquals("Delhi-INDIA", employeeDetails.getAddress());

		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}

	}
	
	@Test
	public void given_ValidEmpId_when_calling_getEmployee_then_return_employeeDetails3() {

		try {

			ResponseEntity<Object> response = employeeController.getEmployee((long) 1003);

			assertNotNull(response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			Employee employeeDetails = converEmployeeData(response);

			assertEquals(1003, employeeDetails.getId());
			assertEquals("Prabhas", employeeDetails.getFirstName());
			assertEquals("Raju", employeeDetails.getLastName());
			assertEquals("Java-SpringBoot", employeeDetails.getDepartment());
			assertEquals("prabhasraju@gmail.com", employeeDetails.getEmailId());
			assertEquals("Bangalore-INDIA", employeeDetails.getAddress());

		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}

	}



	@Test
	public void test_ValidEmpId_when_calling_getEmployee_then_return_employeeDetails() {

		try {
			Employee employeeResponse = null;
			String empString = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("testdata/employeeResponse.json"), "UTF-8");
			employeeResponse = objectMapper.readValue(empString, new TypeReference<Employee>() {});

			when(employeeRepository.getEmbloyeById((long) 101)).thenReturn(employeeResponse);
			ResponseEntity<Object> response = employeeController.getEmployee((long)101);

			assertNotNull(response);
			assertEquals(HttpStatus.OK, response.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	private Employee converEmployeeData(ResponseEntity<Object> response) {
		Employee emp = null;
		try {
			String empString = objectMapper.writeValueAsString(response.getBody());
			emp = objectMapper.readValue(empString, new TypeReference<Employee>() {});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	@Test
	public void given_InValidEmpId_when_calling_getEmployee_then_return_ErrorDetails() {

		try {

			when(employeeRepository.getEmbloyeById((long) -101)).thenThrow(new RuntimeException());
			ResponseEntity<Object> rponse = employeeController.getEmployee((long)-101);


		} catch (CustomException ex) {
			assertNotNull(ex.getMessage());
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
			assertEquals("Employee Id Should be positive number", ex.getMessage());
		}

	}

	@Test
	public void given_ValidEmpIdButNoRecord_when_calling_getEmployee_then_return_ErrorDetails() {

		try {
			when(employeeRepository.getEmbloyeById((long) 104)).thenReturn(null);
			ResponseEntity<Object> rponse = employeeController.getEmployee((long)104);

		} catch (CustomException ex) {
			assertNotNull(ex.getMessage());
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
			assertEquals("Employee not found for given id 104", ex.getMessage());
		}

	}


}
