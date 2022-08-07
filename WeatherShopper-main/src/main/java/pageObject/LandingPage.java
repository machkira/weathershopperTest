package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Base;

public class LandingPage {
	
	public WebDriver driver;
	
	Base b = new Base();
	String[] temperature;
	String currentTemperature;
	
	public LandingPage(WebDriver driver) {
		
		this.driver = driver;
	
	}
	
	By currentTemp = By.xpath("//span[@id='temperature']");
	By moisturizers = By.xpath("(//button[@class='btn btn-primary'])[1]");
	By sunscreens = By.xpath("(//button[@class='btn btn-primary'])[2]");
	
	
	public String getCurrentTemperature() {
		
		
		
		if(driver.findElement(currentTemp).getText().contains("℃")){
			
			temperature = driver.findElement(currentTemp).getText().split("℃");
			currentTemperature = temperature[0].trim();
			
		}else if(driver.findElement(currentTemp).getText().contains("°C")) {
			
			temperature = driver.findElement(currentTemp).getText().split("°C");
			currentTemperature = temperature[0].trim();
			
		}
		
		return currentTemperature;
	}
	
	public WebElement btnBuyMoisturizers() {
		
		return driver.findElement(moisturizers);
		
	}
	
	public WebElement btnBuySunscreens() {
		
		return driver.findElement(sunscreens);
	}

}
