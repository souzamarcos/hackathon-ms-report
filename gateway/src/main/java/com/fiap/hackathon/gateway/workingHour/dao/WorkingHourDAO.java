package com.fiap.hackathon.gateway.workingHour.dao;

import com.fiap.hackathon.gateway.workingHour.model.WorkingHourJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkingHourDAO extends JpaRepository<WorkingHourJPA, Long> {
    @Query(value ="select wh FROM WorkingHourJPA wh WHERE wh.employeeId = :employeeId and wh.registryDateTime >= :startDate and wh.registryDateTime <= :endDate",
    nativeQuery = false)
    List<WorkingHourJPA> findBetweenStartDateAndEndDate(@Param("employeeId") String employeeId,
                                                        @Param("startDate") LocalDateTime startDate,
                                                        @Param("endDate") LocalDateTime endDate);
}
