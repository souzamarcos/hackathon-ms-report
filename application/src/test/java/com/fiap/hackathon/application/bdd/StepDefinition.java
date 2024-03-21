package com.fiap.hackathon.application.bdd;

import com.fiap.hackathon.api.dto.report.response.ReportResponseDto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;


public class StepDefinition extends CucumberIntegrationTest {
    @Autowired
    DataSource ds;
    private Response response;

    private ReportResponseDto reportResponseDto;
    private String getEndpoint() { return "http://localhost:" + port + "/reports"; }

    @Dado("que as horas de trabalho do funcionario já existam")
    public void queHorasDeTrabalhoFuncionarioExistam() {
        try (Connection conn = ds.getConnection()) {
            conn.createStatement().execute("delete from working_hour");
            conn.createStatement().execute("insert into working_hour values (1, 'ABC001', '2024-03-16 08:00:00')");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Quando("requisitar o relatorio de horas de trabalho do funcionario")
    public void requisitarRelatorioHorasDeTrabalhoFuncionario() {
        var bearerToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImlzcyI6IlRFU1QtSVNTVUVSIiwiaWQiOiJBQkMwMDEiLCJlbWFpbCI6ImVtYWlsQGdtYWlsLmNvbSIsIm5hbWUiOiJOb21lIiwidHlwZSI6MCwiaWF0IjoxNzA2NDEzNDMyLCJqdGkiOiJmMDQyM2U0Yy03YzEwLTRiMzUtOGQ5NS1kYmZhZmMzY2ZkYTQifQ._yL06ZjOO0k2Iin1udWAeou-MYVukcZ9XVOsJScVNiU";
        var startDate = "2024-03-16T00:00:00";
        var endDate = "2024-03-20T00:00:00";
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .headers(
                "Authorization",
                "Bearer " + bearerToken,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
            .when()
            .get(getEndpoint() + "/preview?startDate={startDate}&endDate={endDate}", startDate, endDate);
    }
    @Entao("o relatorio é gerado com sucesso")
    public void relatorioGeradoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }

}
