package com.fiap.hackathon.api.dto.report.response;

import com.fiap.hackathon.entity.employee.Employee;

public record ReportEmployeeResponseDto (
    String id,
    String email,
    String name
){
    public static ReportEmployeeResponseDto toResponseDto(Employee employee) {
        return new ReportEmployeeResponseDto(
            employee.getId(),
            employee.getEmail(),
            employee.getName()
        );
    }
}
