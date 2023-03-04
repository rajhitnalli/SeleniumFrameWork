package Campaign;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Java_Utility;
import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.CampaignCreatePage;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaign {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		//fetch data from properties file for login the app
		
		Property_Utility plib=new Property_Utility();
		String URL = plib.getKeyValue("url");
		String USERNAME = plib.getKeyValue("username");
		String PASSWORD = plib.getKeyValue("password");
		
		/*FileInputStream fis=new FileInputStream("./src/test/resources/Commondata.properties.txt");
		Properties pro=new Properties();
		pro.load(fis);
		
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");*/
	
		driver.get(URL);
		Login_Page llib=new Login_Page(driver);
		llib.loginToApp(USERNAME, PASSWORD);
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();*/
		
		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRanDomNum();
		
		//fetch data from excel
		//fetching excel using DataFarmatter method
		Excel_Utility elib=new Excel_Utility();
		String data = elib.getExcelUsingDataFormatter("Campaign",0,0)+ranNum;
		
		/*FileInputStream fes=new FileInputStream("./src/test/resources/ExcelFeb.xlsx");
		Workbook book=WorkbookFactory.create(fes);
		Sheet sheetName = book.getSheet("Campaign");
		Row rowNum = sheetName.getRow(0);
		Cell celNum = rowNum.getCell(0);
		String data = celNum.getStringCellValue()+ranNum;*/
		
		WebElement ele = driver.findElement(By.linkText("More"));
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.mouseOverOnElement(driver, ele);
		/*Actions a=new Actions(driver);
		a.moveToElement(ele).perform();*/
		
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnCampaigns(driver);
		CampaignCreatePage createCamp=new CampaignCreatePage(driver);
		createCamp.createCampaign(data);
		/*driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(data);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();*/
		
		//validation
		String ele3 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(ele3.contains(data))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		//logout from app
		hlib.signOut(driver);
		/*WebElement ele2 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//Actions aa=new Actions(driver);
		a.moveToElement(ele2).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();*/
		
		
		
		driver.close();
		
	}
}
