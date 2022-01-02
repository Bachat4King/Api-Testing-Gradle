package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReqResTests {


    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build();
    }

    @Test
    public void postLoginTest() {
        given()
        .body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}")
        .post("login")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body("token", notNullValue());
    }

    @Test
    public void getSingleUserTest(){
        given()
        .get("users/2")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body("data.id", equalTo(2));
    }

    @Test
    public void deleteUserTest(){
        given()
                .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void patchUserTest(){
        String nameUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("name");

                assertThat(nameUpdated, equalTo("morpheus"));
    }

    @Test
    public void putUserTest() {
       String job =  given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .put("users/s")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("job");

        assertThat(job, equalTo("zion resident"));
    }

}
