package testNG;

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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import POM_Repo.ProductCreatePage;
import POM_Repo.Validation;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductTest extends BaseClass {

	@Test(groups = "RegressionTest")
	public void createProduct() throws Throwable {
		
		Java_Utility jlib=new Java_Utility();
		int ranNum = jlib.getRanDomNum();
		
		Home_Page hlib=new Home_Page(driver);
		hlib.clickOnProducts();
		
		// fetching data from excel file
		Excel_Utility elib=new Excel_Utility();
		String data = elib.getExcelData("Product",0,0)+ranNum;
		
		ProductCreatePage pCreate=new ProductCreatePage(driver);
		pCreate.createProduct(data);
		
		//validation
		//String ele3 = driver.findElement(By.id("dtlview_Product Name")).getText();
		Validation val=new Validation(driver);
		String ele3 = val.proValidation();
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
