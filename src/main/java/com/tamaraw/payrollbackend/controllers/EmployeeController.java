package com.tamaraw.payrollbackend.controllers;

import com.tamaraw.payrollbackend.models.ApiResponse;
import com.tamaraw.payrollbackend.models.Employee;
import com.tamaraw.payrollbackend.repositories.EmployeeRepository;
import com.tamaraw.payrollbackend.utils.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(new ApiResponse<>("Success", employees), HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<Employee>> create(@RequestBody Employee employee) {
        try {
            employee = employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("Success", employee), HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<Employee>> update(@PathVariable Long employeeId, @RequestBody Employee employee) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (employeeOptional.isPresent()) {
                Employee employeeDb = employeeOptional.get();
                employeeDb.update(employee);
                employee = employeeRepository.save(employeeDb);
            } else {
                return new ResponseEntity<>(new ApiResponse<>(String.format("Employee with id %s not found!", employeeId),
                        null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("Success", employee), HttpStatus.OK);
    }

}
