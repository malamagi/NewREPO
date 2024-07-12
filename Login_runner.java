package Runner;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import cucumber.api.CucumberOptions;
	import cucumber.api.testng.CucumberFeatureWrapper;
	
	import cucumber.api.testng.TestNGCucumberRunner;

	
	@CucumberOptions (
	       features = {"C:\\Users\\Karthikeyan\\eclipse-workspace\\Cucumber\\src\\main\\java\\FeatureFiles\\write.feature"}
	       ,glue = {"Step_def"},
plugin= { "pretty",
	               "html:target/site/cucumber-pretty",
	       "json:target/cucumber.json" },
	       monochrome = true) 

	public class Login_runner {
	   private TestNGCucumberRunner testNGCucumberRunner;
	   
	   @BeforeClass(alwaysRun = true)
	   public void setUpClass() throws Exception {    	
	       testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	   }

	   @Test(dataProvider = "features")    
	   public void feature(CucumberFeatureWrapper cucumberFeature) throws Throwable {

		   testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	   }
	   
	   @DataProvider//(parallel=true)
	   public Object[][] features() {
	      // return testNGCucumberRunner.provideFeatures();    	
	   	return testNGCucumberRunner.provideFeatures();
	   }
	   
	   @AfterClass(alwaysRun = true)
	   public void tearDownClass() throws Exception {    	
	       testNGCucumberRunner.finish();        
	   }
	}

	

