package com.fiap.burger.application.bdd;

import com.fiap.burger.api.dto.report.response.ReportResponseDto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;


public class StepDefinition extends CucumberIntegrationTest {

    private Response response;

    private ReportResponseDto reportResponseDto;
    private String getEndpoint() { return "http://localhost:" + port + "/reports"; }

    @Dado("que as horas de trabalho do funcionario já existam")
    public void queHorasDeTrabalhoFuncionarioExistam() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("requisitar o relatorio de horas de trabalho do funcionario")
    public void requisitarRelatorioHorasDeTrabalhoFuncionario() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "?employeeId={id}&startDate={startDate}&endDate={endDate}", "ABC001", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }
    @Entao("o relatorio é gerado com sucesso")
    public void relatorioGeradoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }

}
