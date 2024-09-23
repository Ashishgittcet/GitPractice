package com.example.SeleniumDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.SeleniumDesign.AbstractComponent.AbstractComponent;

public class cartPage extends AbstractComponent{
	
	WebDriver driver;
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
		
	}
	@FindBy(xpath="//div[@class='cartSection']//following-sibling::h3")
	List<WebElement> productsTitle;
	
	@FindBy(xpath="//button[text()=\"Checkout\"]")
	WebElement checkOutEle;
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = productsTitle.stream().anyMatch(k->k.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void goToCheckout() {
		checkOutEle.click();
	}
	

}
