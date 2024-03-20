package com.fiap.hackathon.gateway.workingHour.gateway;

import com.fiap.hackathon.entity.workingHour.WorkingHour;
import com.fiap.hackathon.gateway.workingHour.dao.WorkingHourDAO;
import com.fiap.hackathon.gateway.workingHour.model.WorkingHourJPA;
import com.fiap.hackathon.usecase.adapter.gateway.WorkingHourGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
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
