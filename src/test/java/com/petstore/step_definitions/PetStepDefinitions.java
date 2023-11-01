package com.petstore.step_definitions;

import com.petstore.utilities.PetStoreTestBase;
import com.pojo.Pet;
import com.pojo.PetForUpdate;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import java.io.File;

import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStepDefinitions extends PetStoreTestBase {

    static long petID;
    static String authorizationHeader = "api_key";
    static String authorizationApiKey = "42265ac65554js";

    @Order(1)
    @Test
    @When("A new pet is to be added to the store")
    public void aNewPetIsToBeAddedToTheStore() {

    Pet pet = new Pet();

    pet.setId2(1);
    pet.setName1("dog");
    pet.setName2("max");
    pet.setPhotoUrls("string");
    pet.setId3(1);
    pet.setName3("string");
    pet.setStatus("available");

        petID = given().log().body()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(authorizationHeader, authorizationApiKey)
                .body(pet + "").when().post("/pet").then().statusCode(200).extract().jsonPath().getLong("id");
    }
    @Order(2)
    @Test
    @And("An image to the pet is to be uploaded")
    public void anImageToThePetIsToBeUploaded() {

        File file = new File("C:\\Users\\aliek\\OneDrive\\Documents\\New Folder\\download.jpg");

        given()
                .pathParam("petId",petID)
                .header(authorizationHeader, authorizationApiKey)
                .multiPart("file", file, "multipart/form-data")
                .when().post("/pet/{petId}/uploadImage").then().statusCode(200);

    }
    @Order(3)
    @Test
    @And("The pet is to be updated")
    public void thePetIsToBeUpdated() {

        PetForUpdate petForUpdate = new PetForUpdate();

        petForUpdate.setId1(petID);
        petForUpdate.setId2(1);
        petForUpdate.setName1("dog");
        petForUpdate.setName2("max");
        petForUpdate.setPhotoUrls("string");
        petForUpdate.setId3(1);
        petForUpdate.setName3("string");
        petForUpdate.setStatus("pending");

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(authorizationHeader, authorizationApiKey)
                .body(petForUpdate + "").when().put("/pet").then().statusCode(200).extract().response();
    }
    @Order(4)
    @Test
    @And("Pets are to be found by status")
    public void petsAreToBeFoundByStatus() {

        Response response = given().accept(ContentType.JSON)
                .header(authorizationHeader, authorizationApiKey)
                .queryParam("status", "available")
                .when().get("/pet/findByStatus")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("response.asString() = " + response.asString());

    }
    @Order(5)
    @Test
    @And("A pet is to be found by ID")
    public void aPetIsToBeFoundByID() {
        Response response = given().accept(ContentType.JSON)
                .header(authorizationHeader, authorizationApiKey)
                .pathParam("petId", petID)
                .when().get("/pet/{petId}")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("response.asString() = " + response.asString());
    }
    @Order(6)
    @Test
    @And("The pet is to be updated with form data")
    public void thePetIsToBeUpdatedWithFormData() {

        given().accept(ContentType.JSON)
                .header(authorizationHeader, authorizationApiKey)
                .contentType("application/x-www-form-urlencoded")
                .pathParam("petId", petID)
                .formParam("name", "max")
                .formParam("status", "sold")
                .when().post("/pet/{petId}")
                .then().statusCode(200);
    }
    @Order(7)
    @Test
    @Then("The pet is to be deleted")
    public void thePetIsToBeDeleted() {

        given().pathParam("id", petID)
                .accept(ContentType.JSON)
                .header(authorizationHeader, authorizationApiKey)
                .when().delete("/pet/{id}").then().statusCode(200);
    }


}
