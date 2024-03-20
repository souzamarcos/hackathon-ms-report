package com.fiap.hackathon.usecase.adapter.usecase;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.report.Report;

import java.time.LocalDateTime;

public interface ReportUseCase {
    Report preview(Employee employee, LocalDateTime startDateTime, LocalDateTime endDateTime);
    String export(Employee employee, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
