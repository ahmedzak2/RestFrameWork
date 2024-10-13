package FrameWork.addPlace.validatePlace;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BaseTest {
    RequestSpecification res;
    Response response;
    protected static int getResponseStatusCode;
   public  RequestSpecification req;
    public static RequestSpecification requestAddPlace;
    ResponseSpecification resp;
    PrintStream printStream;

    public RequestSpecification requestSpecification() {
        try {
            printStream = new PrintStream(new FileOutputStream("src/Pages/resources/Logging.txt", true)); // Corrected path
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .build();

        System.out.println("RequestSpecification built.");
        return spec;
    }
}
