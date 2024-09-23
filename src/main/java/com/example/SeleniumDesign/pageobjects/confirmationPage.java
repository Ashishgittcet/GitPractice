package com.example.SeleniumDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.SeleniumDesign.AbstractComponent.AbstractComponent;

public class confirmationPage extends AbstractComponent{
	
	WebDriver driver;
	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);	
	}
	
	@FindBy(xpath="//td/h1")
	WebElement confirmationMessage;
	
	public String verifyConfirmationMessage() {
		return confirmationMessage.getText();
		
	}
	

}
