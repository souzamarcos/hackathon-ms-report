package com.fiap.hackathon.entity.report;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReportDateInterval {
    private LocalDateTime entry;
    private LocalDateTime exit;
    private long total;
    private Boolean hasError;

    public LocalDateTime getEntry() {
        return entry;
    }

    public void setEntry(LocalDateTime entry) {
        this.entry = entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDateInterval that = (ReportDateInterval) o;
        return Objects.equals(entry, that.entry) && Objects.equals(exit, that.exit) && Objects.equals(total, that.total) && Objects.equals(hasError, that.hasError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry, exit, total, hasError);
    }

    @Override
    public String toString() {
        return "ReportDateInterval{" +
            "entry=" + entry +
            ", exit=" + exit +
            ", total=" + total +
            ", hasError=" + hasError +
            '}';
    }

    public ReportDateInterval() {
    }

    public ReportDateInterval(LocalDateTime entry, LocalDateTime exit, long total, Boolean hasError) {
        this.entry = entry;
        this.exit = exit;
        this.total = total;
        this.hasError = hasError;
    }
}
