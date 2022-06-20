package com.tamaraw.payrollbackend.controllers;

import com.tamaraw.payrollbackend.dto.EmployeeDeductionsTotalDto;
import com.tamaraw.payrollbackend.dto.EmployeeDto;
import com.tamaraw.payrollbackend.models.ApiResponse;
import com.tamaraw.payrollbackend.models.Employee;
import com.tamaraw.payrollbackend.models.deductions.EmployeeDeduction;
import com.tamaraw.payrollbackend.models.deductions.EmployeeDeductions;
import com.tamaraw.payrollbackend.repositories.EmployeeDeductionRepository;
import com.tamaraw.payrollbackend.repositories.EmployeeDeductionsRepository;
import com.tamaraw.payrollbackend.repositories.EmployeeRepository;
import com.tamaraw.payrollbackend.utils.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/v1/employeeDeductions")
public class EmployeeDeductionsController {

    @Autowired
    private EmployeeDeductionRepository employeeDeductionRepository;

    @Autowired
    private EmployeeDeductionsRepository employeeDeductionsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<List<EmployeeDeductionsTotalDto>>> getAllTotal() {
        List<EmployeeDeductions> employeeDeductions = employeeDeductionsRepository.findAll();
        return new ResponseEntity<>(new ApiResponse<>("", map(employeeDeductions)), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<EmployeeDeductions>> getByEmployee(@PathVariable Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>("Employee with ID " + employeeId + " not found!", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>("", employeeDeductionsRepository.findEmployeeDeductionsByEmployee(employee.get())), HttpStatus.OK);
    }

    @PostMapping("/{deductionsId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<EmployeeDeductions>> addEmployeeDeduction(@PathVariable Long deductionsId,
                                                                                @RequestBody EmployeeDeduction employeeDeduction) {
        EmployeeDeductions employeeDeductions = employeeDeductionsRepository.getById(deductionsId);
        employeeDeduction.setEmployeeDeductions(employeeDeductions);
        employeeDeductions.addEmployeeDeduction(employeeDeduction);
        try {
            employeeDeductions = employeeDeductionsRepository.save(employeeDeductions);
            return new ResponseEntity<>(new ApiResponse<>("", employeeDeductions), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{employeeDeductionId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<EmployeeDeduction>> updateDeduction(@PathVariable Long employeeDeductionId,
                                                                          @RequestBody EmployeeDeduction employeeDeduction) {
        EmployeeDeduction deduction = employeeDeductionRepository.getById(employeeDeductionId);
        deduction.update(employeeDeduction);
        try {
            deduction = employeeDeductionRepository.save(deduction);
            return new ResponseEntity<>(new ApiResponse<>("", deduction), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{employeeDeductionId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<String>> deleteDeduction(@PathVariable Long employeeDeductionId) {
        try {
            employeeDeductionRepository.deleteById(employeeDeductionId);
            return new ResponseEntity<>(new ApiResponse<>("Successfully deleted", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<EmployeeDeductionsTotalDto> map(List<EmployeeDeductions> employeeDeductions) {
        Map<EmployeeDto, BigDecimal> employeeDeductionsMap = new HashMap<>();
        employeeDeductions.forEach(e -> {
            EmployeeDto employeeDto = new EmployeeDto(e.getEmployee().getId(), e.getEmployee().getFirstName(), e.getEmployee().getLastName());
            employeeDeductionsMap.putIfAbsent(employeeDto, BigDecimal.ZERO);
            List<EmployeeDeduction> deductions = employeeDeductionRepository.findEmployeeDeductionByEmployeeDeductions(e);
            if (!deductions.isEmpty()) {
                BigDecimal total = deductions.stream().map(EmployeeDeduction::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                employeeDeductionsMap.put(employeeDto, total);
            }
        });

        List<EmployeeDeductionsTotalDto> employeeDeductionsTotalDtos = new ArrayList<>();
        employeeDeductionsMap.forEach((key, value) -> employeeDeductionsTotalDtos.add(new EmployeeDeductionsTotalDto(key, value)));

        return employeeDeductionsTotalDtos;
    }

}
