package com.example.SeleniumDesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports ExtentgetReportsconfig() {
		
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation web Reports");
		reporter.config().setDocumentTitle("Tests Results");
		reporter.config().setTimelineEnabled(true);
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ashish");
		return extent;
		
	}

}
