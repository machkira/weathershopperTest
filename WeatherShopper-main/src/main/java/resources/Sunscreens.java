package resources;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.SunscreensPage;

public class Sunscreens {
	public WebDriver driver;
	List<WebElement> listProductsName;
	List<WebElement> listProductsPrice;
	String price[];

	ArrayList<String> spf50Price = new ArrayList<String>();

	int minspf50Price;

	ArrayList<String> spf30Price = new ArrayList<String>();

	int minspf30Price;
	
	SunscreensPage sp;

	public Sunscreens(WebDriver driver) {

		this.driver = driver;
		sp = new SunscreensPage(this.driver);

	}

	public void getspf50Andspf30Price() {
		

		listProductsName = sp.getAllProductName();

		listProductsPrice = sp.getAllProductPrice();

		for (int i = 0; i < listProductsName.size(); i++) {

			if (listProductsName.get(i).getText().contains("SPF-50")) {

				if (listProductsPrice.get(i).getText().contains("Rs.")) {

					price = listProductsPrice.get(i).getText().split("Rs.");
					spf50Price.add(price[1].trim());
				} else {

					price = listProductsPrice.get(i).getText().split(":");

					spf50Price.add(price[1].trim());
				}

			} else if (listProductsName.get(i).getText().contains("SPF-30")|(listProductsName.get(i).getText().contains("spf-30"))) {

				if (listProductsPrice.get(i).getText().contains("Rs.")) {

					price = listProductsPrice.get(i).getText().split("Rs.");
					spf30Price.add(price[1].trim());
				} else {

					price = listProductsPrice.get(i).getText().split(":");

					spf30Price.add(price[1].trim());
				}
			}

		}

	}

	public int getMinimumspf50Price() {


		minspf50Price = Integer.parseInt(spf50Price.get(0));
		
		for (int i = 0; i < spf50Price.size(); i++) {


			if (minspf50Price > Integer.parseInt(spf50Price.get(i))) {

				minspf50Price = Integer.parseInt(spf50Price.get(i));

			}

		}
		return minspf50Price;
	}

	public int getMinimumspf30Price() {

		minspf30Price = Integer.parseInt(spf30Price.get(0));
		
		for (int i = 0; i < spf30Price.size(); i++) {
			

			if (minspf30Price > Integer.parseInt(spf30Price.get(i))) {

				minspf30Price = Integer.parseInt(spf30Price.get(i));

				
			}

		}
		return minspf30Price;
	}

}
