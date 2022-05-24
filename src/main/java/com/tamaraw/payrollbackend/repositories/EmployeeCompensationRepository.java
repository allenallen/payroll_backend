package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.EmployeeCompensation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCompensationRepository extends JpaRepository<EmployeeCompensation, Long> {
}
