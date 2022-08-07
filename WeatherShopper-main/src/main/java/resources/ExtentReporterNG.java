package resources;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public WebDriver driver;
	
	public ExtentReporterNG(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public static ExtentReports getReportObject() {
		
		ExtentSparkReporter extent = new ExtentSparkReporter(System.getProperty("user.dir")+"\\src\\main\\java\\Reports\\report.html");
		extent.config().setDocumentTitle("Weather Shopper Testing");
		extent.config().setReportName("Automation Testing");
		
		ExtentReports reporter = new ExtentReports();
		reporter.attachReporter(extent);
		reporter.setSystemInfo("Tester Name: ", "Youssef Machkira");
		
		return reporter;
		
	}

}
