package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCreatePage {

	public ProductCreatePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement clickToCreateProduct;
	
	@FindBy(name="productname")
	private WebElement prodName;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveProduct;
	
	public void createProduct(String dataName)
	{
		clickToCreateProduct.click();
		prodName.sendKeys(dataName);
		saveProduct.click();
	}
}
