package com.tamaraw.payrollbackend.models.deductions;

import com.tamaraw.payrollbackend.models.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class EmployeeDeduction extends AuditableEntity {

    private String deductionType;

    private BigDecimal totalAmount;

    @ManyToOne
    private EmployeeDeductions employeeDeductions;

    public EmployeeDeduction(String deductionType, BigDecimal totalAmount, EmployeeDeductions employeeDeductions) {
        this.deductionType = deductionType;
        this.totalAmount = totalAmount;
        this.employeeDeductions = employeeDeductions;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public EmployeeDeductions getEmployeeDeductions() {
        return employeeDeductions;
    }

    public void setEmployeeDeductions(EmployeeDeductions employeeDeductions) {
        this.employeeDeductions = employeeDeductions;
    }

    protected EmployeeDeduction() {
    }

}
