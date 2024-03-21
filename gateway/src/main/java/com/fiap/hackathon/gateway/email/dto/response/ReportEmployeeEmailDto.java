package com.fiap.hackathon.gateway.email.dto.response;

import com.fiap.hackathon.entity.employee.Employee;

public record ReportEmployeeEmailDto(
    String id,
    String email,
    String name
){
    public static ReportEmployeeEmailDto toResponseDto(Employee employee) {
        return new ReportEmployeeEmailDto(
            employee.getId(),
            employee.getEmail(),
            employee.getName()
        );
    }
}
