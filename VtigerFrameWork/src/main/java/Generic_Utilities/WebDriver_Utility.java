package Generic_Utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class WebDriver_Utility {
/**
 * method for switching from parent window to child window
 * @param driver
 * @param PartialWindowTitle
 */
	public void switchWindow(WebDriver driver,String PartialWindowTitle)
	{
		Set<String> child = driver.getWindowHandles();
		Iterator<String> it = child.iterator();
		while(it.hasNext())
		{
			String wid = it.next();
			driver.switchTo().window(wid);
			String title = driver.getTitle();
			if(title.contains(PartialWindowTitle))
			{
				break;
			}
		}
	}
	
	/**
	 * wait for page to load before identifying any synchronized element in 
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	/**
	 * Used to maximize the window of browser
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * After every action it will wait for next action to perform
	 * @param driver
	 */
	public void scriptTimeOut(WebDriver driver)
	{
		driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
	}
	/**
	 * Used to wait for element to be clickable in GUI and check for specific element for every 500 milliseconds
	 * @param driver
	 * @param Element
	 * @param pollingTime
	 */
	public void waitForElementWithCustomerTimeOut(WebDriver driver,WebElement Element, int pollingTime)
	{
		FluentWait wait=new FluentWait(driver);
		wait.pollingEvery(Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(Element));
	}
	
	/**
	 * used to switch to alert window and accept (click on of button
	 * @param driver
	 */
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * used to switch to alert window and dismiss(click on cancel button)
	 * @param driver
	 */
	public void switchToFrame(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * used to switch to frame window based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * used to switch to frame window based on id or name or attribute
	 * @param driver
	 * @param id_name_Attribute
	 */
	public void switchToFrame(WebDriver driver, String id_name_Attribute)
	{
		driver.switchTo().frame(id_name_Attribute);
	}
	
	/**
	 * used to select the value from the dropdown based on index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * used to select the value from dropdown on text
	 * @param element
	 * @param text
	 */
	public void select(WebElement element, String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	/**
	 * used to place mouse cursor on specified element
	 * @param driver
	 * @param element
	 */
	public void mouseOverOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
	}
	
	/**
	 * used to right click on specified element
	 * @param driver
	 * @param element
	 */
	public void rightClickOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.contextClick(element).perform();
	}
	/**
	 * used move scroll at specified location(x and y) and click on location
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void moveByOffeset(WebDriver driver, int x,int y)
	{
		Actions a=new Actions(driver);
		a.moveByOffset(x, y).click().perform();
	}
}
