package com.fiap.hackathon.usecase.usecase;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.entity.workingHour.WorkingHour;
import com.fiap.hackathon.usecase.adapter.gateway.EmailGateway;
import com.fiap.hackathon.usecase.adapter.gateway.WorkingHourGateway;
import com.fiap.hackathon.usecase.adapter.usecase.ReportUseCase;
import com.fiap.hackathon.usecase.misc.builder.ReportBuilder;
import com.fiap.hackathon.usecase.misc.exception.WorkingHoursNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class DefaultReportUseCase implements ReportUseCase {

    private final WorkingHourGateway gateway;

    private final EmailGateway emailGateway;

    public DefaultReportUseCase(WorkingHourGateway gateway, EmailGateway emailGateway) {
        this.gateway = gateway;
        this.emailGateway = emailGateway;
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

        var report = new ReportBuilder(employee, workingHours).build();

        emailGateway.sendEmail(report);

        return "Email sent sucessfully!";
    }
}
