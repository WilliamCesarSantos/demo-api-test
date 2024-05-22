package br.com.ada.ecommerce.test.http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpSession {

    protected RequestSpecification request;
    protected Response response;

    public HttpSession() {
        request = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON);
    }

    public void post(String path, Object object) {
        response = request.body(object).when().post(path);
    }

    public void get(String path) {
        response = request.when().get(path);
    }

    public Response getResponse() {
        return response;
    }

}
