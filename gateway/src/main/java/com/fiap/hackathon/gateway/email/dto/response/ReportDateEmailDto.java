package com.fiap.hackathon.gateway.email.dto.response;


import com.fiap.hackathon.entity.report.ReportDate;
import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ReportDateEmailDto(
    String date,
    String total,
    String warning,
    List<ReportDateIntervalEmailDto> intervals
)
{
    public static ReportDateEmailDto toResponseDto(ReportDate reportDate) {
        return new ReportDateEmailDto(
            DateTimeUtils.toDateFormat(reportDate.getDate()),
            DateTimeUtils.toHourMinuteFormatByTotalMinutes(reportDate.getTotal()),
            getWarning(reportDate),
            Optional.ofNullable(reportDate.getIntervals()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateIntervalEmailDto::toResponseDto)
                .toList()
        );
    }

    private static String getWarning(ReportDate reportDate) {
        if (Optional.ofNullable(reportDate.getHasErrors()).orElse(false)) {
            return "REGISTRO DE PONTO INCONSISTENTE";
        }
        return null;
    }
}
