package br.com.ada.ecommerce.test.customer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public class CustomerStepDefinition {

    private RequestSpecification request = RestAssured.given()
            .baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);
    private Response response = null;
    private CustomerDto customer = null;

    @Given("cliente não esta presente na base")
    public void customerNotExist() {
        customer = new CustomerDto();
        customer.setDocument("100");
        customer.setName(RandomStringUtils.randomAlphabetic(20));
//        customer.setBirthDate(LocalDate.now());
        customer.setTelephone(List.of("111111111"));
        customer.setEmail(List.of("api@test.com"));
    }

    @Given("cliente já existiver cadastrado")
    public void registerNewCustomer() {

    }

    @When("cadastro o cliente")
    public void registerCustomer() {
        response = request.body(customer).when().post("/customers");
    }

    @When("cadastro o cliente sem informar o nome")
    public void registerCustomerWithoutName() {

    }

    @When("cadastro o cliente sem informar o document")
    public void registerCustomerWithoutDocument() {

    }

    @When("pesquisar o cliente pelo nome")
    public void searchCustomerByName() {

    }

    @Then("encontro o cliente cadastrado")
    public void searchCustomer() {
        var response = request.when().get("/customers?name="+customer.getName());
        response.then().statusCode(200);
        var name = response.jsonPath().get("$.[0].name");
        Assertions.assertEquals(customer.getName(), name);
    }

    @Then("erro no cadastro {int}")
    public void errorOnRegister(Integer status) {
        System.out.println(status);
    }

    @Then("deve retornar o cliente dentro da lista")
    public void checkIfCustomerIsPresent() {

    }

    @And("a requisição deve ter status igual a {int}")
    public void checkStatusInResponse(Integer status) {
        response.then().statusCode(status);
    }

}
