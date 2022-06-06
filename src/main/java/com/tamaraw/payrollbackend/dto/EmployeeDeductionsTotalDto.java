package com.tamaraw.payrollbackend.dto;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeDeductionsTotalDto {

    private EmployeeDto employee;

    private BigDecimal total;

    public EmployeeDeductionsTotalDto(EmployeeDto employee, BigDecimal total) {
        this.employee = employee;
        this.total = total;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
