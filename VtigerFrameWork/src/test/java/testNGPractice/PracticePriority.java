package testNGPractice;

import org.testng.annotations.Test;

//@Test  // if we write test annotation here then all method written in this class will be executed
public class PracticePriority {

	@Test  (priority=1) //method 1 if we use test annotation here then only method 1 will get executed 
	public void create() // if we don't mention priority then according to ASCII it will display
	{
		System.out.println("create");
	}
	
	@Test  (priority=2) //method2 if we use test annotation here then only method 2 will get executed
	public void modify()
	{
		System.out.println("modified");
	}
	
	@Test  (priority=3) //method3 if we use test annotation here then only method 3 will get executed
	public void delete()
	{
		System.out.println("delete");
	}
}
