package testNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Generic_Utilities.BaseClass;
import Generic_Utilities.Excel_Utility;
import Generic_Utilities.Java_Utility;
import Generic_Utilities.Property_Utility;
import Generic_Utilities.WebDriver_Utility;
import POM_Repo.Home_Page;
import POM_Repo.Login_Page;
import POM_Repo.OrganizationCreatePage;
import POM_Repo.Validation;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTest extends BaseClass{
	@Test (retryAnalyzer = Generic_Utilities.RetryAnalyser.class)
public void createOrganization() throws Throwable {
	
	Java_Utility jlib=new Java_Utility();
	int ranNum = jlib.getRanDomNum();
	
	Home_Page hlib=new Home_Page(driver);
	hlib.clickOnOrganizations();
	
	//to take screen shot intentionally validation fail
	Assert.assertEquals(false, true);
	Excel_Utility elib=new Excel_Utility();
	String data = elib.getExcelData("Organization",0,0)+ranNum;
	
	
	// fetching data from excel file
	OrganizationCreatePage orgCreate=new OrganizationCreatePage(driver);
	orgCreate.organizationCreate(data);
	
	Thread.sleep(5000);
	
	//validation
	//String actData = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
	Validation val=new Validation(driver);
	String actData = val.orgValidation();
	Assert.assertEquals(data, actData);
	
	/*String dataTitle = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(dataTitle.contains(data))
	{
		System.out.println("PASS");
	}
	else
	{
		System.out.println("FAIL");
	}*/
	
	System.out.println("Oganization created successfully");
}
	/*@Test
	public void m1()
	{
		System.out.println("m1 running");
	}*/
}
