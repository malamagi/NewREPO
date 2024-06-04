package testngpackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SuiteExample {
@Test
	public void opengoogle()
	{
	
	long starttime=System.currentTimeMillis();
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	driver.get("http://www.google.co.in");
	driver.quit();
	
	long endtime=System.currentTimeMillis();
	
	long totaltime=endtime-starttime;
	System.out.println("Total time taken for Google site:"+totaltime);
	}
	@Test
	public void openbing()
	{
		long starttime=System.currentTimeMillis();
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.bing.com");
		driver.quit();
		
		long endtime=System.currentTimeMillis();
		long totaltime=endtime-starttime;
		
		System.out.println("Total time taken for Bingo site:"+totaltime);
	}
	@Test
	public void openyahoo()
	{
		long starttime=System.currentTimeMillis();
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.yahoo.com");
		driver.quit();
	
		long endtime=System.currentTimeMillis();
		long totaltime=endtime-starttime;
		
		System.out.println("Total time taken for Yahoo site:"+totaltime);
	}
	
}
