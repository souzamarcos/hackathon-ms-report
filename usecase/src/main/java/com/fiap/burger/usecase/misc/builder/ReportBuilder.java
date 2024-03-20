package com.fiap.burger.usecase.misc.builder;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.report.Report;
import com.fiap.burger.entity.report.ReportDate;
import com.fiap.burger.entity.report.ReportDateInterval;
import com.fiap.burger.entity.workingHour.WorkingHour;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReportBuilder {

    private final Employee employee;

    private final List<WorkingHour> workingHours;


    public ReportBuilder(Employee employee, List<WorkingHour> workingHours) {
        this.employee = employee;
        this.workingHours = workingHours;
    }

    public Report build() {
        Report report = new Report();
        report.setEmployee(employee);
        var reportDates = buildReportDates();
        report.setDates(reportDates);
        report.setTotal(reportDates.stream().mapToLong(ReportDate::getTotal).sum());
        return report;
    }

    private List<ReportDate> buildReportDates() {
        return workingHours.stream()
            .map(WorkingHour::getRegistryDateTime)
            .collect(Collectors.groupingBy((LocalDateTime registryDateTime) ->
                LocalDateTime.of(registryDateTime.getYear(), registryDateTime.getMonth(), registryDateTime.getDayOfMonth(), 0, 0, 0, 0)
            ))
            .entrySet().stream().map(e ->{
                ReportDate reportDate = new ReportDate();
                reportDate.setDate(e.getKey());
                var intervals = buildReportDateInterval(e.getValue());
                reportDate.setIntervals(intervals);
                reportDate.setHasErrors(intervals.stream().anyMatch(ReportDateInterval::getHasError));
                reportDate.setTotal(intervals.stream().mapToLong(ReportDateInterval::getTotal).sum());
                return reportDate;
            })
            .sorted(Comparator.comparing(ReportDate::getDate))
            .toList();
    }

    private List<ReportDateInterval> buildReportDateInterval(List<LocalDateTime> registryDateTimes) {
        List<LocalDateTime> times = registryDateTimes.stream().sorted().toList();
        List<ReportDateInterval> intervals = new ArrayList<>();
        for(int i = 0; i+1 < times.size(); i = i + 2) {
            ReportDateInterval interval = new ReportDateInterval();
            interval.setEntry(times.get(i));
            interval.setExit(times.get(i+1));
            interval.setTotal(buildTotal(interval.getEntry(), interval.getExit()));
            interval.setHasError(false);
            intervals.add(interval);
        }

        if(times.size() %2 != 0) {
            ReportDateInterval interval = new ReportDateInterval();
            interval.setEntry(times.get(times.size()-1));
            interval.setExit(interval.getEntry().plusSeconds(1));
            interval.setTotal(buildTotal(interval.getEntry(), interval.getExit()));
            interval.setHasError(true);
            intervals.add(interval);
        }

        return intervals;
    }

    private long buildTotal(LocalDateTime entry, LocalDateTime exit) {
        return entry.until(exit, ChronoUnit.MINUTES);
    }
}
