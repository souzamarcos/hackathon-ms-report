package com.fiap.hackathon.gateway.email.gateway;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import com.fiap.hackathon.entity.report.Report;
import com.fiap.hackathon.usecase.misc.exception.SendEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DefaultEmailGatewayTest {

    @Mock
    JavaMailSender javaMailSender;

    @InjectMocks
    DefaultEmailGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrownSendEmailExceptionWhenExceptionHappens() {
        var report = new Report(new Employee("ABC001", "Nome", "email@email.com", EmployeeType.USER), 10L, null);

        SimpleMailMessage message = gateway.buildMessage(report);

        willThrow(new MailAuthenticationException("Message")).given(javaMailSender).send(message);

        assertThrows(SendEmailException.class, () -> gateway.sendEmail(report));

        verify(javaMailSender, times(1)).send(message);
    }

}
