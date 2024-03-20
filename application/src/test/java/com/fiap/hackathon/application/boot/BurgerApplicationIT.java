package com.fiap.hackathon.application.boot;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
class BurgerApplicationIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() throws Exception {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveAplicacaoIniciarCorretamente() {
        given()
            .when()
            .get("/health")
            .then()
            .statusCode(HttpStatus.OK.value());
        ;
    }
}
