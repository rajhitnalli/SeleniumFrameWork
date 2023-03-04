package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

public class OrganizationCreatePage {
 
	public OrganizationCreatePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//declaration
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement organizationCreate;
	
	@FindBy(name="accountname")
	private WebElement accountName;
	
	@FindBy(id="phone")
	private WebElement phoneNum;
	
	@FindBy(id="email1")
	private WebElement emailId;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;
	
	//business logic
	public void organizationCreateWith3IP(String orgName, String phone, String email)
	{
		organizationCreate.click();
		accountName.sendKeys(orgName);// for each we can create separate business logics 
		phoneNum.sendKeys(phone);
		emailId.sendKeys(email);
		saveButton.click();
	}

	public void organizationCreate(String orgName) {
		organizationCreate.click();
		accountName.sendKeys(orgName);
		saveButton.click();
	}
	

	
}
