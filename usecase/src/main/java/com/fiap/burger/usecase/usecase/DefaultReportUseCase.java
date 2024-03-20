package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.report.Report;
import com.fiap.burger.entity.workingHour.WorkingHour;
import com.fiap.burger.usecase.adapter.gateway.WorkingHourGateway;
import com.fiap.burger.usecase.adapter.usecase.ReportUseCase;
import com.fiap.burger.usecase.misc.builder.ReportBuilder;
import com.fiap.burger.usecase.misc.exception.WorkingHoursNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class DefaultReportUseCase implements ReportUseCase {

    private final WorkingHourGateway gateway;

    public DefaultReportUseCase(WorkingHourGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Report preview(Employee employee, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<WorkingHour> workingHours = gateway.findByEmployeeIdAndBetweenStartDateAndEndDate(
            employee.getId(), startDateTime, endDateTime
        );

        if(workingHours.isEmpty()) {
            throw new WorkingHoursNotFoundException(employee.getId());
        }

        return new ReportBuilder(employee, workingHours).build();
    }

    @Override
    public String export(Employee employee, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<WorkingHour> workingHours = gateway.findByEmployeeIdAndBetweenStartDateAndEndDate(
            employee.getId(), startDateTime, endDateTime
        );

        if(workingHours.isEmpty()) {
            throw new WorkingHoursNotFoundException(employee.getId());
        }

        return null;
    }
}
