package Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.ProductCreatePage;
import POM_Repo.ProductDeletePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteProduct {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		//fetch data from properties file
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
		
		//create product
		Random ran=new Random();
		int ranNum = ran.nextInt(1000);
		
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnProducts();
		
		Excel_Utility elib=new Excel_Utility();
		String data = elib.getExcelUsingDataFormatter("Product", 0, 0)+ranNum;
		
		ProductCreatePage createProd=new ProductCreatePage(driver);
		createProd.createProduct(data);
		//driver.findElement(By.linkText("Products")).click();
		
		//delete one by one
		hlib.clickOnProducts();
		
		ProductDeletePage deleteProd=new ProductDeletePage(driver);
		String ele3 = deleteProd.deleteProduct(data);
		
		
		/*driver.findElement(By.xpath("(//a[text()='Mahesh'])[3]")).click();
		driver.findElement(By.name("Delete")).click();*/
		
		//delete all one time
		//driver.findElement(By.name("selectall")).click();
		//driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])[1]")).click();
		
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.switchToAlertAndAccept(driver);
		/*Alert al = driver.switchTo().alert();
		al.accept();*/
		
		Thread.sleep(3000);
		
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
		
		driver.close();
	}
}
