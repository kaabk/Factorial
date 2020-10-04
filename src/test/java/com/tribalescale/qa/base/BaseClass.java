package com.tribalescale.qa.base;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import com.tribalescale.qa.utilities.ReadConfig;
import com.tribalescale.qa.utilities.WebEventListener;

public class BaseClass 
{
	ReadConfig  readconfig = new ReadConfig(); // Creating object
	// Integrating data from ReadConfig
	public String baseURL=readconfig.getApplicationURL(); 

	public static WebDriver driver;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger logger;
	//public String Excel_path =readconfig.getExcelData(); 
	public String Excel_path= System.getProperty("user.dir")+"/src/test/java/com/tribalescale/qa/testData/testData.xlsx";
	
	
	
	
	@BeforeMethod
	public void setup()
	{		
		// Initialization // Logger initiated within the setup method
		logger=Logger.getLogger("TribaleScale");// Project Name 
		PropertyConfigurator.configure("log4j.properties"); // Added Logger
		logger.setLevel(Level.DEBUG); // to get the debug log
		logger.debug("Debug logging has started ");
		System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		driver = new ChromeDriver();
		driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	}
	
	@AfterMethod
	public void tearDown()
	{
	driver.quit();
	 }
	
	//Method to take screenshot
	
	public String  screenshot(String testMethodName)
	{
		File source =( (TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String destination  = System.getProperty("user.dir") + "/Screenshots/" + testMethodName + "-" + timeStamp+ ".png" ;
		try
		{
			FileUtils.copyFile(source, new File(destination));
		}
		catch (IOException e) {
			e.printStackTrace();}
		
		return destination;
	}
	//method to return the factorial of a number
	public static BigInteger factorial(long number){ 
		BigInteger factorial = BigInteger.ONE;
		for (long i = number; i > 0; i--) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
			} return factorial;
		}
	
}