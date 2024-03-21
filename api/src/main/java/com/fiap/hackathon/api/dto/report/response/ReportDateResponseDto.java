package com.fiap.hackathon.api.dto.report.response;

import com.fiap.hackathon.entity.report.ReportDate;
import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ReportDateResponseDto(
    String date,
    String total,
    String warning,
    List<ReportDateIntervalResponseDto> intervals
)
{
    public static ReportDateResponseDto toResponseDto(ReportDate reportDate) {
        return new ReportDateResponseDto(
            DateTimeUtils.toDateFormat(reportDate.getDate()),
            DateTimeUtils.toHourMinuteFormatByTotalMinutes(reportDate.getTotal()),
            getWarning(reportDate),
            Optional.ofNullable(reportDate.getIntervals()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateIntervalResponseDto::toResponseDto)
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
