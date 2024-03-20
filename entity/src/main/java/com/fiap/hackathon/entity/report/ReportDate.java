package com.fiap.hackathon.entity.report;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ReportDate {
    private LocalDateTime date;
    private long total;
    private Boolean hasErrors;
    private List<ReportDateInterval> intervals;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Boolean getHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(Boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<ReportDateInterval> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<ReportDateInterval> intervals) {
        this.intervals = intervals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDate that = (ReportDate) o;
        return Objects.equals(date, that.date) && Objects.equals(total, that.total) && Objects.equals(hasErrors, that.hasErrors) && Objects.equals(intervals, that.intervals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, total, hasErrors, intervals);
    }

    @Override
    public String toString() {
        return "ReportDate{" +
            "date=" + date +
            ", total=" + total +
            ", hasErrors=" + hasErrors +
            ", items=" + intervals +
            '}';
    }

    public ReportDate() {
    }

    public ReportDate(LocalDateTime date, long total, Boolean hasErrors, List<ReportDateInterval> intervals) {
        this.date = date;
        this.total = total;
        this.hasErrors = hasErrors;
        this.intervals = intervals;
    }
}
