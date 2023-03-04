package Campaign;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Java_Utility;
import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.CampaignCreatePage;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.ProductCreatePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CampaignProduct {

	public static void main(String[] args) throws Throwable {
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
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//fetch data from properties file for login
		
		String URL = plib.getKeyValue("url");
		String USERNAME = plib.getKeyValue("username");
		String PASSWORD = plib.getKeyValue("password");
		
		/*FileInputStream fis=new FileInputStream("./src/test/resources/Commondata.properties.txt");
		Properties pro=new Properties();
		pro.load(fis);
		String URL=pro.getProperty("url");
		String USERNAME=pro.getProperty("username");
		String PASSWORD=pro.getProperty("password");*/
		
		driver.get(URL);
		Login_Page llib=new Login_Page(driver);
		llib.loginToApp(USERNAME, PASSWORD);
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();*/
		
		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRanDomNum();
				
		//Random ran=new Random();
		//int ranNum = ran.nextInt();
		//fetch the data from excel
		//FileInputStream fes=new FileInputStream("./src/test/resources/ExcelFeb.xlsx");
		//Workbook book=WorkbookFactory.create(fes);
		
		//create product and save
		Excel_Utility elib=new Excel_Utility();
		String data2 = elib.getExcelUsingDataFormatter("Product", 0, 0)+ranNum;
		/*Sheet sheetName2 = book.getSheet("Product");
		Row celrow2 = sheetName2.getRow(0);
		Cell celnum2 = celrow2.getCell(0);
		String data2 = celnum2.getStringCellValue()+ranNum;*/
		
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnProducts();
		ProductCreatePage createProd=new ProductCreatePage(driver);
		createProd.createProduct(data2);
		/*driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("productname")).sendKeys(data2);
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();*/
		
		
		/*WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
		driver.findElement(By.name("Campaigns")).click();*/
		
		//excel data fetching data formatter method
		
		
		//driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		String data = elib.getExcelUsingDataFormatter("Campaign", 0, 0)+ranNum;
		/*Sheet sheetName = book.getSheet("Campaign");
		Row rownum = sheetName.getRow(0);
		Cell celNum = rownum.getCell(0);
		String data = celNum.getStringCellValue()+ranNum;*/
		
		String parent = driver.getWindowHandle();
		
		hlib.clickOnCampaigns(driver);
		CampaignCreatePage createCamp=new CampaignCreatePage(driver);
		createCamp.createCampaignWithProduct(driver,data,data2,parent);
		
		String ele3 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(ele3.contains(data))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		/*driver.findElement(By.name("campaignname")).sendKeys(data);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();*/
		
		
		/*Set<String> child = driver.getWindowHandles();
		Iterator<String> it = child.iterator();
		while(it.hasNext())// if multiple child window open's
		{
			String wid = it.next();
			driver.switchTo().window(wid);
			String title=driver.getTitle();
			if(title.contains("Products&action"))
			{
				break;
			}
			
		}*/
		/*
		for(String b:child)
		{
			driver.switchTo().window(b);
		}*/
		
		
		/*driver.findElement(By.name("search_text")).sendKeys(data2);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+data2+"']")).click();*/
		
		
		//driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		Thread.sleep(3000);
		
		hlib.signOut(driver);
		/*WebElement ele3 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(ele3).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();*/
	}
}
