package com.fiap.hackathon.gateway.email.dto.response;


import com.fiap.hackathon.entity.report.ReportDateInterval;

public record ReportDateIntervalEmailDto(
    String entry,
    String exit
)
{
    public static ReportDateIntervalEmailDto toResponseDto(ReportDateInterval interval) {
        return new ReportDateIntervalEmailDto(
            interval.getEntry().toString(),
            interval.getExit().toString()
        );
    }
}
