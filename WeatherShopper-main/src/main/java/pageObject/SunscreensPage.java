package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SunscreensPage {
	
public WebDriver driver;
	
	String[] allMoisturiser;
	
	By btnAdd = By.xpath("//div[@class='text-center col-4']//button[@class='btn btn-primary']");
	
	By allProductsName = By.xpath("//div[@class='text-center col-4']/p[1]");
	
	By allProductsPrice = By.xpath("//div[@class='text-center col-4']/p[2]");
	
	By cart = By.xpath("//button[@class='thin-text nav-link']");
	

	
	public SunscreensPage(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public List<WebElement> getAllProductName() {
		
		return driver.findElements(allProductsName);

	}
	
	public List<WebElement> getAllProductPrice() {
		
		return driver.findElements(allProductsPrice);

	}
	
	public List<WebElement> getBtnAdd() {
		return driver.findElements(btnAdd);
	}

	public WebElement getCart() {
		
		return driver.findElement(cart);
		
	}
	
}
