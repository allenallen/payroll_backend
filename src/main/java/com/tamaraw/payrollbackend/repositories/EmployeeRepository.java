package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
