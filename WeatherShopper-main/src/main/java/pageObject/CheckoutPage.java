package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	
	public WebDriver driver;
	
	By btnPayWithCard = By.xpath("//button[@class='stripe-button-el']");
	
	By numberofIFrame = By.tagName("iframe");
	
	By textBoxEmail = By.xpath("//input[@id='email']"); 
	By textBoxCardNumber = By.xpath("//input[@id='card_number']"); 
	By textBoxExpiredMonthYear = By.xpath("//input[@id='cc-exp']"); 
	By textBoxCVC = By.xpath("//input[@id='cc-csc']"); 
	By btnPay = By.xpath("//span[@class='iconTick']");
	By textBoxZipCode = By.xpath("//input[@name='zip']");
	By header = By.xpath("//h2[text()='Checkout']");
	
	public CheckoutPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public WebElement getPayWithCard() {
		
		return driver.findElement(btnPayWithCard);
	}
	
	public WebElement getEnterEmail() {
		
		return driver.findElement(textBoxEmail);
	}
	
	public WebElement getEnterCardNumber() {
		
		return driver.findElement(textBoxCardNumber);
	}
	
	public WebElement getEnterExpiryMonthYear() {
		
		return driver.findElement(textBoxExpiredMonthYear);
	}
	
	public WebElement getEnterCVC() {
		
		return driver.findElement(textBoxCVC);
	}
	public WebElement getPay() {
		
		return driver.findElement(btnPay);
	}
	
	public List<WebElement> getNumberofIFrame() {
		
		return driver.findElements(numberofIFrame);
	
	}
	
	public WebElement getEnterZipCode() {
		
			return driver.findElement(textBoxZipCode);
	
	}

	public WebElement getHeader() {
		
		return driver.findElement(header);

}
}
