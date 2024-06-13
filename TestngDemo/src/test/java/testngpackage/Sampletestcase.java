package testngpackage;

import org.testng.annotations.Test;

public class Sampletestcase {
	
	@Test(priority=0)
	public void firstcase()
	{
		System.out.println("This is first test case");
	}
	@Test(priority=1)
	public void secondcase()
	{
		System.out.println("This is second test case");
	}
	@Test(priority=2)
	public void Thirdcase()
	{
		System.out.println("This is third test case");
	}
	@Test(priority=3)
	public void fourthcase()
	{
		System.out.println("This is fourth test case");
	}
}
