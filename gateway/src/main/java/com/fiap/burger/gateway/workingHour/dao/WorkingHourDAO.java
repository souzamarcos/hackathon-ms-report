package com.fiap.burger.gateway.workingHour.dao;

import com.fiap.burger.gateway.product.model.ProductJPA;
import com.fiap.burger.gateway.workingHour.model.WorkingHourJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkingHourDAO extends JpaRepository<ProductJPA, Long> {
    @Query("select wh FROM WorkingHourJPA wh WHERE wh.employeeId = ?1 wh.registryDateTime >= ?2 and wh.registryDateTime <= ?3")
    List<WorkingHourJPA> findBetweenStartDateAndEndDate(String employeeId, LocalDateTime startDate, LocalDateTime endDate);
}
