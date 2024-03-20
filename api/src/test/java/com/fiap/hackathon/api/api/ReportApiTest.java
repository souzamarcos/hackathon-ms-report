package com.fiap.hackathon.api.api;

import com.fiap.hackathon.api.dto.report.response.ReportResponseDto;
import com.fiap.hackathon.controller.adapter.api.ReportController;
import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import com.fiap.hackathon.entity.report.Report;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportApiTest {
    @Mock
    ReportController controller;

    @InjectMocks
    ReportApi api;

    @Test
    void shouldPreview() {
        var employeeId = "ABC001";
        var startDate = LocalDateTime.now();
        var endDate = LocalDateTime.now();
        var report = new Report(
            new Employee(employeeId, "email@email.com", "Nome", EmployeeType.USER),
            0L,
            null);
        var expected = ReportResponseDto.toResponseDto(report);

        when(controller.preview(employeeId, startDate, endDate)).thenReturn(report);

        ReportResponseDto actual = api.preview(employeeId, startDate, endDate);

        assertEquals(expected, actual);

        verify(controller, times(1)).preview(employeeId, startDate, endDate);
    }

    @Test
    void shouldExport() {
        var employeeId = "ABC001";
        var startDate = LocalDateTime.now();
        var endDate = LocalDateTime.now();
        var expected = "Report sent sucessfully.";

        when(controller.export(employeeId, startDate, endDate)).thenReturn(expected);

        String actual = api.export(employeeId, startDate, endDate);

        assertEquals(expected, actual);

        verify(controller, times(1)).export(employeeId, startDate, endDate);
    }

}
