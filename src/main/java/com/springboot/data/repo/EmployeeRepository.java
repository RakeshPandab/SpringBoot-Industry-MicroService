package com.springboot.data.repo;

import com.springboot.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee getEmbloyeById(Long empId);
}
