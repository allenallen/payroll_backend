package com.tamaraw.payrollbackend.controllers;

import com.tamaraw.payrollbackend.models.Employee;
import com.tamaraw.payrollbackend.repositories.EmployeeRepository;
import com.tamaraw.payrollbackend.utils.TrackExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        try {
            employee = employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
