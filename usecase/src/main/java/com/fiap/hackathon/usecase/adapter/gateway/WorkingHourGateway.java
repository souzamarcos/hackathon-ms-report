package com.fiap.hackathon.usecase.adapter.gateway;

import com.fiap.hackathon.entity.workingHour.WorkingHour;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkingHourGateway {
    List<WorkingHour> findByEmployeeIdAndBetweenStartDateAndEndDate(String employeeId, LocalDateTime startDate, LocalDateTime endDate);
}
