package com.example.SeleniumDesign.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.example.SeleniumDesign.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.ExtentgetReportsconfig();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();///Thread safe
	

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Started:" + result.getName());
		ExtentReports extent = ExtentReportNG.ExtentgetReportsconfig();
		test =extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		System.out.println("I successfully executed Listeners Pass code:" + result.getName());
		//test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	

		System.out.println("I failed executed Listeners Pass code: " + result.getName());
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		String filePath = null;
		
		try {
		     driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
			//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		//screenshot ,Attach to code

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Skipped:" + result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		 

	}

}
