package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.deductions.EmployeeDeduction;
import com.tamaraw.payrollbackend.models.deductions.EmployeeDeductions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDeductionRepository extends JpaRepository<EmployeeDeduction, Long> {

    List<EmployeeDeduction> findEmployeeDeductionByEmployeeDeductions(EmployeeDeductions employeeDeductions);

}
