package com.fiap.hackathon.gateway.email.dto.response;


import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.entity.report.ReportDate;
import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ReportEmailDto(
    ReportEmployeeEmailDto employee,
    String total,
    String warning,
    List<ReportDateEmailDto> dates
){
    public static ReportEmailDto toResponseDto(Report report) {
        return new ReportEmailDto(
            Optional.ofNullable(report.getEmployee()).map(ReportEmployeeEmailDto::toResponseDto).orElse(null),
            DateTimeUtils.toHourMinuteFormatByTotalMinutes(report.getTotal()),
            getWarning(report.getDates()),
            Optional.ofNullable(report.getDates()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateEmailDto::toResponseDto)
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
