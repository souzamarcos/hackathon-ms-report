package com.fiap.burger.gateway.workingHour.gateway;

import com.fiap.burger.entity.workingHour.WorkingHour;
import com.fiap.burger.gateway.workingHour.dao.WorkingHourDAO;
import com.fiap.burger.gateway.workingHour.model.WorkingHourJPA;
import com.fiap.burger.usecase.adapter.gateway.WorkingHourGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class DefaultWorkingHourGateway implements WorkingHourGateway {
    @Autowired
    WorkingHourDAO dao;

    @Override
    public List<WorkingHour> findByEmployeeIdAndBetweenStartDateAndEndDate(String employeeId, LocalDateTime startDate, LocalDateTime endDate) {
        return dao.findBetweenStartDateAndEndDate(employeeId, startDate, endDate)
            .stream()
            .map(WorkingHourJPA::toWorkingHour)
            .toList();
    }
}
