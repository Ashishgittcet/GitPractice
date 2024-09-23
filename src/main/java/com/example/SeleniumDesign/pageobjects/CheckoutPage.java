package com.example.SeleniumDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.SeleniumDesign.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);	
	}
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> options;
	
	@FindBy(xpath="//div[@class='form-group']/input")
	WebElement CountryNameSend;
	
	@FindBy(xpath="//a[contains(text(),'Place Order ')]")
	WebElement submit;
	
	By getCountryListMessage =By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']");
	
	public List<WebElement> getCountryList() {
		CountryNameSend.sendKeys("ind");
		WaitForElementsToAppear(getCountryListMessage);
		return options;
	}
	
	public confirmationPage submitOrder() {
		submit.click();
		 return new confirmationPage(driver);
	}

}
