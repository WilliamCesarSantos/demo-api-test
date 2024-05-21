package br.com.ada.ecommerce.test.product;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class ProductStepDefinition {

    protected RequestSpecification request;
    protected Response response;
    private ProductDto product;

    public ProductStepDefinition() {
        request = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON);

        product = new ProductDto();
        product.setBarcode(RandomStringUtils.randomAlphabetic(47));
        product.setDescription(RandomStringUtils.randomAlphabetic(20));
        product.setPrice(new BigDecimal(RandomStringUtils.randomNumeric(2)));
    }

    @Given("product is new")
    public void createNewProduct() {
        System.out.println("ignoring...");
    }

    @Given("product already registered")
    public void productIsRegistered() {
        var response = request.body(product).when().post("/products");
        var id = response.jsonPath().getLong("id");
        product.setId(id);
    }

    @When("I post product")
    public void registerProduct() {
        response = request.body(product).when().post("/products");
    }

    @When("I search for barcode")
    public void searchByBarcode() {
        response = request.when().get("/products?barcode=" + product.getBarcode());
    }

    @Then("product was registered")
    public void productWasRegistered() {
        response.then().statusCode(201);
    }

    @Then("product with barcode was found")
    public void foundProduct(){
        var id = response.jsonPath().getLong("[0].id");
        Assertions.assertEquals(product.getId(), id);
    }

    @And("response should status equals {int}")
    public void responseShouldHaveStatusCode(Integer status) {
        response.then().statusCode(status);
    }

}
