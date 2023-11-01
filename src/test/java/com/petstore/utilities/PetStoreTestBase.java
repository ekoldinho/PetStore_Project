package com.petstore.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class PetStoreTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "https://petstore.swagger.io/v2";

    }

    @AfterAll
    public static void destroy() {
        reset();
    }
}
