package com.tamaraw.payrollbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class IncomeSettings extends AuditableEntity {

    private String name;

    @Column(columnDefinition = "boolean default false")
    private boolean fixed = false;

    private BigDecimal value;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public IncomeSettings(String name, boolean fixed, BigDecimal value) {
        this.name = name;
        this.fixed = fixed;
        this.value = value;
    }

    public void update(IncomeSettings incomeSettings) {
        this.name = incomeSettings.getName();
        this.fixed = incomeSettings.getFixed();
        this.value = incomeSettings.getValue();
    }

    protected IncomeSettings() {
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
