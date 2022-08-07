package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmationPage {
	
	public WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		
		this.driver = driver;
	
	}
	
	By confirmationHeader = By.xpath("//h2[text()='PAYMENT SUCCESS']");
	
	public WebElement getConfirmationHeader() {
		return driver.findElement(confirmationHeader);
	}

}
