package com.tamaraw.payrollbackend.controllers;

import com.tamaraw.payrollbackend.models.ApiResponse;
import com.tamaraw.payrollbackend.models.DeductionType;
import com.tamaraw.payrollbackend.repositories.DeductionTypeRepository;
import com.tamaraw.payrollbackend.utils.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/deductionType")
public class DeductionTypeController {

    @Autowired
    private DeductionTypeRepository deductionTypeRepository;

    @GetMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<List<DeductionType>>> getAll() {
        List<DeductionType> deductionTypes = deductionTypeRepository.findAll();
        return new ResponseEntity<>(new ApiResponse<>("", deductionTypes), HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<DeductionType>> create(@RequestBody DeductionType deductionType) {
        try {
            deductionType = deductionTypeRepository.save(deductionType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ApiResponse<>("", deductionType), HttpStatus.OK);
    }

    @DeleteMapping("/{deductionTypeId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long deductionTypeId) {
        Optional<DeductionType> deductionType = deductionTypeRepository.findById(deductionTypeId);
        if (deductionType.isPresent()) {
            try {
                deductionTypeRepository.delete(deductionType.get());
                return new ResponseEntity<>(new ApiResponse<>("Successfully deleted", null), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ApiResponse<>(String.format("Deduction Type with id %s not found!", deductionType), null), HttpStatus.NOT_FOUND);
        }
    }
}
