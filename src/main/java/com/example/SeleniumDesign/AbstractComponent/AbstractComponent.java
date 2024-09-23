package com.example.SeleniumDesign.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.SeleniumDesign.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="(//li/button)[3]")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public void WaitForElementsToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void WaitForWebElementsToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void WaitForElementsToDisAppear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void goToCartPage() throws InterruptedException {
		Thread.sleep(2000);
		cartHeader.click();
	}
	
	public void goToOrdersPage() {
		orderHeader.click();
		//return;
		//OrderPage cartPage = new OrderPage(driver);
	}

}
