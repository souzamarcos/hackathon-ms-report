package com.fiap.hackathon.api.dto.report.response;


import com.fiap.hackathon.entity.report.ReportDate;

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
            reportDate.getDate().toString(),
            reportDate.getTotal() + "",
            reportDate.getHasErrors(),
            Optional.ofNullable(reportDate.getIntervals()).orElse(Collections.emptyList())
                .stream()
                .map(ReportDateIntervalResponseDto::toResponseDto)
                .toList()
        );
    }
}
