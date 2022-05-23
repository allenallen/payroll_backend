package com.tamaraw.payrollbackend.controllers;

import com.tamaraw.payrollbackend.models.WebUser;
import com.tamaraw.payrollbackend.repositories.WebUserRepository;
import com.tamaraw.payrollbackend.utils.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private WebUserRepository webUserRepository;

    @GetMapping("/{username}")
    @TrackExecutionTime
    public ResponseEntity<WebUser> getUser(@PathVariable String username) {
        WebUser webUser = webUserRepository.findUserByUsername(username);

        if (webUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(webUser, HttpStatus.OK);
    }
}
