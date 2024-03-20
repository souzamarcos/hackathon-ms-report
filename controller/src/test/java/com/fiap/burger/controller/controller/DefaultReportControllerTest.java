package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.employee.EmployeeType;
import com.fiap.burger.entity.report.Report;
import com.fiap.burger.usecase.adapter.usecase.ReportUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DefaultReportControllerTest {

    @Mock
    ReportUseCase useCase;

    @InjectMocks
    DefaultReportController controller;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class preview {
        @Test
        void shouldPreview() {
            var employee = new Employee("ABC001", "email@email.com", "Nome", EmployeeType.USER);
            var startDate = LocalDateTime.now();
            var endDate = LocalDateTime.now().plusDays(1L);
            var expected = new Report();

            when(useCase.preview(employee, startDate, endDate)).thenReturn(expected);

            Report actual = controller.preview(employee.getId(), startDate, endDate);

            assertEquals(expected, actual);

            verify(useCase, times(1)).preview(employee, startDate, endDate);
        }
    }

    @Nested
    class export {
        @Test
        void shouldExport() {
            var employee = new Employee("ABC001", "email@email.com", "Nome", EmployeeType.USER);
            var startDate = LocalDateTime.now();
            var endDate = LocalDateTime.now().plusDays(1L);
            var expected = "Report sent sucessfully.";

            when(useCase.export(employee, startDate, endDate)).thenReturn(expected);

            String actual = controller.export(employee.getId(), startDate, endDate);

            assertEquals(expected, actual);

            verify(useCase, times(1)).export(employee, startDate, endDate);
        }
    }
}
