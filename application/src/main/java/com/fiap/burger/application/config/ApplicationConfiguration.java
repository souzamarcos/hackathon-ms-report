package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import com.fiap.burger.usecase.adapter.gateway.WorkingHourGateway;
import com.fiap.burger.usecase.adapter.usecase.ProductUseCase;
import com.fiap.burger.usecase.adapter.usecase.ReportUseCase;
import com.fiap.burger.usecase.usecase.DefaultProductUseCase;
import com.fiap.burger.usecase.usecase.DefaultReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ProductUseCase productUseCase(ProductGateway productGateway) {
        return new DefaultProductUseCase(productGateway);
    }

    @Bean
    public ReportUseCase reportUseCase(WorkingHourGateway workingHourGateway) {
        return new DefaultReportUseCase(workingHourGateway);
    }
}
