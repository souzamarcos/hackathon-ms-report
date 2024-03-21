package com.fiap.hackathon.api.dto.report.response;


import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.entity.report.ReportDate;
import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ReportResponseDto (
    ReportEmployeeResponseDto employee,
    String total,
    String warning,
    List<ReportDateResponseDto> dates
){
    public static ReportResponseDto toResponseDto(Report report) {
        return new ReportResponseDto(
            Optional.ofNullable(report.getEmployee()).map(ReportEmployeeResponseDto::toResponseDto).orElse(null),
            DateTimeUtils.toHourMinuteFormatByTotalMinutes(report.getTotal()),
            getWarning(report.getDates()),
            Optional.ofNullable(report.getDates()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateResponseDto::toResponseDto)
                .toList()
        );
    }

    private static String getWarning(List<ReportDate> dates) {
        if (hasErrors(dates)) {
            return "RELATORIO POSSUI UM OU MAIS DIAS COM REGISTRO DE PONTO INCONSISTENTE";
        }
        return null;
    }

    private static boolean hasErrors(List<ReportDate> dates) {
        return Optional.ofNullable(dates).orElse(Collections.emptyList())
            .stream().anyMatch(ReportDate::getHasErrors);
    }
}
