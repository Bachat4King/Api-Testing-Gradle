package tests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import utils.Token;

public class Test01_GET {

    @Test
    public void test01_GET() {
       // create a GET request to Nasa API and print the response


        ValidatableResponse response = RestAssured.given()
                .get("https://api.nasa.gov/planetary/apod?api_key="+ Token.getToken()).then().statusCode(200);

        System.out.println("-----");
        System.out.println(response.extract().response().prettyPrint());

        //Response response = RestAssured.get("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        //assert response.getStatusCode() == 200;
       // System.out.println(response.getBody().prettyPrint());
        // search for the "hdurl" key in the response and print the value using the getPath() method
       // System.out.println((String) response.jsonPath().get("hdurl"));
        
    }


}


