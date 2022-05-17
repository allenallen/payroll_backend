package com.tamaraw.payrollbackend.repositories;

import com.tamaraw.payrollbackend.models.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {
    WebUser findUserByUsername(String username);
}
