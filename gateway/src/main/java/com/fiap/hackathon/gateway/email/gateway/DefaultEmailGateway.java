package com.fiap.hackathon.gateway.email.gateway;

import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.gateway.email.dto.response.ReportEmailDto;
import com.fiap.hackathon.usecase.adapter.gateway.EmailGateway;
import com.fiap.hackathon.usecase.misc.exception.SendEmailException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class DefaultEmailGateway implements EmailGateway {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmail(Report report) {
        try {
            SimpleMailMessage message = buildMessage(report);
            emailSender.send(message);
        } catch (Exception e) {
            throw new SendEmailException(e);
        }
    }

    protected SimpleMailMessage buildMessage(Report report) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@hackathon.com");
        message.setTo(report.getEmployee().getEmail());
        message.setSubject("Relatorio - Hackathon");
        message.setText(getMessage(report));
        return message;
    }

    private String getMessage(Report report) {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        return gson.toJson(ReportEmailDto.toResponseDto(report));
    }
}
