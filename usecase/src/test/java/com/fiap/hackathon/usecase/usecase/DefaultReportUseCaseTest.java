package com.fiap.hackathon.usecase.usecase;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.entity.report.ReportDate;
import com.fiap.hackathon.entity.report.ReportDateInterval;
import com.fiap.hackathon.entity.workingHour.WorkingHour;
import com.fiap.hackathon.usecase.adapter.gateway.EmailGateway;
import com.fiap.hackathon.usecase.adapter.gateway.WorkingHourGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DefaultReportUseCaseTest {
    @Mock
    WorkingHourGateway workingHourGateway;

    @Mock
    EmailGateway emailGateway;

    @InjectMocks
    DefaultReportUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class preview {
        @Test
        void testPreview() {
            var employee = new Employee("ABC001", "email@email.com", "Nome", EmployeeType.USER);
            var startDate = LocalDateTime.of(2024, 3, 16, 0, 0, 0);
            var endDate = LocalDateTime.of(2024, 3, 19, 23, 59, 59);
            var expected = expectedReport01(employee);

            when(workingHourGateway.findByEmployeeIdAndBetweenStartDateAndEndDate(employee.getId(), startDate, endDate)).thenReturn(mockWorkingHours01());

            Report actual = useCase.preview(employee, startDate, endDate);

            assertEquals(expected, actual);

            verify(workingHourGateway, times(1)).findByEmployeeIdAndBetweenStartDateAndEndDate(employee.getId(), startDate, endDate);
        }
    }

    @Nested
    class export {
        @Test
        void testPreview() {
            var employee = new Employee("ABC001", "email@email.com", "Nome", EmployeeType.USER);
            var startDate = LocalDateTime.of(2024, 3, 16, 0, 0, 0);
            var endDate = LocalDateTime.of(2024, 3, 19, 23, 59, 59);
            var expected = "Email sent sucessfully!";

            when(workingHourGateway.findByEmployeeIdAndBetweenStartDateAndEndDate(employee.getId(), startDate, endDate)).thenReturn(mockWorkingHours01());

            String actual = useCase.export(employee, startDate, endDate);

            assertEquals(expected, actual);

            verify(workingHourGateway, times(1)).findByEmployeeIdAndBetweenStartDateAndEndDate(employee.getId(), startDate, endDate);
            verify(emailGateway, times(1)).sendEmail(expectedReport01(employee));
        }
    }

    private List<WorkingHour> mockWorkingHours01() {
        List<WorkingHour> workingHours = new ArrayList<>();
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 16, 8, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 16, 12, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 16, 13, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 16, 18, 0, 0)));

        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 17, 8, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 17, 12, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 17, 13, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 17, 18, 0, 0)));

        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 18, 8, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 18, 10, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 18, 10, 30, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 18, 12, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 18, 13, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 18, 18, 0, 0)));

        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 19, 8, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 19, 12, 0, 0)));
        workingHours.add(new WorkingHour("ABC001", LocalDateTime.of(2024, 3, 19, 18, 0, 0)));

        return workingHours;
    }

    private Report expectedReport01(Employee employee) {
        Report report = new Report();
        report.setEmployee(employee);
        report.setTotal(1830L);
        report.setDates(new ArrayList<>(Arrays.asList(
            new ReportDate(
                LocalDateTime.of(2024,  3, 16, 0, 0, 0),
                540L,
                false,
                new ArrayList<>(Arrays.asList(
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 16, 8, 0, 0),
                        LocalDateTime.of(2024, 3, 16, 12, 0, 0),
                        240L,
                        false
                    ),
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 16, 13, 0, 0),
                        LocalDateTime.of(2024, 3, 16, 18, 0, 0),
                        300L,
                        false
                    )
                ))
            ),
            new ReportDate(
                LocalDateTime.of(2024, 3, 17, 0, 0, 0),
                540L,
                false,
                new ArrayList<>(Arrays.asList(
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 17, 8, 0, 0),
                        LocalDateTime.of(2024, 3, 17, 12, 0, 0),
                        240L,
                        false
                    ),
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 17, 13, 0, 0),
                        LocalDateTime.of(2024, 3, 17, 18, 0, 0),
                        300L,
                        false
                    )
                ))
            ),
            new ReportDate(
                LocalDateTime.of(2024, 3, 18, 0, 0, 0),
                510L,
                false,
                new ArrayList<>(Arrays.asList(
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 18, 8, 0, 0),
                        LocalDateTime.of(2024, 3, 18, 10, 0, 0),
                        120L,
                        false
                    ),
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 18, 10, 30, 0),
                        LocalDateTime.of(2024, 3, 18, 12, 0, 0),
                        90L,
                        false
                    ),
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 18, 13, 0, 0),
                        LocalDateTime.of(2024, 3, 18, 18, 0, 0),
                        300L,
                        false
                    )
                ))
            ),
            new ReportDate(
                LocalDateTime.of(2024, 3, 19, 0, 0, 0),
                240L,
                true,
                new ArrayList<>(Arrays.asList(
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 19, 8, 0, 0),
                        LocalDateTime.of(2024, 3, 19, 12, 0, 0),
                        240L,
                        false
                    ),
                    new ReportDateInterval(
                        LocalDateTime.of(2024, 3, 19, 18, 0, 0),
                        null,
                        0L,
                        true
                    )
                ))
            )
        )));

        return report;
    }
}
