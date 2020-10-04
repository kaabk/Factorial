package com.tribalescale.qa.testCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tribalescale.qa.base.BaseClass;
import com.tribalescale.qa.pageObjects.FactorialPage;
import com.tribalescale.qa.utilities.getDataFromExcel;


public class TC_FACT_002 extends BaseClass{
	
  String expectedMessage="Please enter an integer";
  
  @Test(dataProvider="getData")
  public void invalidInput(String input)  {
	  
		logger.info("Started TC_FACT_002: Invalid input");
		
		//Create the object for FatorialPage
		FactorialPage fp = new FactorialPage(driver);
		
		//Enter number
		fp.setNumberInput(input);
		logger.info("Input entered");
		
		//Click calculate button
		fp.clkCalculateBtn();
		
		//validate result
		String actualMessage=fp.getMessageTxt();
			
		//instantiate soft assertion
		SoftAssert softAssert = new SoftAssert();
		
		//Verify the display of the success message
		if (actualMessage.contains(expectedMessage))
		{
			logger.info("Test Passed for :"+ input);
			softAssert.assertTrue(true);

		}
		else
		{
			logger.error("Test Failed for :"+input);
			softAssert.assertTrue(false);
			
		}
		softAssert.assertAll();
		logger.info("Completed TC_FACT_002:  Invalid input");
  }
  
	//Test Data
	@DataProvider
	public Object[][] getData () throws IOException {
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\tribalescale\\qa\\testData\\TestData.xlsx";
		return getDataFromExcel.getData(path,"TC_FACT_002");

	}
}