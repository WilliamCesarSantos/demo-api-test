package br.com.ada.ecommerce.test.product;

import br.com.ada.ecommerce.test.http.HttpSession;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class ProductStepDefinition {

    private ProductDto product;
    private HttpSession httpSession;

    public ProductStepDefinition(HttpSession httpSession) {
        this.httpSession = httpSession;

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
        this.httpSession.post("/products", product);
        var id = this.httpSession.getResponse().jsonPath().getLong("id");
        product.setId(id);
    }

    @When("I post product")
    public void registerProduct() {
        this.httpSession.post("/products", product);
    }

    @When("I search for barcode")
    public void searchByBarcode() {
        this.httpSession.get("/products?barcode=" + product.getBarcode());
    }

    @Then("product was registered")
    public void productWasRegistered() {
        this.httpSession.getResponse().then().statusCode(201);
    }

    @Then("product with barcode was found")
    public void foundProduct() {
        var id = this.httpSession.getResponse().jsonPath().getLong("[0].id");
        Assertions.assertEquals(product.getId(), id);
    }

}
