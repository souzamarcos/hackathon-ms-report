package com.fiap.hackathon.gateway.email.dto.response;


import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.entity.report.ReportDate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ReportEmailDto(
    ReportEmployeeEmailDto employee,
    String total,
    Boolean hasError,
    List<ReportDateEmailDto> dates
){
    public static ReportEmailDto toResponseDto(Report report) {
        return new ReportEmailDto(
            Optional.ofNullable(report.getEmployee()).map(ReportEmployeeEmailDto::toResponseDto).orElse(null),
            ""+report.getTotal(),
            hasErrors(report.getDates()),
            Optional.ofNullable(report.getDates()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateEmailDto::toResponseDto)
                .toList()
        );
    }

    private static Boolean hasErrors(List<ReportDate> dates) {
        return Optional.ofNullable(dates).orElse(Collections.emptyList())
            .stream().anyMatch(ReportDate::getHasErrors);
    }
}
