package testngpackage;

import org.testng.annotations.Test;

public class DriveCar {

	@Test(priority=0)
	public void startthecar()
	{
		System.out.println("Start the car");
	}
	@Test(priority=1)
	 public void putfirstgear()
	 {
		 System.out.println("First Gear");
	 }
	@Test(priority=2)
	 public void putsecondgear()
	 {
		 System.out.println("Second Gear");
	 }
	@Test(priority=3)
	 public void putthirdgear()
	 {
		 System.out.println("Third Gear");
	 }
	@Test(priority=4)
	 public void putfourthgear()
	 {
		 System.out.println("Fourth Gear");
	 }
	 
	 
}
