package FrameWork.addPlace.validatePlace;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoAddPlaceAPI.AddPlace;
import pojoAddPlaceAPI.Location;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BaseTest {
    RequestSpecification res;
    Response response;
    protected static int getResponseStatusCode;
   public  RequestSpecification req;
    public static RequestSpecification requestAddPlace;


}
