package com.fiap.burger.controller.controller;

import com.fiap.burger.controller.adapter.api.ReportController;
import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.employee.EmployeeType;
import com.fiap.burger.entity.report.Report;
import com.fiap.burger.usecase.adapter.usecase.ReportUseCase;
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
