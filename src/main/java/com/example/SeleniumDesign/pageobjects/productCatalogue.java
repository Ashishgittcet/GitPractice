package com.example.SeleniumDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.SeleniumDesign.AbstractComponent.AbstractComponent;

public class productCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		 PageFactory.initElements(driver,this);
		
		
	}
	//pagefactory
	@FindBy(xpath="//div[contains(@class,'col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted')]")
	List<WebElement> products;
	
	//button[@class='btn w-10 rounded']
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	//@FindBy(xpath="(//li/button)[3]")
	//WebElement cartHeader;
	
	
	
	//By productsBy= By.xpath("//div[contains(@class,'col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted')]");
	By productsBy=By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:Last-of-type");
	By toastMessage =By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		WaitForElementsToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(
				l->l.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click(); 
		WaitForElementsToAppear(toastMessage);
		WaitForElementsToDisAppear(spinner);
		
		
		
	}
	
	
	
	

}
