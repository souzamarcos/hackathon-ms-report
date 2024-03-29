package com.fiap.hackathon.application.bdd;

import com.fiap.hackathon.application.config.CucumberTestConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = CucumberTestConfig.class
)
@TestPropertySource("classpath:application-cucumber.properties")
@ActiveProfiles("test")
public class CucumberIntegrationTest {

    @LocalServerPort
    protected int port;

}
