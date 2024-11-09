package FrameWork.addPlace.validatePlace;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BaseTest {
    RequestSpecification res;
   static Response response;
    protected static int getResponseStatusCode;
   public  RequestSpecification req;
    public static RequestSpecification requestAddPlace;
    ResponseSpecification resp;
    PrintStream printStream;
    public static  String place_Id;
    public RequestSpecification requestSpecification() throws IOException {
      if ( req == null)
      { try {
            printStream = new PrintStream(new FileOutputStream("src/test/java/Pages/resources/Logging.txt", true)); // Corrected path
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
           req =     new RequestSpecBuilder()
                  .setBaseUri(getGlobalValue("baseUrl"))
                  .setContentType(ContentType.JSON)
                   .addQueryParam("key", "qaclick123")
                  .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                  .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                  .build();

          System.out.println("RequestSpecification built.");
          return req;
      }
        return req;
    }
    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/Pages/resources/global.properties");
        properties.load(fileInputStream);
        return  properties.getProperty(key);


    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        if (resp == null || resp.trim().isEmpty()) {
            throw new IllegalArgumentException("Response is empty or null, cannot extract data using JsonPath.");
        }
        JsonPath js = new JsonPath(resp);
        return js.getString(key);
    }
    public String deletePlacePayload(String placeId)
    {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }

}
