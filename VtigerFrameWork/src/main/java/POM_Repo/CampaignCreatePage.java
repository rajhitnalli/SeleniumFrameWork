package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Utilities.WebDriver_Utility;

public class CampaignCreatePage {

	public CampaignCreatePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement clickOn;
	
	@FindBy(name="campaignname")
	private WebElement cName;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement prodSelect;
	
	@FindBy(name="search_text")
	private WebElement searchText;
	
	@FindBy(name="search")
	private WebElement searchButton;
	
	@FindBy(id="1")
	private WebElement select;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;
	
	public void createCampaign(String campName)
	{
		clickOn.click();
		cName.sendKeys(campName);
		saveButton.click();
	}
	
	public void createCampaignWithProduct(WebDriver driver,String campName,String pName, String parent) throws Throwable
	{
		clickOn.click();
		cName.sendKeys(campName);
		prodSelect.click();
		WebDriver_Utility wlib=new WebDriver_Utility();
		wlib.switchWindow(driver,"Products&action");
		searchText.sendKeys(pName);
		searchButton.click();
		Thread.sleep(6000);
		select.click();
		driver.switchTo().window(parent);
		saveButton.click();
	}
}
