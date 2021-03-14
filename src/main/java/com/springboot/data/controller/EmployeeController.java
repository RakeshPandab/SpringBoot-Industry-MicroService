package com.springboot.data.controller;

import com.springboot.data.customExceptionHandler.CustomException;
import com.springboot.data.entities.Employee;
import com.springboot.data.validationUtil.EmployeeRequestValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.data.service.EmployeeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRequestValidationUtil validationUtil;

    @PostMapping(value = "/saveEmployees")
    public ResponseEntity<Object> saveAllEmployees(@Valid @RequestBody Employee employee) {

        employeeService.saveEmployee(employee);
        return new ResponseEntity<Object>("Successfully Saved", HttpStatus.OK);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping(value = "/getEmployeeByEmpId/empId/{empId}")
    public ResponseEntity<Object> getEmployee(@PathVariable(value = "empId") Long empId) throws CustomException {

        validationUtil.validateEmployeeId(empId);
        Employee empResponse = employeeService.getEmployeeById(empId);
        return new ResponseEntity<Object>(empResponse, HttpStatus.OK);

    }

    @PutMapping("/updateEmployeesByEmpId/empId/{empId}")
    public String updateEmployee(@PathVariable(value = "empId") Long employeeId,
                                 @Valid @RequestBody Employee employeeDetails) throws CustomException{
        validationUtil.validateEmployeeId(employeeId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setAddress(employeeDetails.getAddress());
        employee.setPassportNumber(employeeDetails.getPassportNumber());
        employeeService.saveEmployee(employee);
        return "redirect:getAllEmployees";
    }

    @DeleteMapping("/deleteEmployeesByEmpId/empId/{empId}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "empId") Long employeeId)throws CustomException {
        validationUtil.validateEmployeeId(employeeId);
        Employee employeeDetails = employeeService.getEmployeeById(employeeId);
        employeeService.deleteEmployeeByEmplId(employeeDetails);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Employee " + employeeDetails.getFirstName() + " Record is Delete .", Boolean.TRUE);
        return response;
    }

}
