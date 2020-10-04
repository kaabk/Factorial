package com.tribalescale.qa.utilities;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.tribalescale.qa.base.BaseClass;


public class WebEventListener extends BaseClass implements WebDriverEventListener, ITestListener{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Value of the:" + element.toString() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured: " + error);
		//try {
			//takeScreenshotAtEndOfTest();
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
	}
	
	 @Override
	    public void onTestFailure(ITestResult result) {
	    	System.out.println("Failed Test");
			String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
			feature = feature.substring(feature.lastIndexOf(".") + 1);	
			test=extent.createTest(feature); // create new entry in the report
			test.log(Status.FAIL, "Test Case FAILED IS " + feature);
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
	    	//Get screenshot
	    	String screenshotPath= screenshot(result.getMethod().getMethodName());
			//Attach screenshot to extent report
			try {
				test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
			} catch (IOException e) {
					e.printStackTrace();
			}
	    }
	 @Override
	    public void onTestSuccess(ITestResult result) {
	    	
	    	System.out.println("Passed Test");
			String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
			feature = feature.substring(feature.lastIndexOf(".") + 1);	
			test=extent.createTest(feature); // create new entry in the report
			//test=extent.createTest(result.getName()); // create new entry in the report
			test.log(Status.PASS, "Test Case PASSED IS " + feature);
			//Get screenshot
	    	String screenshotPath=screenshot(result.getMethod().getMethodName());
			//Attach screenshot to extent report
			try {
				test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
			} catch (IOException e) {
					e.printStackTrace();
			}
	    }
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
		public void onTestSkipped (ITestResult result)
		{   System.out.println("Skipped Test");
			String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
			feature = feature.substring(feature.lastIndexOf(".") + 1);	
			test=extent.createTest(feature); // create new entry in the report
			//test=extent.createTest(result.getName()); // create new entry in the report
			test.log(Status.SKIP, "Test Case SKIIPED  IS " + feature);
			//Get screenshot
	    	String screenshotPath=screenshot(result.getMethod().getMethodName());
			//Attach screenshot to extent report
			try {
				test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
			} catch (IOException e) {
					e.printStackTrace();
			}
			
		}
		public void onFinish(ITestContext testContext)
		{
			
			extent.flush();
			
		}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

}