package com.petstore.step_definitions;

import com.petstore.utilities.PetStoreTestBase;
import com.pojo.Store;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreStepDefinitions extends PetStoreTestBase {
    static long purchaseID;
    static String authorizationHeader = "api_key";
    static String authorizationApiKey = "42265ac65554js";

    @Order(1)
    @Test
    @When("A new order for a pet has been placed")
    public void aNewOrderForAPetHasBeenPlaced() {

        Store store = new Store();
        store.setPetId(5215);
        store.setQuantity(3);
        store.setShipDate("2023-11-01T08:46:59.529Z");
        store.setStatus("placed");
        store.setComplete(true);

        purchaseID = given().log().body().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(store).when().post("/store/order").then()
                .statusCode(200).extract().jsonPath().getLong("id");

        System.out.println("purchaseID = " + purchaseID);


    }
    @Order(2)
    @Test
    @And("Purchase order has been found by ID")
    public void purchaseOrderHasBeenFoundByID() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("orderId", purchaseID)
                .when().get("/store/order/{orderId}")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().response();

        System.out.println("response.asString() = " + response.asString());


    }
    @Order(3)
    @Test
    @And("Pet inventories are returned")
    public void petInventoriesAreReturned() {
        Response response = given().accept(ContentType.JSON)
                .header(authorizationHeader, authorizationApiKey)
                .when().get("/store/inventory")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().response();

        System.out.println("response.asString() = " + response.asString());
    }

    @Order(4)
    @Test
    @Then("Purchase order has been deleted by ID")
    public void purchaseOrderHasBeenDeletedByID() {

        given().accept(ContentType.JSON)
                .pathParam("orderId", purchaseID)
                .when().delete("/store/order/{orderId}").then().statusCode(200);

    }
}
