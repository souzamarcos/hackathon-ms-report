package com.fiap.burger.gateway.workingHour.gateway;

import com.fiap.burger.entity.workingHour.WorkingHour;
import com.fiap.burger.gateway.workingHour.dao.WorkingHourDAO;
import com.fiap.burger.gateway.workingHour.model.WorkingHourJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DefaultWorkingHourGatewayTest {

    @Mock
    WorkingHourDAO dao;

    @InjectMocks
    DefaultWorkingHourGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindBetweenStartDateAndEndDate() {
        var employeeId = "ABC001";
        var startDate = LocalDateTime.now();
        var endDate = LocalDateTime.now();
        var workingHourJPA = new WorkingHourJPA("ABC001", startDate.plusDays(1L));
        List<WorkingHour> expected = new ArrayList<>(Arrays.asList(
           new WorkingHour("ABC001", startDate.plusDays(1L))
        ));


        when(dao.findBetweenStartDateAndEndDate(employeeId, startDate, endDate)).thenReturn(new ArrayList<>(Arrays.asList(workingHourJPA)));

        List<WorkingHour> actual = gateway.findByEmployeeIdAndBetweenStartDateAndEndDate(employeeId, startDate, endDate);

        assertEquals(expected, actual);

        verify(dao, times(1)).findBetweenStartDateAndEndDate(employeeId, startDate, endDate);
    }

}
