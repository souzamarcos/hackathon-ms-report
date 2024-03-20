package com.fiap.hackathon.entity.report;

import com.fiap.hackathon.entity.employee.Employee;

import java.util.List;
import java.util.Objects;

public class Report {
    private Employee employee;
    private long total;
    private List<ReportDate> dates;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ReportDate> getDates() {
        return dates;
    }

    public void setDates(List<ReportDate> dates) {
        this.dates = dates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(employee, report.employee) && Objects.equals(total, report.total) && Objects.equals(dates, report.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, total, dates);
    }

    @Override
    public String toString() {
        return "Report{" +
            "employee=" + employee +
            ", total=" + total +
            ", dates=" + dates +
            '}';
    }

    public Report() {

    }

    public Report(Employee employee, long total, List<ReportDate> dates) {
        this.employee = employee;
        this.total = total;
        this.dates = dates;
    }
}
