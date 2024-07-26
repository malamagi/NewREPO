package stepdefinitions;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import baseClasses.BaseClass;	
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;



public class Hooks {
	
	@BeforeAll
	public static void setUp() throws IOException {
	BaseClass.initializeDriver();
	    }
	
	@AfterAll
	public static void quit() throws IOException {
	BaseClass.quitDriver();
	    }

	
	
	@AfterStep
	public void attachScreenshot(Scenario scenario) 
	 {
		if(scenario.isFailed()) {
			byte[] screenshotTaken=((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshotTaken, "image/png", "Error Screen");
		 }
	 }

	
	}


