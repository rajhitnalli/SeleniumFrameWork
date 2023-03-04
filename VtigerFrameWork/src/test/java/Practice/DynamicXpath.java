package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicXpath {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://www.makemytrip.com/");
		driver.findElement(By.xpath("//span[@class='ic_circularclose_grey']")).click();
		
		driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
		
		String month="March 2023";
		String date="26";
		WebElement ele = driver.findElement(By.xpath("//div[text()='"+month+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']"));
	
		ele.click();
	
	}
}
