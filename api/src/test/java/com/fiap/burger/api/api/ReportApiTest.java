package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.report.response.ReportResponseDto;
import com.fiap.burger.controller.adapter.api.ReportController;
import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.employee.EmployeeType;
import com.fiap.burger.entity.report.Report;
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
