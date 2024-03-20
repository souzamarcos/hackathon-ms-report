package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.WorkingHourGateway;
import com.fiap.burger.usecase.adapter.usecase.ReportUseCase;
import com.fiap.burger.usecase.usecase.DefaultReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ReportUseCase reportUseCase(WorkingHourGateway workingHourGateway) {
        return new DefaultReportUseCase(workingHourGateway);
    }
}
