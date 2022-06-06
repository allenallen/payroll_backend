package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.Employee;
import com.tamaraw.payrollbackend.models.deductions.EmployeeDeductions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDeductionsRepository extends JpaRepository<EmployeeDeductions, Long> {

    List<EmployeeDeductions> findEmployeeDeductionsByEmployee(Employee employee);

}
