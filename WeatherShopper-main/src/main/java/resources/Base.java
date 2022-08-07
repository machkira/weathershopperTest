package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Base {

	public WebDriver driver;
	static String browser;
	static String url ;
	static Xls_Reader xss;
	private static String cardNumber;
	private static String monthYear;
	private static String cvc;
	private static String zipCode;
	static String assertionMessage;
	static String emailid;



	@Test
	public WebDriver initialization() throws IOException {

		Base.getDataFromExcel();
		Base.getDataFromPropertyFile();

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\driver\\chromedriver.exe");

			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\driver\\geckodriver.exe");

			driver = new FirefoxDriver();

		}

		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		return driver;
	}

	public String takeScreenshot(String methodName, WebDriver driver) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destinationPath = System.getProperty("user.dir") + "\\src\\main\\java\\reports\\" + methodName + ".jpeg";

		FileUtils.copyFile(src, new File(destinationPath));

		return destinationPath;

	}
	
	public static void getDataFromExcel() {
		xss = new Xls_Reader(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\Data.xlsx");

		browser = xss.getCellData("Sheet1", "browser", 2);
		url = xss.getCellData("Sheet1", "url", 2);
		emailid = xss.getCellData("Sheet1", "Email", 2);
		monthYear = xss.getCellData("Sheet1", "MonthYear", 2);
		cvc = xss.getCellData("Sheet1", "CVC", 2);
		zipCode = xss.getCellData("Sheet1", "ZipCode", 2);
		assertionMessage = xss.getCellData("Sheet1", "AssertMessage", 2);

	}
	
	public static void getDataFromPropertyFile() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.Properties");

		prop.load(fis);
		
		cardNumber = prop.getProperty("cardNumber");
	}
	
	
	public String getCardNumber() {
		
		return cardNumber;
		
	}
	
	public String getMonthYear() {
		
		return monthYear;
		
	}
	
	public String getCVC() {
		
		return cvc;
		
	}
	public String getZipCode() {
		
		return zipCode;
		
	}
	public String getEmailId() {
		
		return emailid;
		
	}
	
	public String getAssertMessage() {
		
		return assertionMessage;
		
	}
	
	public void teardown(WebDriver driver) {
		
		driver.quit();
	}
	
}
