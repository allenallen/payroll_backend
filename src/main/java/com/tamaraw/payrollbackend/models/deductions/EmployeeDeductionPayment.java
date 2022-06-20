package com.tamaraw.payrollbackend.models.deductions;

import com.tamaraw.payrollbackend.models.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class EmployeeDeductionPayment extends AuditableEntity {

    @ManyToOne
    private EmployeeDeduction employeeDeduction;

    private LocalDate datePaid;

    private BigDecimal amount;

    public EmployeeDeductionPayment(EmployeeDeduction employeeDeduction, LocalDate datePaid, BigDecimal amount) {
        this.employeeDeduction = employeeDeduction;
        this.datePaid = datePaid;
        this.amount = amount;
    }

    public EmployeeDeduction getEmployeeDeduction() {
        return employeeDeduction;
    }

    public void setEmployeeDeduction(EmployeeDeduction employeeDeduction) {
        this.employeeDeduction = employeeDeduction;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    protected EmployeeDeductionPayment() {}
}
