package com.example.SeleniumDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.SeleniumDesign.pageobjects.CheckoutPage;
import com.example.SeleniumDesign.pageobjects.LandingPage;
import com.example.SeleniumDesign.pageobjects.OrderPage;
import com.example.SeleniumDesign.pageobjects.cartPage;
import com.example.SeleniumDesign.pageobjects.confirmationPage;
import com.example.SeleniumDesign.pageobjects.productCatalogue;
import com.example.SeleniumDesign.testcomponents.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest2 extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups={"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		
		//LandingPage landing = LaunchApplication();
		landing.loginApplication(input.get("email"), input.get("password"));
		productCatalogue productCatalogue = new productCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		productCatalogue.goToCartPage();
		cartPage cartPage = new cartPage(driver);
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		cartPage.goToCheckout();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		List<WebElement> options = checkoutpage.getCountryList();
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}
		confirmationPage confirmationPage = checkoutpage.submitOrder();
		String confirmationMessage = confirmationPage.verifyConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		Thread.sleep(2000);

		
	}
	
	//To verify "ZARA COAT 3" is displaying in order page./
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() {
		
		landing.loginApplication("email15@example.com", "Simmu@15");
		OrderPage orderPage = new OrderPage(driver);
		orderPage.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() {
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.put("email", "email15@example.com");
		map.put("password", "Simmu@15");
		map.put("product", "ADIDAS ORIGINAL");
		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
		map1.put("email", "email15@example.com");
		map1.put("password", "Simmu@15");
		map1.put("product", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};
	}
	

}
