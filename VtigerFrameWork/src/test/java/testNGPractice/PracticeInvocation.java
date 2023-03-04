package testNGPractice;

import org.testng.annotations.Test;

public class PracticeInvocation {

	@Test   
	public void create() 
	{
		System.out.println("create");
		
	}
	
	@Test  (invocationCount = 2) 
	public void modify() // this method will get executed 2times
	{
		System.out.println("modified");
	}
	
	@Test  () 
	public void delete()
	{
		System.out.println("delete");
	}
}
