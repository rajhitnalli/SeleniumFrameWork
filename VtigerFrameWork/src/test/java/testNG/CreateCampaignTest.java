package testNG;

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
import org.openqa.selenium.edge.EdgeDriver;
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
import POM_Repo.Validation;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignTest extends BaseClass{

	@Test(groups = "Smoke")
	public void createCampaign() throws Throwable {

		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRanDomNum();
		
		//fetch data from excel
		//fetching excel using DataFarmatter method
		Excel_Utility elib=new Excel_Utility();
		String data = elib.getExcelUsingDataFormatter("Campaign",0,0)+ranNum;
		
		WebElement ele = driver.findElement(By.linkText("More"));
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.mouseOverOnElement(driver, ele);
		
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnCampaigns(driver);
		CampaignCreatePage createCamp=new CampaignCreatePage(driver);
		createCamp.createCampaign(data);
		
		//validation
		//String ele3 = driver.findElement(By.id("dtlview_Campaign Name")).getText();
		Validation val=new Validation(driver);
		String ele3 = val.camValidation();
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
