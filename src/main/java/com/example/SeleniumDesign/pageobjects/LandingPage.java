package com.example.SeleniumDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.SeleniumDesign.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
		
	}
	//pagefactory
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void loginApplication(String email,String password) {
		userName.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		
	}
	
	public String getErrorMessage() {
		WaitForWebElementsToAppear(errorMessage);
		 return errorMessage.getText();
	}
	
	public void url() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	

}
