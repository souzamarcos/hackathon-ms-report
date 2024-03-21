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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
        var employee = new Employee("ABC001", "email@email.com", "Nome", EmployeeType.USER);
        var startDate = LocalDateTime.now();
        var endDate = LocalDateTime.now();
        var report = new Report(
            employee,
            0L,
            null);
        var expected = ReportResponseDto.toResponseDto(report);

        when(controller.preview(employee, startDate, endDate)).thenReturn(report);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getCredentials()).thenReturn(employee);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        ReportResponseDto actual = api.preview(startDate, endDate);

        assertEquals(expected, actual);

        verify(controller, times(1)).preview(employee, startDate, endDate);
    }

    @Test
    void shouldExport() {
        var employee = new Employee("ABC001", "email@email.com", "Nome", EmployeeType.USER);
        var startDate = LocalDateTime.now();
        var endDate = LocalDateTime.now();
        var expected = "Report sent sucessfully.";

        when(controller.export(employee, startDate, endDate)).thenReturn(expected);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getCredentials()).thenReturn(employee);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        String actual = api.export(startDate, endDate);

        assertEquals(expected, actual);

        verify(controller, times(1)).export(employee, startDate, endDate);
    }

}
