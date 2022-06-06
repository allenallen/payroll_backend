package com.tamaraw.payrollbackend.models.deductions;

import com.tamaraw.payrollbackend.models.AuditableEntity;
import com.tamaraw.payrollbackend.models.Employee;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EmployeeDeductions extends AuditableEntity {

    @ManyToOne
    private Employee employee;

    @OneToMany(mappedBy = "employeeDeductions", cascade = CascadeType.DETACH)
    private List<EmployeeDeduction> employeeDeductions = new ArrayList<>();

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void addEmployeeDeduction(EmployeeDeduction employeeDeduction) {
        this.employeeDeductions.add(employeeDeduction);
    }

    public void removeEmployeeDeduction(EmployeeDeduction employeeDeduction) {
        this.employeeDeductions.remove(employeeDeduction);
    }

    public List<EmployeeDeduction> getEmployeeDeductions() {
        return new ArrayList<>(employeeDeductions);
    }

    public void setEmployeeDeductions(List<EmployeeDeduction> employeeDeductions) {
        this.employeeDeductions = employeeDeductions;
    }

    protected EmployeeDeductions() {
    }
}
