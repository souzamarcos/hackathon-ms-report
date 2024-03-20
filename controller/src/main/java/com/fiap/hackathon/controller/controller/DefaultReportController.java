package com.fiap.hackathon.controller.controller;

import com.fiap.hackathon.controller.adapter.api.ReportController;
import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.usecase.adapter.usecase.ReportUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefaultReportController implements ReportController {
    @Autowired
    ReportUseCase useCase;

    @Override
    public Report preview(String employeeId, LocalDateTime startDate, LocalDateTime endDate) {
        var employee = new Employee(employeeId, "email@email.com", "Nome", EmployeeType.USER);
        return useCase.preview(employee, startDate, endDate);
    }

    @Override
    public String export(String employeeId, LocalDateTime startDate, LocalDateTime endDate) {
        var employee = new Employee(employeeId, "email@email.com", "Nome", EmployeeType.USER);
        return useCase.export(employee, startDate, endDate);
    }
}
