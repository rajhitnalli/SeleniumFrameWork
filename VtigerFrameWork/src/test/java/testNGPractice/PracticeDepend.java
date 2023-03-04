package testNGPractice;

import org.testng.annotations.Test;

public class PracticeDepend {

	@Test   
	public void create() 
	{
		System.out.println("create");
		int [] arr= {1,44,66};
		System.out.println(arr[5]);
	}
	
	@Test  (dependsOnMethods = "create") 
	public void modify() // these method will get skipped if depended method get failed
	{
		System.out.println("modified");
	}
	
	@Test  (dependsOnMethods = "create") 
	public void delete()
	{
		System.out.println("delete");
	}
}
