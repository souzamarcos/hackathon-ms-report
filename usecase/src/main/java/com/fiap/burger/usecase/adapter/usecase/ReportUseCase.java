package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.report.Report;

import java.time.LocalDateTime;

public interface ReportUseCase {
    Report preview(Employee employee, LocalDateTime startDateTime, LocalDateTime endDateTime);
    String export(Employee employee, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
