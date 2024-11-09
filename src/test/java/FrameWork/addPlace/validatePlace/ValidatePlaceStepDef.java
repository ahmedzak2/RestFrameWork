package FrameWork.addPlace.validatePlace;

import FrameWork.Utlis.AddPlaceDataBuild;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.assertEquals;

public class ValidatePlaceStepDef extends BaseTest {

    @When("user calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource,String method) {
        System.out.println("whyMeMe");

        resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST")) {
            response = res.when().post(resource);
            //response = res.when().post(resource).then().spec(resp).log().all().extract().response();
        }
        else if(method.equalsIgnoreCase("GET"))
            response = res.when().get(resource);

    }

    public int getResponseStatusCode() {
        return getResponseStatusCode = response.getStatusCode();
    }

    @Then("The API request is success with status code {int}")
    public void theAPIRequestIsSuccessWithStatusCode(int statusCode) {
        assertEquals("The actual status code is not as expected", statusCode, getResponseStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String expectedValue) {
        String actualValue = response.jsonPath().getString(key);
        assertEquals("Value for " + key + " is not as expected", expectedValue, getJsonPath(response,key));
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
        res = given().spec(req).body(AddPlaceDataBuild.addPlace(name,language, address  , phoneNumber ));
        // Implementation
    }

    @Then("Verify place ID created map to{string}")
    public void verifyPlaceIDCreatedMapTo(String arg0, String arg1) throws IOException {

        res = given().spec(requestSpecification()).queryParam("place_id",place_Id);
    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void     verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
        System.out.println("Attempting API call...");
        String finalResponse =response.then().log().all().extract().asString();
        JsonPath jsonPath   = new JsonPath(finalResponse);
        System.out.println("final"+finalResponse);
         place_Id = jsonPath.getString("place_id");
        System.out.println("place"+place_Id);
        if (place_Id == null || place_Id.isEmpty()) {
            throw new IllegalArgumentException("Place ID is null or empty. Cannot proceed with the API call.");
        }
        if (finalResponse.isEmpty()) {
            throw new IllegalArgumentException("Response body is empty.");
        }
        res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
        userCallsWithHttpRequest("/maps/api/place/get/json", "GET");
        System.out.println("res111"+res.log().all());
        String getResponse= response.then().log().all().extract().asString();
        JsonPath jsonPath2 = new JsonPath(getResponse);
        System.out.println("GetResponse"+getResponse);
        // Using JsonPath to extract 'name' from the response after verifying it is not empty
        String actualName = jsonPath2.get("name");

        if (actualName == null) {
            throw new IllegalArgumentException("Name field is not available in the response.");
        }

        System.out.println("Actual Name: " + actualName);
        System.out.println("Expected Name: " + expectedName);

        // Assert that the actual name matches the expected name
        assertEquals("Expected name does not match the actual name.", expectedName, actualName);
    }

    @Given("Delete place load")
    public void deletePlaceLoad() throws IOException {
        String finalResponse =response.then().log().all().extract().asString();
        JsonPath jsonPath = new JsonPath(finalResponse);
        System.out.println("final"+finalResponse);

        place_Id = jsonPath.getString("place_id");

      res=  given().spec(requestSpecification()).body(deletePlacePayload(place_Id));
    }
}
