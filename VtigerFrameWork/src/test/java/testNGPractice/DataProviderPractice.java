package testNGPractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {

	@Test(dataProvider = "dataProviderTest")
	public void ticketBooking(String src, String dest)
	{
		System.out.println("Book ticket from "+src+" to "+dest);
	}
	
	@DataProvider
	public Object[][] dataProviderTest()
	{
		Object[][] obj=new Object[2][2];
		obj[0][0]="Bijapur";
		obj[0][1]="Bangalore";
		
		obj[1][0]="Bijapur";
		obj[1][1]="Goa";
		
		return obj;
		
	}
}
