package com.example.SeleniumDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.SeleniumDesign.pageobjects.CheckoutPage;
import com.example.SeleniumDesign.pageobjects.cartPage;
import com.example.SeleniumDesign.pageobjects.confirmationPage;
import com.example.SeleniumDesign.pageobjects.productCatalogue;
import com.example.SeleniumDesign.testcomponents.BaseTest;
import com.example.SeleniumDesign.testcomponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void SubmitOrder() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";
		//LandingPage landing = LaunchApplication();
		landing.loginApplication("email15999@example.com", "Simmu@15999");
		Assert.assertEquals("Incorrect email  password.",landing.getErrorMessage());
		
		

		
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";
		//LandingPage landing = LaunchApplication();
		landing.loginApplication("email15@example.com", "Simmu@15");
		productCatalogue productCatalogue = new productCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
		cartPage cartPage = new cartPage(driver);
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
	}

}
