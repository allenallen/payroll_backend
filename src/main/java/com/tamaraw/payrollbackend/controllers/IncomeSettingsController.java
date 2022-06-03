package com.tamaraw.payrollbackend.controllers;

import com.tamaraw.payrollbackend.models.ApiResponse;
import com.tamaraw.payrollbackend.models.IncomeSettings;
import com.tamaraw.payrollbackend.repositories.IncomeSettingsRepository;
import com.tamaraw.payrollbackend.utils.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/incomeSettings")
public class IncomeSettingsController {

    @Autowired
    private IncomeSettingsRepository incomeSettingsRepository;

    @GetMapping("/{incomeSettingsId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<IncomeSettings>> getOne(@PathVariable Long incomeSettingsId) {
        Optional<IncomeSettings> incomeSettings = incomeSettingsRepository.findById(incomeSettingsId);
        return incomeSettings.map(settings -> new ResponseEntity<>(new ApiResponse<>("Success", settings), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Not found", null), HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<List<IncomeSettings>>> getAll(@RequestParam(required = false) boolean queryAll) {
        List<IncomeSettings> incomeSettings;
        if (queryAll) {
            incomeSettings = incomeSettingsRepository.findAll();
        } else {
            incomeSettings = incomeSettingsRepository.getAllActive();
        }
        return new ResponseEntity<>(new ApiResponse<>("", incomeSettings), HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<IncomeSettings>> create(@RequestBody IncomeSettings incomeSettings) {
        try {

            if (incomeSettings.getFixed() && incomeSettings.getValue() != null) {
                throw new RuntimeException("Percentage value should be empty");
            } else if (!incomeSettings.getFixed() && incomeSettings.getValue() == null) {
                throw new RuntimeException("Percentage value should not be empty!");
            }

            incomeSettings = incomeSettingsRepository.save(incomeSettings);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ApiResponse<>("", incomeSettings), HttpStatus.OK);
    }

    @PutMapping("/{incomeSettingsId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<IncomeSettings>> update(@PathVariable Long incomeSettingsId, @RequestBody IncomeSettings incomeSettings) {
        Optional<IncomeSettings> toUpdate = incomeSettingsRepository.findById(incomeSettingsId);
        if (toUpdate.isPresent()) {
            IncomeSettings update = toUpdate.get();
            update.update(incomeSettings);
            try {
                incomeSettings = incomeSettingsRepository.save(update);
                return new ResponseEntity<>(new ApiResponse<>("", incomeSettings), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ApiResponse<>(String.format("Income setting with ID %s not found!", incomeSettingsId), null),
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{incomeSettingsId}")
    @TrackExecutionTime
    public ResponseEntity<ApiResponse<String>> setActiveOrInactive(@PathVariable Long incomeSettingsId) {
        Optional<IncomeSettings> toUpdate = incomeSettingsRepository.findById(incomeSettingsId);
        if (toUpdate.isPresent()) {
            try {
                IncomeSettings update = toUpdate.get();
                update.setActive(!update.getActive());
                incomeSettingsRepository.save(update);
                return new ResponseEntity<>(new ApiResponse<>("", ""), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ApiResponse<>(String.format("Income setting with ID %s not found!", incomeSettingsId), null),
                    HttpStatus.NOT_FOUND);
        }
    }
}
