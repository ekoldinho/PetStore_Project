package com.petstore.step_definitions;

import com.petstore.utilities.PetStoreTestBase;
import com.pojo.Store;
import com.pojo.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserStepDefinitions extends PetStoreTestBase {

    @Order(1)
    @Test
    @When("Creating list of users with given input array")
    public void creatingListOfUsersWithGivenInputArray() {

        User user1 = new User();
        user1.setInfo(1532, "tester1", "wooden", "spoon", "tester1@gmail.com", "tester1", "565498415613", 2);
        User user2 = new User();
        user1.setInfo(1533, "tester2", "qa", "engineer", "tester2@gmail.com", "tester2", "53165132856", 1);
        User user3 = new User();
        user1.setInfo(1534, "tester3", "jane", "doe", "tester3@gmail.com", "tester3", "65124986456", 2);

        ArrayList<User> userList =new ArrayList<>();
        userList.addAll(Arrays.asList(user1, user2, user3));

        System.out.println("userList = " + userList);

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(userList).when().post("/user/createWithList")
                .then().statusCode(200);
    }
    @Order(2)
    @Test
    @And("Creating one single user")
    public void creatingOneSingleUser() {

        User user4 = new User();
        user4.setInfo(1535, "user4", "joe", "doe", "user4@gmail.com", "user4", "56498456132", 2);

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(user4).when().post("/user").then().statusCode(200);


    }
    @Order(3)
    @Test
    @And("Calling a user by user name")
    public void callingAUserByUserName() {

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", "user2")
                .when().get("/user/{username}")
                .then().statusCode(200).extract().response();

        System.out.println("response.asString() = " + response.asString());

    }
    @Order(4)
    @Test
    @And("Logging user into the system")
    public void loggingUserIntoTheSystem() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("username", "user4");
        queryMap.put("password", "user4");

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParams(queryMap)
                .when().get("/user/login");

    }
    @Order(5)
    @Test
    @And("Logging user out of the system")
    public void loggingUserOutOfTheSystem() {

            given().when().get("/user/logout").then().statusCode(200);
    }
    @Order(6)
    @Test
    @Then("Deleting the user")
    public void deletingTheUser() {

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", "user4")
                .when().delete("/user/{username}")
                .then().statusCode(200);

    }
}
