package com.tamaraw.payrollbackend.controllers;

import com.tamaraw.payrollbackend.models.ApiResponse;
import com.tamaraw.payrollbackend.models.EmployeeCompensation;
import com.tamaraw.payrollbackend.repositories.EmployeeCompensationRepository;
import com.tamaraw.payrollbackend.utils.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employeeCompensation")
public class EmployeeCompensationController {

    @Autowired
    private EmployeeCompensationRepository employeeCompensationRepository;

    @GetMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<List<EmployeeCompensation>>> getAll() {
        List<EmployeeCompensation> employeeCompensations = employeeCompensationRepository.findAll();
        return new ResponseEntity<>(new ApiResponse<>("Success", employeeCompensations), HttpStatus.OK);
    }

    @PutMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<EmployeeCompensation>> update(@RequestBody EmployeeCompensation employeeCompensation) {
        Optional<EmployeeCompensation> toUpdate = employeeCompensationRepository.findById(employeeCompensation.getId());
        if (toUpdate.isPresent()) {
            EmployeeCompensation updated = toUpdate.get();
            updated.update(employeeCompensation);
            updated = employeeCompensationRepository.save(updated);
            return new ResponseEntity<>(new ApiResponse<>("Successfully updated", updated), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>("Employee compensation not found!", null), HttpStatus.NOT_FOUND);
        }
    }

}
