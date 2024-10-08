package FrameWork.addPlace.validatePlace;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import pojoAddPlaceAPI.AddPlace;
import pojoAddPlaceAPI.Location;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ValidatePlaceStepDef extends BaseTest{
    @Given("add place payload")
    public void addPlacePayLoad() {
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setLanguage("English-EN");
        place.setPhone_number("21121650000");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setAddress("22 smart village");
        place.setName("ahmed zakaria");
        Location location = new Location();
        location.setLat(22);
        location.setLng(50);
        place.setLocation(location);
        place.setTypes(Arrays.asList("male", "female", "alien"));

        // Additional request setup as needed, e.g., setting body
        req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();
        res = given().spec(req).body(place).log().all();
        //  response = requestAddPlace.when().post("/maps/api/place/add/json").then().spec(responseSpecification).extract().response();

    }
    @When("user calls {string} with http request")
    public void userCallsWithHttpRequest(String resource) {
        response = res.when().post(resource).then().log().all().extract().response();
    }

    public   int getResponseStatusCode(){
        return getResponseStatusCode=response.getStatusCode();
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


}
