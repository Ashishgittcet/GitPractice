package com.example.SeleniumDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.example.SeleniumDesign.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Ashish\\Desktop\\Selenium_Code\\NewDriverupdated\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landing= new LandingPage(driver);
		//landing.loginApplication("email15@example.com","Simmu@15");
		driver.findElement(By.id("userEmail")).sendKeys("email15@example.com");
		driver.findElement(By.id("userPassword")).sendKeys("Simmu@15");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//List<WebElement> products= driver.findElements(By.xpath("//div[contains(@class,'col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted')]"));
		/*for(int i=0;i<products.size();i++) {
			System.out.println(products.get(i).getText());
			
		}*/
		List<WebElement> Products= driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = Products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().contains("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:Last-of-type")).click();
		
		//WebElement prod = products.stream().filter(
				//l->l.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		
		//prod.findElement(By.xpath("//div[contains(@class,'card-body')]//following-sibling::button[contains(@class,'btn w-10 rounded')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//li/button)[3]")).click();
		List<WebElement> CartsProductsCheck = driver.findElements(By.xpath("//div[@class='cartSection']//following-sibling::h3"));
		boolean match = CartsProductsCheck.stream().anyMatch(k->k.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()=\"Checkout\"]")).click();
		driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']")));
		List<WebElement> options = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));  
		for(WebElement option:options) {
			if(option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}  
		}
		driver.findElement(By.xpath("//a[contains(text(),'Place Order ')]")).click();
		String str=driver.findElement(By.xpath("//td/h1")).getText();
		Assert.assertEquals(str, "THANKYOU FOR THE ORDER.");
		Thread.sleep(2000);
		
		driver.close();		
	}
	
	

}
