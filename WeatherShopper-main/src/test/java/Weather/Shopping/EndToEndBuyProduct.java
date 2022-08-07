package Weather.Shopping;

import org.testng.annotations.Test;

import pageObject.LandingPage;
import pageObject.MoisturizersPage;
import pageObject.SunscreensPage;
import pageObject.CheckoutPage;
import pageObject.ConfirmationPage;
import resources.Base;
import resources.Moisturizers;
import resources.Sunscreens;
import resources.Xls_Reader;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class EndToEndBuyProduct {
	
	public WebDriver driver;
	String currentTemperature;
	LandingPage lp;
	Moisturizers ms;
	MoisturizersPage mp;
	int minimumAloePrice;
	int minimumAlmondPrice;
	List<WebElement> listMoisturizersProductsName;
	List<WebElement> listMoisturizersProductsPrice;
	Sunscreens ss;
	SunscreensPage sp;
	int minimumSPF50Price;
	int minimumSPF30Price;
	List<WebElement> listSunscreensProductsName;
	List<WebElement> listSunscreensProductsPrice;
	CheckoutPage cp;
	ConfirmationPage conp;
	Xls_Reader xss;
	Base b;
	String emailid;
	String cardNumber;
	String cardExpiry;
	String zipCode;
	String cardCVC;
	String expectedMessage;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void initilize() throws IOException {
		
		b = new Base();
		driver = b.initialization();
		log.info("Driver is Initialized");
		
		lp = new LandingPage(driver);
		ms = new Moisturizers(driver);
		mp = new MoisturizersPage(driver);
		ss = new Sunscreens(driver);
		sp = new SunscreensPage(driver);
		cp = new CheckoutPage(driver);
		conp = new ConfirmationPage(driver);
		xss = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Data.xlsx");

	}
	
	
	@Test
	public void addToCartProduct() {

		
		currentTemperature = lp.getCurrentTemperature();
		log.info("Current Temerature  is: " +currentTemperature);
		
		if(Integer.parseInt(currentTemperature)<15) {
			
			lp.btnBuyMoisturizers().click();
			log.info("Select Moisturizers");
			
			listMoisturizersProductsName = mp.getAllProductName();
			listMoisturizersProductsPrice = mp.getAllProductPrice();
			
			ms.getAloeAndAlmondPrice();
			minimumAloePrice = ms.getMinimumAloePrice();
			log.info("Lowest Price on Page is: " +minimumAloePrice);
			
			for(int i =0; i<listMoisturizersProductsPrice.size(); i++) {
				
				if(listMoisturizersProductsPrice.get(i).getText().contains(Integer.toString(minimumAloePrice))) {
					
					mp.getBtnAdd().get(i).click();
					break;
					
				}
				
			}
			log.info("Lowest Aloe Product added in cart successfully");
			
			minimumAlmondPrice = ms.getMinimumAlmondPrice();
			
			log.info("Lowest Almond Price on Page is: " + minimumAlmondPrice);
			
			for(int i =0; i<listMoisturizersProductsPrice.size(); i++) {
				
				if(listMoisturizersProductsPrice.get(i).getText().contains(Integer.toString(minimumAlmondPrice))) {
					
					mp.getBtnAdd().get(i).click();
					
				}
				
			}
			
			log.info("Lowest Almond Product added in cart successfully");
			
			mp.getCart().click();
			
			log.info("Click on Cart");
			
		}
		else if(Integer.parseInt(currentTemperature)>37) {
			lp.btnBuySunscreens().click();
			log.info("Selecting Sunscreens");
			listSunscreensProductsName = sp.getAllProductName();
			listSunscreensProductsPrice = sp.getAllProductPrice();
			
			ss.getspf50Andspf30Price();
			minimumSPF50Price = ss.getMinimumspf50Price();
			
			log.info("Lowest SPF-50 Price on Page is: " + minimumSPF50Price);
			
			for(int i =0; i<listSunscreensProductsPrice.size(); i++) {
				
				if(listSunscreensProductsPrice.get(i).getText().contains(Integer.toString(minimumSPF50Price))) {
					
					sp.getBtnAdd().get(i).click();
					break;
					
				}
				
			}
			log.info("Lowest SPF-50 Product added in cart successfully");
			
			minimumSPF30Price = ss.getMinimumspf30Price();
			
			log.info("Lowest SPF-30 Price on Page is: " +minimumSPF30Price);
			
			for(int i =0; i<listSunscreensProductsPrice.size(); i++) {
				
				if(listSunscreensProductsPrice.get(i).getText().contains(Integer.toString(minimumSPF30Price))) {
					
					sp.getBtnAdd().get(i).click();
					
				}
				
			}
			log.info("Lowest SPF-30 Product added in cart successfully");
			
			sp.getCart().click();
			
			log.info("Click on Cart");
			
		}
		else {
			
			log.info("Invalid Temperature");
		}
		
	}
	
	@Test(dependsOnMethods="addToCartProduct")
	public void checkout() throws InterruptedException {
		
		cp.getPayWithCard().click();
		
		List<WebElement> numberofIFrame = cp.getNumberofIFrame();
		
		System.out.println(numberofIFrame.size());
		
		driver.switchTo().frame(0);
		log.info("Switch to Frame");

		emailid = b.getEmailId();
		cardNumber = b.getCardNumber();
		cardExpiry = b.getMonthYear();
		cardCVC = b.getCVC();
		zipCode =b.getZipCode();
		expectedMessage = b.getAssertMessage();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(cp.getEnterEmail()));
		
		cp.getEnterEmail().sendKeys(emailid);
		log.info("Enter Email ID");

		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[1].value = arguments[0];", cardNumber, cp.getEnterCardNumber());
		log.info("Enter Card Number");
		
		js.executeScript("arguments[1].value = arguments[0];", cardExpiry, cp.getEnterExpiryMonthYear());
		log.info("Enter Card Expiry Month and Year");

		cp.getEnterCVC().sendKeys(cardCVC);
		log.info("Enter Card CVC");
		

		wait.until(ExpectedConditions.visibilityOf(cp.getEnterZipCode()));
		
		
		js.executeScript("arguments[1].value = arguments[0];", zipCode, cp.getEnterZipCode());
		log.info("Enter ZipCode");
		cp.getPay().click();
		log.info("Click on Final Pay Button");
		
		driver.switchTo().defaultContent();
		
		wait.until(ExpectedConditions.invisibilityOf(cp.getHeader()));
		
		Assert.assertEquals(conp.getConfirmationHeader().getText(), expectedMessage);
		log.info("Payment Confirmation");
		
		}
		
	@AfterTest
	public void taerdown() {
		b.teardown(driver);
	}
		
	}
	
	


