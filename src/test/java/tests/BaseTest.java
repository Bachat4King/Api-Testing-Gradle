package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import utils.ConfVariables;
import io.restassured.filter.Filter;
import io.qameta.allure.restassured.AllureRestAssured;


import java.util.ArrayList;
import java.util.List;


public abstract class BaseTest {

    private static final Logger logger = LogManager.getLogger(ReqResTests.class);

    @BeforeClass
    public static void setUp() {
        logger.info("Setting up the base test");
        RestAssured.requestSpecification = defaultRequestSpecification();
        logger.info("Base test setup complete");
    }

    public static RequestSpecification defaultRequestSpecification() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());
        filters.add(new AllureRestAssured());

        return new RequestSpecBuilder().setBaseUri(ConfVariables.getHost())
                .setBasePath(ConfVariables.getPath())
                .setContentType(ContentType.JSON).build();
    }

}
