package com.springboot.data.validationUtil;

import com.springboot.data.customExceptionHandler.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeRequestValidationUtil {

    public void validateEmployeeId(long empId)throws CustomException {
        if(empId <= 0) {
            throw new CustomException("Employee Id Should be positive number", HttpStatus.BAD_REQUEST);
        }
    }

}
