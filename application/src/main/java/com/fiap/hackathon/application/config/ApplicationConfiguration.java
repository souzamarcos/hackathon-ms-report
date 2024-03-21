package com.fiap.hackathon.application.config;

import com.fiap.hackathon.usecase.adapter.gateway.EmailGateway;
import com.fiap.hackathon.usecase.adapter.gateway.WorkingHourGateway;
import com.fiap.hackathon.usecase.adapter.usecase.ReportUseCase;
import com.fiap.hackathon.usecase.usecase.DefaultReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ReportUseCase reportUseCase(WorkingHourGateway workingHourGateway,
                                       EmailGateway emailGateway) {
        return new DefaultReportUseCase(workingHourGateway, emailGateway);
    }
}
