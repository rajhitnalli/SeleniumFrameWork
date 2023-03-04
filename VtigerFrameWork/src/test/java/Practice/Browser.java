package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

	public static void main(String[] args) throws InterruptedException, IOException {
		/*String Key="webdriver.chrome.driver";
		String Value="E:\\SW testing cource\\Selenium\\chromedriver.exe";
		System.setProperty(Key, Value);*/
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		// Fetching data from properties file
		FileInputStream fis=new FileInputStream("./src/test/resources/Commondata.properties.txt");
		Properties pro=new Properties();
		pro.load(fis);
		
		String URL = pro.getProperty("url");
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		// fetching data from excel file
		FileInputStream fes=new FileInputStream("./src/test/resources/ExcelFeb.xlsx");
		Workbook book=WorkbookFactory.create(fes);
		Sheet sheetName = book.getSheet("Organization");
		Row rowNum = sheetName.getRow(0);
		Cell celNum = rowNum.getCell(0);
		String data = celNum.getStringCellValue();
		
		driver.findElement(By.name("accountname")).sendKeys(data);
		
		
		Thread.sleep(3000);
		driver.close();
		
	}
	
	
}
