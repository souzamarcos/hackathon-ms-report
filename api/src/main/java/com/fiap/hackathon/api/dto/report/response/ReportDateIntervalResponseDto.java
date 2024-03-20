package com.fiap.hackathon.api.dto.report.response;


import com.fiap.hackathon.entity.report.ReportDateInterval;

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
