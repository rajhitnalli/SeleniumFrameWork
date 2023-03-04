package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Validation {

	public Validation(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgName;
	
	@FindBy(id="dtlview_Campaign Name")
	private WebElement campName;
	
	@FindBy(id="dtlview_Campaign Name")
	private WebElement camName;
	
	@FindBy(id="dtlview_Product Name")
	private WebElement proName;
	
	
	public String orgValidation()
	{
		String data = orgName.getText();
		return data;
	}
	
	public String campValidation()
	{
		String data=campName.getText();
		return data;
	}
	
	public String camValidation()
	{
		String data=camName.getText();
		return data;
	}
	
	public String proValidation()
	{
		String data=proName.getText();
		return data;
	}
	
	
	
	
	
}
