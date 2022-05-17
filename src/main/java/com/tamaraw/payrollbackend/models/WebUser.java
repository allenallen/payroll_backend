package com.tamaraw.payrollbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WebUser extends AuditableEntity{

    @Column(unique = true, updatable = false)
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WebUser() {}
}
