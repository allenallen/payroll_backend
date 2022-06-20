package com.tamaraw.payrollbackend.models;

import javax.persistence.Entity;

@Entity
public class DeductionType extends AuditableEntity{

    private String type;

    public DeductionType(String type) {
        this.type = type;
    }

    protected DeductionType() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
