package com.fiap.hackathon.controller.adapter.api;

import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.entity.employee.Employee;

import java.time.LocalDateTime;

public interface ReportController {
    Report preview(Employee employee, LocalDateTime startDate, LocalDateTime endDate);

    String export(Employee employee, LocalDateTime startDate, LocalDateTime endDate);

}