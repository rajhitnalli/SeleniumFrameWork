package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickOnAllModuleOfHomePage {

	public static void main(String[] args) throws Throwable {
	WebDriver driver;
	Property_Utility plib=new Property_Utility();
	String BROWSER = plib.getKeyValue("browser");
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	}
	if(BROWSER.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
	}
	if(BROWSER.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
	}
	else {
		driver=new ChromeDriver();
	}
	
	WebDriver_Utility wlib=new WebDriver_Utility();
	wlib.waitForPageToLoad(driver);
	wlib.maximizeWindow(driver);
	String URL = plib.getKeyValue("url");
	String USERNAME = plib.getKeyValue("username");
	String PASSWORD = plib.getKeyValue("password");
	
	driver.get(URL);
	Login_Page llib=new Login_Page(driver);
	llib.loginToApp(USERNAME, PASSWORD);
	
	Home_Page hlib=new Home_Page(driver);
	hlib.clickOnOrganizations();
	hlib.clickOnProducts();
	hlib.clickOnContacts();
	hlib.clickOnCampaigns(driver);
	hlib.clickOnInvoice(driver);
	hlib.signOut(driver);
}
}
