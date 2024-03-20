package com.fiap.hackathon.controller.adapter.api;

import com.fiap.hackathon.entity.report.Report;

import java.time.LocalDateTime;

public interface ReportController {
    Report preview(String employeeId, LocalDateTime startDate, LocalDateTime endDate);

    String export(String employeeId, LocalDateTime startDate, LocalDateTime endDate);

}