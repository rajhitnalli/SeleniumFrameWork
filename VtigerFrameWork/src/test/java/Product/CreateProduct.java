package Product;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Java_Utility;
import Generic_Utilities.Property_Utility;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.ProductCreatePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProduct {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		// Fetching data from properties file
		Property_Utility plib=new Property_Utility();
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
		Login_Page llib=new Login_Page(driver);
		llib.loginToApp(USERNAME, PASSWORD);
		
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();*/
		
		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRanDomNum();
		
		//Random ran=new Random();
		//int ranNum=ran.nextInt(1000);
		
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnProducts();
		//driver.findElement(By.linkText("Products")).click();
		
		
		// fetching data from excel file
		
		Excel_Utility elib=new Excel_Utility();
		String data = elib.getExcelData("Product",0,0)+ranNum;
		
		/*FileInputStream fes=new FileInputStream("./src/test/resources/ExcelFeb.xlsx");
		Workbook book=WorkbookFactory.create(fes);
		Sheet sheetName = book.getSheet("Product");
		Row rowNum = sheetName.getRow(0);
		Cell celNum = rowNum.getCell(0);
		String data = celNum.getStringCellValue()+ranNum;*/
		
		ProductCreatePage pCreate=new ProductCreatePage(driver);
		pCreate.createProduct(data);
		/*driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("productname")).sendKeys(data);
		driver.findElement(By.xpath("//input[@type='submit'][1]")).click();*/
		
		//validation
		String ele3 = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(ele3.contains(data))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
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
