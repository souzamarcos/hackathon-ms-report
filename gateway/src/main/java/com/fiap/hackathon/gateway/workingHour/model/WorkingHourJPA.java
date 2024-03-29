package com.fiap.hackathon.gateway.workingHour.model;

import com.fiap.hackathon.entity.workingHour.WorkingHour;
import com.fiap.hackathon.gateway.misc.common.BaseDomainJPA;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "working_hour")
@Entity
public class WorkingHourJPA extends BaseDomainJPA {

    @Column(name = "employee_id")
    String employeeId;
    @Column(name = "registry_date_time")
    LocalDateTime registryDateTime;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getRegistryDateTime() {
        return registryDateTime;
    }

    public void setRegistryDateTime(LocalDateTime registryDateTime) {
        this.registryDateTime = registryDateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkingHourJPA workingHourJPA)) return false;
        return Objects.equals(hashCode(), workingHourJPA.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getId(),
            getEmployeeId(),
            getRegistryDateTime()
        );
    }

    public WorkingHourJPA() {
    }

    public WorkingHourJPA(String employeeId, LocalDateTime registryDateTime) {
        this.employeeId = employeeId;
        this.registryDateTime = registryDateTime;
    }

    public WorkingHour toWorkingHour() {
        return new WorkingHour(employeeId, registryDateTime);
    }
}
