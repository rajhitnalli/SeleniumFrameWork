package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Generic_Utilities.WebDriver_Utility;

public class ProductDeletePage {
	
	public ProductDeletePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	/*@FindBy(xpath="//a[text()='Product Name']/ancestor::table[@class='lvt small']/descendant::a[text()='"+data+"']")
	private WebElement ProdName;*/
	
	@FindBy(name="search_text")
	private WebElement searchText;
	
	@FindBy(id="bas_searchfield")
	private WebElement selectField;
	
	@FindBy(name="submit")
	private WebElement submit;
	
	@FindBy(xpath="//a[@title='Products']")
	private WebElement proName;
	
	@FindBy(id="dtlview_Product Name")
	private WebElement titleName;
	
	public WebElement getTitleName() {
		return titleName;
	}

	@FindBy(xpath="(//input[@title='Delete [Alt+D]'])[1]")
	private WebElement deleteProd;
	
	public String deleteProduct(String data) throws Throwable
	{
		searchText.sendKeys(data);
		Select s=new Select(selectField);
		s.selectByVisibleText("Product Name");
		submit.click();
		Thread.sleep(4000);
		proName.click();
		String ele3 = titleName.getText();
		Thread.sleep(4000);
		deleteProd.click();
		
		return ele3;
	}
}
