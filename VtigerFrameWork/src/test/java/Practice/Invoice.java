package Practice;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Invoice {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//fetch the data for properties file to login app
		FileInputStream fis=new FileInputStream("./src/test/resources/Commondata.properties.txt");
		Properties pro=new Properties();
		pro.load(fis);
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		Random ran=new Random();
		int ranNum = ran.nextInt(1000);
		
		//create contact but it is dependent to organization, so create 1st organization and then create contact
		FileInputStream fes=new FileInputStream("./src/test/resources/ExcelFeb.xlsx");
		Workbook book=WorkbookFactory.create(fes);
		Sheet orgName = book.getSheet("Organization");
		Row orgRow = orgName.getRow(0);
		Cell orgCel = orgRow.getCell(0);
		String orgData = orgCel.getStringCellValue()+ranNum;
				
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgData);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		WebElement ele = driver.findElement(By.name("salutationtype"));
		Select s=new Select(ele);
		Sheet conName = book.getSheet("Contact");
		Row conRow = conName.getRow(0);
		Cell conCel = conRow.getCell(1);
		String firstName = conCel.getStringCellValue();
		Cell conCel2 = conRow.getCell(2);
		String lastName = conCel2.getStringCellValue();
		
		String parent = driver.getWindowHandle();
		
		s.selectByValue("Mr.");
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		Set<String> child = driver.getWindowHandles();
		Iterator<String> it = child.iterator();
		while(it.hasNext())
		{
			String wid = it.next();
			driver.switchTo().window(wid);
			String title = driver.getTitle();
			if(title.contains("Accounts&action"))
			{
				break;
			}
		}
		driver.findElement(By.name("search_text")).sendKeys(orgData);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgData+"']")).click();
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//create invoice
		Actions a=new Actions(driver);
		WebElement Ele = driver.findElement(By.linkText("More"));
		a.moveToElement(Ele).perform();
		//before creating invoice we have to create sales order
		driver.findElement(By.name("Sales Order")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("subject")).sendKeys("sell");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		a.moveToElement(Ele).perform();
		driver.findElement(By.name("Invoice")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("subject")).sendKeys("sell");
		
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
		
	}
}
