package Organization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Java_Utility;
import Generic_Utilities.Property_Utility;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.OrganizationCreatePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization {
public static void main(String[] args) throws Throwable {
	
	Property_Utility plib=new Property_Utility();
	String BROWSER = plib.getKeyValue("browser");
	WebDriver driver;
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
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//WebDriver driver=new ChromeDriver();
	//driver.manage().window().maximize();
	
	// Fetching data from properties file
	
	String URL = plib.getKeyValue("url");
	String USERNAME = plib.getKeyValue("username");
	String PASSWORD = plib.getKeyValue("password");
	
	/*FileInputStream fis=new FileInputStream("./src/test/resources/Commondata.properties.txt");
	Properties pro=new Properties();
	pro.load(fis);
	
	String URL = pro.getProperty("url");
	String username = pro.getProperty("username");
	String password = pro.getProperty("password");*/
	
	driver.get(URL);
	Login_Page login=new Login_Page(driver);
	login.loginToApp(USERNAME, PASSWORD);
	/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();*/
	
	Java_Utility jlib=new Java_Utility();
	int ranNum = jlib.getRanDomNum();
	//Random ran=new Random();
	//int ranNum=ran.nextInt(1000);
	
	Home_Page hlib=new Home_Page(driver);
	hlib.clickOnOrganizations();
	//driver.findElement(By.linkText("Organizations")).click();
	
	
	Excel_Utility elib=new Excel_Utility();
	String data = elib.getExcelData("Organization",0,0)+ranNum;
	
	// fetching data from excel file
	/*FileInputStream fes=new FileInputStream("./src/test/resources/ExcelFeb.xlsx");
	Workbook book=WorkbookFactory.create(fes);
	Sheet sheetName = book.getSheet("Organization");
	Row rowNum = sheetName.getRow(0);
	Cell celNum = rowNum.getCell(0);
	String data = celNum.getStringCellValue()+ranNum;*/
	
	OrganizationCreatePage orgCreate=new OrganizationCreatePage(driver);
	orgCreate.organizationCreate(data);
	/*driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	driver.findElement(By.name("accountname")).sendKeys(data);
	driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();*/
	Thread.sleep(5000);
	
	String dataTitle = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(dataTitle.contains(data))
	{
		System.out.println("PASS");
	}
	else
	{
		System.out.println("FAIL");
	}
	hlib.signOut(driver);
	/*WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions a=new Actions(driver);
	a.moveToElement(ele).perform();
	driver.findElement(By.linkText("Sign Out")).click();*/
	
	Thread.sleep(3000);
	driver.close();
}
}
