package com.tribalescale.qa.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="TribaleScale_ExtentReport-"+timeStamp+".html"; // Report name and time stamp in html format
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\ExtentReports\\"+repName);//specify location of the report
		
		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report - given by the user
		htmlReporter.config().setReportName("Functional Testing"); // name of the report - given by the user
		//htmlReporter.config().setTheme(Theme.DARK); //
	//	htmlReporter.config().setTheme(Theme.STANDARD); 
		String css = ".r-img {width: 50%;}";
	    htmlReporter.config().setCSS(css);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		// System info can be any info defined by the User that comes in the report
		// populate the common details
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","TribaleScale");
		extent.setSystemInfo("OS","Windows10");
		extent.setSystemInfo("Browser name","Chrome,Firefox,IE");
	}
	public void onTestSuccess (ITestResult result)
	{
		String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
		feature = feature.substring(feature.lastIndexOf(".") + 1);	
		test=extent.createTest(feature); // create new entry in the report
		//test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.PASS, "Test Case PASSED IS " + feature);
		
		}
	
	public void onTestFailure (ITestResult result)
	{
		
		String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
		feature = feature.substring(feature.lastIndexOf(".") + 1);	
		test=extent.createTest(feature); // create new entry in the report
		test.log(Status.FAIL, "Test Case FAILED IS " + feature);
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+ "-" + timeStamp+".png";
		try {
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		
	}
	public void onTestSkipped (ITestResult result)
	{
		String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
		feature = feature.substring(feature.lastIndexOf(".") + 1);	
		test=extent.createTest(feature); // create new entry in the report
		//test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "Test Case SKIIPED  IS " + feature);

		
	}
	public void onFinish(ITestContext testContext)
	{
		
		extent.flush();
		
	}
	
}
