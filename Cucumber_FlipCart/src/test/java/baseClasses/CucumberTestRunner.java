package baseClasses;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)


@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"stepdefinitions"},
       /* plugin = {"pretty", "html:target/cucumber-reports.html",
        		"json:target/cucumber-reports.json",
        		"junit:target/cucumber-reports.xml"})*/
      

        
      plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},monochrome = true)


public class CucumberTestRunner {

}
