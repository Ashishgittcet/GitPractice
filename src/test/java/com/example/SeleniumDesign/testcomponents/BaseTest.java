package com.example.SeleniumDesign.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.example.SeleniumDesign.pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landing;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\example\\SeleniumDesign\\resources\\Global.properties");
		properties.load(fis);
		String BrowserName = properties.getProperty("browser");
		if (BrowserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Ashish\\Desktop\\Selenium_Code\\NewDriverupdated\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (BrowserName.equalsIgnoreCase("firefox")) {

		} else if (BrowserName.equalsIgnoreCase("edge")) {
			// Set the driver path
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\Ashish\\Desktop\\Selenium_Code\\NewDriverupdated\\edge.exe");

			// Start Edge Session
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
		//Read Json to string
		String JsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException {
		driver = initializeDriver();
		landing = new LandingPage(driver);
		landing.url();
		return landing;

	} 
	
	@AfterMethod(alwaysRun=true)
	public void TearDown() {
		driver.close();
	}

}
