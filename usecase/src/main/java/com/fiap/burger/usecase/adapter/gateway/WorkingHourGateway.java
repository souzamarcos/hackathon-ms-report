package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.workingHour.WorkingHour;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkingHourGateway {
    List<WorkingHour> findByEmployeeIdAndBetweenStartDateAndEndDate(String employeeId, LocalDateTime startDate, LocalDateTime endDate);
}
