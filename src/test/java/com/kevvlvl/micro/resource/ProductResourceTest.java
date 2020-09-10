package com.kevvlvl.micro.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testGetProducts() {
        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .body(is(notNullValue()));
    }
}
