package FrameWork.addPlace;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/FrameWork/addPlace",
        glue = "FrameWork.addPlace",
        plugin = {"json:target/jsonReports/cucumber.json"}
)
public class TestRunner {
}
