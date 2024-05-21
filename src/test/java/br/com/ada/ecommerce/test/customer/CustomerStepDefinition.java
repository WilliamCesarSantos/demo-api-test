package br.com.ada.ecommerce.test.customer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class CustomerStepDefinition {

    private CustomerDto customer = null;
    protected RequestSpecification request;
    protected Response response;

    @Given("cliente com documento igual a {string} e não cadastrado")
    public void customerNotExist(String document) {
        customer = new CustomerDto();
        customer.setDocument(document);
        customer.setName(RandomStringUtils.randomAlphabetic(20));
        customer.setBirthDate(LocalDate.now());
        customer.setTelephone(List.of("111111111"));
        customer.setEmail(List.of("api@test.com"));
    }

    @Given("cliente já estiver cadastrado")
    public void registerNewCustomer() {
        customer = new CustomerDto();
        customer.setDocument(RandomStringUtils.randomNumeric(11));
        customer.setName(RandomStringUtils.randomAlphabetic(20));
        customer.setBirthDate(LocalDate.now());
        customer.setTelephone(List.of("111111111"));
        customer.setEmail(List.of("api@test.com"));
        response = request.body(customer).when().post("/customers");

        response.then().statusCode(201);
        var id = response.jsonPath().getLong("id");
        customer.setId(id);
    }

    @When("cadastro o cliente")
    public void registerCustomer() {
        response = request.body(customer).when().post("/customers");
    }

    @When("cadastro o cliente sem informar o nome")
    public void registerCustomerWithoutName() {
        customer = new CustomerDto();
        customer.setDocument(RandomStringUtils.randomNumeric(11));
        customer.setBirthDate(LocalDate.now());
        customer.setTelephone(List.of("111111111"));
        customer.setEmail(List.of("api@test.com"));
        response = request.body(customer).when().post("/customers");
    }

    @When("cadastro o cliente sem informar o document")
    public void registerCustomerWithoutDocument() {

    }

    @When("pesquisar o cliente pelo nome")
    public void searchCustomerByName() {

    }

    @Then("encontro o cliente cadastrado")
    public void searchCustomer() {
        var response = request.when().get("/customers?name=" + customer.getName());
        response.then().statusCode(200);
        var name = response.jsonPath().get("[0].name");
        Assertions.assertEquals(customer.getName(), name);
    }

    @Then("erro no cadastro {int}")
    public void errorOnRegister(Integer status) {
        System.out.println(status);
    }

    @Then("deve retornar o cliente dentro da lista")
    public void checkIfCustomerIsPresent() {

    }

}
