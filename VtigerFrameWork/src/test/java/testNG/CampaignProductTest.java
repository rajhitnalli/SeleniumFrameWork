package testNG;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import Generic_Utilities.BaseClass;
import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Java_Utility;
import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.CampaignCreatePage;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.ProductCreatePage;
import POM_Repo.Validation;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CampaignProductTest extends BaseClass{

	@Test(groups = "Smoke")
	public void campaignProduct() throws Throwable {
		
		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRanDomNum();
				
		//create product and save
		Excel_Utility elib=new Excel_Utility();
		String data2 = elib.getExcelUsingDataFormatter("Product", 0, 0)+ranNum;
		
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnProducts();
		ProductCreatePage createProd=new ProductCreatePage(driver);
		createProd.createProduct(data2);
		
		//excel data fetching data formatter method
		String data = elib.getExcelUsingDataFormatter("Campaign", 0, 0)+ranNum;
		
		String parent = driver.getWindowHandle();
		
		hlib.clickOnCampaigns(driver);
		CampaignCreatePage createCamp=new CampaignCreatePage(driver);
		createCamp.createCampaignWithProduct(driver,data,data2,parent);
		
		//String ele3 = driver.findElement(By.id("dtlview_Campaign Name")).getText();
		Validation val=new Validation(driver);
		String ele3 = val.campValidation();
		Assert.assertEquals(ele3, data);
		System.out.println("Pass");
		/*if(ele3.contains(data))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}*/
		
		Thread.sleep(3000);
		
		
	}
}
