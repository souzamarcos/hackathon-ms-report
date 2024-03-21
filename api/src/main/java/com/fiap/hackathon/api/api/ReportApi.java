package com.fiap.hackathon.api.api;

import com.fiap.hackathon.api.dto.report.response.ReportResponseDto;
import com.fiap.hackathon.controller.adapter.api.ReportController;
import com.fiap.hackathon.entity.employee.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reports")
@Tag(name = "relatorio", description = "API responsável pelos relatórios de ponto.")
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class ReportApi {

    @Autowired
    ReportController controller;

    @Operation(summary = "Visualizar relatorio", description = "Visualizar relatorio de ponto de horas", tags = {"relatorio"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @GetMapping("/preview")
    @SecurityRequirement(name = "Bearer Authentication")
    public ReportResponseDto preview(@RequestParam @Nullable LocalDateTime startDate,
                                     @RequestParam @Nullable LocalDateTime endDate) {
        var employee = getEmployeeUser();
        return ReportResponseDto.toResponseDto(controller
            .preview(employee, startDate, endDate));
    }

    @Operation(summary = "Exportar relatorio para email", description = "Exportar relatório de ponto de horas", tags = {"relatorio"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @PostMapping("/export")
    @SecurityRequirement(name = "Bearer Authentication")
    public String export(@RequestParam @Nullable LocalDateTime startDate,
                         @RequestParam @Nullable LocalDateTime endDate) {
        var employee = getEmployeeUser();
        return controller.export(employee, startDate, endDate);
    }

    private Employee getEmployeeUser() {
        return (Employee) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
