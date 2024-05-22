package br.com.ada.ecommerce.test.http;

import io.cucumber.java.en.And;

public class HttpStepDefinition {

    private HttpSession httpSession;

    public HttpStepDefinition(HttpSession httpSession){
        this.httpSession = httpSession;
    }

    @And("response should status equals {int}")
    public void responseShouldHaveStatusCode(Integer status) {
        httpSession.getResponse().then().statusCode(status);
    }

}
