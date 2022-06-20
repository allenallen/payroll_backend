package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.IncomeSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeSettingsRepository extends JpaRepository<IncomeSettings, Long> {

    @Query("SELECT incomeSettings FROM IncomeSettings incomeSettings WHERE incomeSettings.active = true")
    List<IncomeSettings> getAllActive();

}
