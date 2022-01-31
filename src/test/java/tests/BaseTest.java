package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import utils.ConfVariables;


public abstract class BaseTest {

    private static final Logger logger = LogManager.getLogger(ReqResTests.class);

    @BeforeClass
    public static void setUp() {
        logger.info("Setting up the base test");
        RestAssured.requestSpecification = defaultRequestSpecification();
        logger.info("Base test setup complete");
    }

    public static RequestSpecification defaultRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri(ConfVariables.getHost())
                .setBasePath(ConfVariables.getPath())
                .setContentType(ContentType.JSON).build();
    }

}
