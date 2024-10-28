package FrameWork.addPlace.validatePlace;

import FrameWork.Utlis.AddPlaceDataBuild;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.assertEquals;

public class ValidatePlaceStepDef extends BaseTest {

    @When("user calls {string} with http request")
    public void userCallsWithHttpRequest(String resource) {
        System.out.println("whyMeMe");

        resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        response = res.when().post(resource).then().spec(resp).log().all().extract().response();
    }

    public int getResponseStatusCode() {
        return getResponseStatusCode = response.getStatusCode();
    }

    @Then("The API request is success with status code {int}")
    public void theAPIRequestIsSuccessWithStatusCode(int statusCode) {
        assertEquals("The actual status code is not as expected", statusCode, getResponseStatusCode());
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String expectedValue) {
        String actualValue = response.jsonPath().getString(key);
        assertEquals("Value for " + key + " is not as expected", expectedValue, actualValue);
    }



    @Given("add place PayLoad with {string} and {string} and {string} and {string}")
    public void addPlacePayLoadWithAndAndAnd(String arg0, String arg1, String arg2, String arg3) throws IOException {
        req = requestSpecification();
        res = given().spec(req).body(AddPlaceDataBuild.addPlace(arg0,arg1, arg2 , arg3 ));
        //  response = requestAddPlace.when().post("/maps/api/place/add/json").then().spec(responseSpecification).extract().response();

    }
    @Given("add place payload with {string}, {string}, {string}, and {string}")
    public void addPlacePayloadWithAndAndAnd(String language, String address, String name, String phoneNumber) throws IOException {
        req = requestSpecification();
        res = given().spec(req).body(AddPlaceDataBuild.addPlace(language,address, name , phoneNumber ));
        // Implementation
    }

}
