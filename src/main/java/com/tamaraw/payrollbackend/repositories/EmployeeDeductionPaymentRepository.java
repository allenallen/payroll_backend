package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.deductions.EmployeeDeductionPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDeductionPaymentRepository extends JpaRepository<EmployeeDeductionPayment, Long> {
}
