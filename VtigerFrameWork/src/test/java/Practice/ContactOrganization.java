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

public class ContactOrganization {

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
		//fetch data from excel to enter the data in create contact
		FileInputStream fes=new FileInputStream("./src/test/resources/ExcelFeb.xlsx");
		Workbook book=WorkbookFactory.create(fes);
		Sheet sheetName = book.getSheet("Organization");
		Row rowNum = sheetName.getRow(0);
		Cell celNum = rowNum.getCell(0);
		String data = celNum.getStringCellValue()+ranNum;
		//create 
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(data);
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		Thread.sleep(3000);
		
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		WebElement ele = driver.findElement(By.name("salutationtype"));
		
		Sheet sheetContact = book.getSheet("Contact");
		Row controw = sheetContact.getRow(0);
		Cell concel = controw.getCell(1);
		String firstName = concel.getStringCellValue();
		Cell concel2 = controw.getCell(2);
		String lastName = concel2.getStringCellValue();
		
		String parent = driver.getWindowHandle();
		
		Select s=new Select(ele);
		s.selectByValue("Mr.");
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
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
		driver.findElement(By.name("search_text")).sendKeys(data);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+data+"']")).click();
		
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(3000);
		
		Actions a=new Actions(driver);
		WebElement ele3 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(ele3).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.close();
	}
}
