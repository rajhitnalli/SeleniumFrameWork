package testNGPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Utilities.Java_Utility;
import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.OrganizationCreatePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderVTiger {

	@Test(dataProvider = "dataProviderVTiger")
	public void VTiger(String name,String phone, String email) throws Throwable
	{
		WebDriver driver;
		Property_Utility plib=new Property_Utility();
		String BROWSER = plib.getKeyValue("browser");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.maximizeWindow(driver);
		wlib.waitForPageToLoad(driver);
		
		String URL = plib.getKeyValue("url");
		String USERNAME = plib.getKeyValue("username");
		String PASSWORD = plib.getKeyValue("password");

		driver.get(URL);
		Login_Page login=new Login_Page(driver);
		login.loginToApp(USERNAME, PASSWORD);
		
		Home_Page home=new Home_Page(driver);
		home.clickOnOrganizations();
		
		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRanDomNum();
		
		OrganizationCreatePage createOrg=new OrganizationCreatePage(driver);
		createOrg.organizationCreateWith3IP(name+ranNum,phone+ranNum,email+ranNum);
		
		//home.signOut(driver);
		driver.quit();			
	}
	
	@DataProvider
	public Object[][] dataProviderVTiger()
	{
		Object[][] obj=new Object [2][3];
		
		obj[0][0]="abc";
		obj[0][1]="555555";
		obj[0][2]="abc@gmail.com";
		
		obj[1][0]="xyz";
		obj[1][1]="66666";
		obj[1][2]="xyz@gmail.com";
		
		return obj;
		
	}
}
