package com.fiap.hackathon.usecase.adapter.gateway;

import com.fiap.hackathon.entity.report.Report;

public interface EmailGateway {
    void sendEmail(Report report);
}
