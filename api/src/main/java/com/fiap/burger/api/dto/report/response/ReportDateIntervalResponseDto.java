package com.fiap.burger.api.dto.report.response;


import com.fiap.burger.entity.report.ReportDateInterval;

public record ReportDateIntervalResponseDto(
    String entry,
    String exit
)
{
    public static ReportDateIntervalResponseDto toResponseDto(ReportDateInterval interval) {
        return new ReportDateIntervalResponseDto(
            interval.getEntry().toString(),
            interval.getExit().toString()
        );
    }
}
