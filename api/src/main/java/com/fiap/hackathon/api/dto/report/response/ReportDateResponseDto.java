package com.fiap.hackathon.api.dto.report.response;


import com.fiap.hackathon.entity.report.ReportDate;
import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ReportDateResponseDto(
    String date,
    String total,
    Boolean hasErrors,
    List<ReportDateIntervalResponseDto> intervals
)
{
    public static ReportDateResponseDto toResponseDto(ReportDate reportDate) {
        return new ReportDateResponseDto(
            DateTimeUtils.toDateFormat(reportDate.getDate()),
            DateTimeUtils.toHourMinuteFormatByTotalMinutes(reportDate.getTotal()),
            reportDate.getHasErrors(),
            Optional.ofNullable(reportDate.getIntervals()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateIntervalResponseDto::toResponseDto)
                .toList()
        );
    }
}
