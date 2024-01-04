package com.fiap.burger.application.bdd;

import com.fiap.burger.api.dto.product.response.ProductResponseDto;
import com.fiap.burger.application.utils.ProductHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition {

    private Response response;

    private ProductResponseDto productResponse;

    private String ENDPOINT_PRODUCTS = "http://localhost:8080/products";

    @Quando("submeter um novo produto")
    public ProductResponseDto submeterUmNovoProduto() {
        var productRequest = ProductHelper.createProductRequest();
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(productRequest)
            .when().post(ENDPOINT_PRODUCTS);
        return response.then().extract().as(ProductResponseDto.class);
    }
    @Entao("a mensagem é registrada com sucesso")
    public void mensagemRegistradaComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("./schemas/ProductResponseSchema.json"));
    }

    @Dado("que um produto já foi cadastrado")
    public void produtoJaCadastrado() {
        productResponse = submeterUmNovoProduto();
    }

    @Quando("requisitar a busca de um produto por id")
    public void requisitarBuscaDeProdutoPorId() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(ENDPOINT_PRODUCTS + "/{id}", productResponse.id().toString());
    }

    @Entao("a mensagem é exibida com sucesso")
    public void mensagemExibidaComSucesso() {
        // Write code here that turns the phrase above into concrete actions
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("./schemas/ProductResponseSchema.json"));
    }

    @Quando("requisitar a alteração do produto")
    public void requisitarAlteracaoDoProduto() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(ProductHelper.updateProductRequest(productResponse))
            .when()
            .put(ENDPOINT_PRODUCTS);
    }

    @Entao("a mensagem é atualizada com sucesso")
    public void mensagemAtualizadaComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("./schemas/ProductResponseSchema.json"));
    }

    @Quando("requisitar a exclusao do produto")
    public void requisitarExclusaoDoProduto() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .delete(ENDPOINT_PRODUCTS + "/{id}", productResponse.id().toString());
    }

    @Entao("a mensagem é excluída com sucesso")
    public void mensagemExcluidaComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(equalTo("Product has been successfully deleted."));
    }

    @Quando("requisitar a listagem de produtos por categoria")
    public void requisitarListagemDeProdutosPorCategoria() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(ENDPOINT_PRODUCTS + "?category=LANCHE");
    }

    @Entao("as mensagens são exibidas com sucesso")
    public void mensagensSaoExibidasComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("./schemas/ListProductResponseSchema.json"));
    }
}
