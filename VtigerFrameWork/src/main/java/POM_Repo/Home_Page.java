package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Page {

	public Home_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement org;
	
	@FindBy(linkText="Contacts")
	private WebElement contact;
	
	@FindBy(linkText="Products")
	private WebElement product;
	
	@FindBy(linkText="More")
	private WebElement more;
	
	@FindBy(name="Campaigns")
	private WebElement campaign;
	
	@FindBy(name="Invoice")
	private WebElement invoice;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement moveOnSignout;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOut;
	
	public void clickOnOrganizations()
	{
		org.click();
	}
	
	public void clickOnContacts()
	{
		contact.click();
	}
	
	public void clickOnProducts()
	{
		product.click();
	}
	
	public void clickOnCampaigns(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.moveToElement(more).perform();
		campaign.click();
	}
	
	public void clickOnInvoice(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.moveToElement(more).perform();
		invoice.click();
	}
	public void signOut(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.moveToElement(moveOnSignout).perform();
		signOut.click();
	}
}
