package com.fiap.hackathon.gateway.email.dto.response;


import com.fiap.hackathon.entity.report.ReportDateInterval;
import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;

public record ReportDateIntervalEmailDto(
    String entry,
    String exit
)
{
    public static ReportDateIntervalEmailDto toResponseDto(ReportDateInterval interval) {
        return new ReportDateIntervalEmailDto(
            DateTimeUtils.toHourMinuteFormat(interval.getEntry()),
            DateTimeUtils.toHourMinuteFormat(interval.getExit())
        );
    }
}
