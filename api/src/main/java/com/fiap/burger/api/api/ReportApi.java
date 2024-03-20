package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.report.response.ReportResponseDto;
import com.fiap.burger.controller.adapter.api.ReportController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reports")
@Tag(name = "relatorio", description = "API responsável pelos relatórios de ponto.")
public class ReportApi {

    @Autowired
    ReportController controller;

    @Operation(summary = "Visualizar relatorio", description = "Visualizar relatorio de ponto de horas", tags = {"relatorio"})
    @GetMapping("/preview")
    public ReportResponseDto preview(@RequestParam @Nullable String employeeId,
                                         @RequestParam @Nullable LocalDateTime startDate,
                                         @RequestParam @Nullable LocalDateTime endDate) {
        return ReportResponseDto.toResponseDto(controller
            .preview(employeeId, startDate, endDate));
    }

    @Operation(summary = "Exportar relatorio para email", description = "Exportar relatório de ponto de horas", tags = {"relatorio"})
    @PostMapping("/export")
    public String export(@RequestParam @Nullable String employeeId,
                         @RequestParam @Nullable LocalDateTime startDate,
                         @RequestParam @Nullable LocalDateTime endDate) {
        return controller.export(employeeId, startDate, endDate);
    }
}
