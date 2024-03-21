package com.fiap.hackathon.api.dto.report.response;


import com.fiap.hackathon.entity.report.ReportDateInterval;
import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;

public record ReportDateIntervalResponseDto(
    String entry,
    String exit
)
{
    public static ReportDateIntervalResponseDto toResponseDto(ReportDateInterval interval) {
        return new ReportDateIntervalResponseDto(
            DateTimeUtils.toHourMinuteFormat(interval.getEntry()),
            DateTimeUtils.toHourMinuteFormat(interval.getExit())
        );
    }
}
