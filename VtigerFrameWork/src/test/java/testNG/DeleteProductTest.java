package testNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Generic_Utilities.BaseClass;
import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.ProductCreatePage;
import POM_Repo.ProductDeletePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteProductTest extends BaseClass{

	@Test(groups = "RegressionTest")
	public void deleteProduct() throws Throwable {
		
				
		Random ran=new Random();
		int ranNum = ran.nextInt(1000);
		
		//create product
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnProducts();
		
		Excel_Utility elib=new Excel_Utility();
		String data = elib.getExcelUsingDataFormatter("Product", 0, 0)+ranNum;
		
		ProductCreatePage createProd=new ProductCreatePage(driver);
		createProd.createProduct(data);
		
		//delete product
		hlib.clickOnProducts();
		
		ProductDeletePage deleteProd=new ProductDeletePage(driver);
		String ele3 = deleteProd.deleteProduct(data);
		
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.switchToAlertAndAccept(driver);
		
		Thread.sleep(3000);
		
		//validation
		Assert.assertEquals(ele3, data);
		/*if(ele3.contains(data))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}*/
	}
}
