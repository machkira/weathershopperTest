package resources;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObject.MoisturizersPage;

public class Moisturizers {
	public WebDriver driver;
	List<WebElement> listMoisturizersProductsName;
	List<WebElement> listMoisturizersProductsPrice;
	String price[];

	ArrayList<String> aloePrice = new ArrayList<String>();

	int minAloePrice;

	ArrayList<String> almondPrice = new ArrayList<String>();

	int minAlmondPrice;
	
	MoisturizersPage mp;

	public Moisturizers(WebDriver driver) {

		this.driver = driver;
		mp = new MoisturizersPage(this.driver);

	}

	public void getAloeAndAlmondPrice() {
		

		listMoisturizersProductsName = mp.getAllProductName();

		listMoisturizersProductsPrice = mp.getAllProductPrice();

		for (int i = 0; i < listMoisturizersProductsName.size(); i++) {

			if (listMoisturizersProductsName.get(i).getText().contains("Aloe")) {

				if (listMoisturizersProductsPrice.get(i).getText().contains("Rs.")) {

					price = listMoisturizersProductsPrice.get(i).getText().split("Rs.");
					aloePrice.add(price[1].trim());
				} else {

					price = listMoisturizersProductsPrice.get(i).getText().split(":");

					aloePrice.add(price[1].trim());
				}

			} else if (listMoisturizersProductsName.get(i).getText().contains("Almond")|(listMoisturizersProductsName.get(i).getText().contains("almond"))) {

				if (listMoisturizersProductsPrice.get(i).getText().contains("Rs.")) {

					price = listMoisturizersProductsPrice.get(i).getText().split("Rs.");
					almondPrice.add(price[1].trim());
				} else {

					price = listMoisturizersProductsPrice.get(i).getText().split(":");

					almondPrice.add(price[1].trim());
				}
			}

		}

	}

	public int getMinimumAloePrice() {


		minAloePrice = Integer.parseInt(aloePrice.get(0));
		
		for (int i = 0; i < aloePrice.size(); i++) {


			if (minAloePrice > Integer.parseInt(aloePrice.get(i))) {

				minAloePrice = Integer.parseInt(aloePrice.get(i));

			}

		}
		return minAloePrice;
	}

	public int getMinimumAlmondPrice() {

		minAlmondPrice = Integer.parseInt(almondPrice.get(0));
		
		for (int i = 0; i < almondPrice.size(); i++) {
			

			if (minAlmondPrice > Integer.parseInt(almondPrice.get(i))) {

				minAlmondPrice = Integer.parseInt(almondPrice.get(i));

				
			}

		}
		return minAlmondPrice;
	}

}
