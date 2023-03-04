package testNGPractice;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertTest {
// soft assert are non-static methods which are mainly used non-compulsory fields
	@Test
	public void createCont()
	{
		System.out.println("step1");
		System.out.println("step 2");
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(true, false); // even it fails then, after all codes will be executed
		System.out.println("step3");
		soft.assertAll();
		//pushed in github
	}
	
	@Test
	public void modifyCon()
	{
		System.out.println("step4");
		System.out.println("step5");
	}
}
