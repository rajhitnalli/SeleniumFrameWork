package Generic_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver sdriver;
	public WebDriver driver;
	@BeforeSuite(groups = {"Smoke","RegressionTest"})
	public void BS()
	{
		System.out.println("Database Connection");
		
	}
	@BeforeTest(groups = {"Smoke","RegressionTest"})
	public void BT()
	{
		System.out.println("Parallel execution");
	}
	@Parameters("BROWSER")
	@BeforeClass //(groups = {"Smoke","RegressionTest"})
	public void BC() throws Throwable
	{
	//public void BC(String BROWSER) throws Throwable{
		//launching browser
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
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		System.out.println("launching the browser");
		sdriver=driver;
	}
	@BeforeMethod(groups = {"Smoke","RegressionTest"})
	public void BM() throws Throwable
	{
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.maximizeWindow(driver);
		wlib.waitForPageToLoad(driver);
		//fetch data from properties file
		Property_Utility plib=new Property_Utility();
		String URL = plib.getKeyValue("url");
		String USERNAME = plib.getKeyValue("username");
		String PASSWORD = plib.getKeyValue("password");
		//login to VTiger application
		driver.get(URL);
		Login_Page login=new Login_Page(driver);
		login.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("Login to application");
	}
	
	@AfterMethod(groups = {"Smoke","RegressionTest"})
	public void AM()
	{
		//logout from VTiger application
		Home_Page home=new Home_Page(driver);
		home.signOut(driver);
		System.out.println("Logout from application");
	}
	@AfterClass(groups = {"Smoke","RegressionTest"})
	public void AC()
	{
		driver.close();
		System.out.println("Close browser");
	}
	
	@AfterTest(groups = {"Smoke","RegressionTest"})
	public void AT()
	{
		System.out.println("parallel execution done");
	}
	@AfterSuite(groups = {"Smoke","RegressionTest"})
	public void AS()
	{
		System.out.println("Database closed");
	}
}
