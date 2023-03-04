package testNGPractice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssert {
// hard assert (These are static methods)  are used mainly to check compulsory fields
	@Test
	public void hardAss()
	{
		System.out.println("pritn1");
		Assert.assertEquals("A", "B"); // if it fails, then code after this will not executes
		System.out.println("pritn not pritn");
	}
	@Test
	public void m1()
	{
		System.out.println("pritn2");
	}
}
