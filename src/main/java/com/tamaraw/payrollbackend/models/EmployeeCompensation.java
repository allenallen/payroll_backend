package com.tamaraw.payrollbackend.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class EmployeeCompensation extends AuditableEntity{

    @OneToOne(cascade = CascadeType.REMOVE, targetEntity = Employee.class, orphanRemoval = true)
    private Employee employee;

    private BigDecimal daily;

    @Column(columnDefinition = "boolean default false")
    private Boolean sss = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean philhealth = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean pagibig = false;

    public EmployeeCompensation(Employee employee) {
        this.employee = employee;
        this.daily = BigDecimal.ZERO;
    }

    public void update(EmployeeCompensation employeeCompensation) {
        setDaily(employeeCompensation.getDaily());
        setPagibig(employeeCompensation.getPagibig());
        setPhilhealth(employeeCompensation.getPhilhealth());
        setSss(employeeCompensation.getSss());
    }

    protected EmployeeCompensation() {}

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getDaily() {
        return daily;
    }

    public void setDaily(BigDecimal daily) {
        this.daily = daily;
    }

    public Boolean getSss() {
        return sss;
    }

    public void setSss(Boolean sss) {
        this.sss = sss;
    }

    public Boolean getPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(Boolean philhealth) {
        this.philhealth = philhealth;
    }

    public Boolean getPagibig() {
        return pagibig;
    }

    public void setPagibig(Boolean pagibig) {
        this.pagibig = pagibig;
    }
}
