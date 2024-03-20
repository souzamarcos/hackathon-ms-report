package com.fiap.burger.entity.workingHour;

import com.fiap.burger.entity.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class WorkingHour extends BaseEntity {
    private String employeeId;
    private LocalDateTime registryDateTime;

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
        if (!(o instanceof WorkingHour workingHour)) return false;
        return Objects.equals(hashCode(), workingHour.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getId(),
            getEmployeeId(),
            getRegistryDateTime(),
            getCreatedAt(),
            getModifiedAt(),
            getDeletedAt()
        );
    }

    @Override
    public String toString() {
        return "WorkingHour{" +
            "employeeId='" + employeeId + '\'' +
            ", registryDateTime=" + registryDateTime +
            ", id=" + id +
            ", createdAt=" + createdAt +
            ", modifiedAt=" + modifiedAt +
            ", deletedAt=" + deletedAt +
            '}';
    }

    public WorkingHour(String employeeId, LocalDateTime registryDateTime) {
        this.employeeId = employeeId;
        this.registryDateTime = registryDateTime;
    }
}
