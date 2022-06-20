package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.DeductionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeductionTypeRepository extends JpaRepository<DeductionType, Long> {
}
