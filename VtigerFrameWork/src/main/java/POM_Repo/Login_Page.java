package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {

	public Login_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Declaration
	@FindBy(name="user_name")
	private WebElement userTextField;
	
	@FindBy(name="user_password")
	private WebElement passwordTextField;
	
	@FindBy(id="submitButton")
	private WebElement submitButton;

	//getter method
	public WebElement getUserTextField() {
		return userTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	//Business logics
	public void loginToApp(String username, String password)
	{
		userTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		submitButton.click();
	}
	
}
