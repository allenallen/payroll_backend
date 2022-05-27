package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT employee FROM Employee employee WHERE employee.active = true ORDER BY employee.employeeNumber")
    List<Employee> getAllActive();

    @Query("SELECT employee FROM Employee employee ORDER BY employee.employeeNumber")
    List<Employee> getAllOrdered();
}
