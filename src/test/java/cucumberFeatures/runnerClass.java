package cucumberFeatures;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features", glue ="stepDefinitions", monochrome = true )
public class runnerClass extends AbstractTestNGCucumberTests {

}
