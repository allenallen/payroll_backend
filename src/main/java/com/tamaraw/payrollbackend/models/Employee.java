package com.tamaraw.payrollbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Employee extends AuditableEntity{

    private String firstName;

    private String lastName;

    private String contactNumber;

    private String address;

    @Column(unique = true)
    private String employeeNumber;

    private LocalDate birthday;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    public Employee(String firstName, String lastName, String contactNumber, String address, String employeeNumber, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.employeeNumber = employeeNumber;
        this.birthday = birthday;
    }

    public void update(Employee employee) {
        setFirstName(employee.getFirstName());
        setLastName(employee.getLastName());
        setAddress(employee.getAddress());
        setBirthday(employee.getBirthday());
        setContactNumber(employee.getContactNumber());
        setEmployeeNumber(employee.getEmployeeNumber());
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    protected Employee() {}
}
