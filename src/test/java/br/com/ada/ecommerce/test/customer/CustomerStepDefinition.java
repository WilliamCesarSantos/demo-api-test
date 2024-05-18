package br.com.ada.ecommerce.test.customer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerStepDefinition {

    @Given("cliente não esta presente na base")
    public void customerNotExist() {

    }

    @When("cadastro o cliente")
    public void registerCustomer() {

    }

    @When("cadastro o cliente sem informar o nome")
    public void registerCustomerWithoutName() {

    }

    @Then("encontro o cliente cadastrado")
    public void searchCustomer() {

    }

    @Then("erro no cadastro")
    public void errorOnRegister(Integer status) {
        System.out.println(status);
    }

}
