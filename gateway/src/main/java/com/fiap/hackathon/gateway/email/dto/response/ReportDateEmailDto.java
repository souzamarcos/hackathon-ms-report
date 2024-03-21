package com.fiap.hackathon.gateway.email.dto.response;


import com.fiap.hackathon.entity.report.ReportDate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ReportDateEmailDto(
    String date,
    String total,
    Boolean hasErrors,
    List<ReportDateIntervalEmailDto> intervals
)
{
    public static ReportDateEmailDto toResponseDto(ReportDate reportDate) {
        return new ReportDateEmailDto(
            reportDate.getDate().toString(),
            reportDate.getTotal() + "",
            reportDate.getHasErrors(),
            Optional.ofNullable(reportDate.getIntervals()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateIntervalEmailDto::toResponseDto)
                .toList()
        );
    }
}
