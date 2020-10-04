package com.tribalescale.qa.testCases;

import java.io.IOException;
import java.math.BigInteger;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tribalescale.qa.base.BaseClass;
import com.tribalescale.qa.pageObjects.FactorialPage;
import com.tribalescale.qa.utilities.getDataFromExcel;


public class TC_FACT_001 extends BaseClass{
	
  String infiniteMessage="Infinity";
  String factorialMessage="The factorial of";
  int maxFact=170;
  
	  
  @Test(dataProvider="getData")
  public void validInput(String number)  {
	  
		logger.info("Started TC_001: The factorial of a valid positive integer");
		
		//Create the object for FatorialPage
		FactorialPage fp = new FactorialPage(driver);
		
		//Enter number
		fp.setNumberInput(number);
		logger.info("Number entered");
		
		//Click calculate button
		fp.clkCalculateBtn();
		logger.info("Calculate button clicked");
		
		//validate result
		String actualMessage=fp.getMessageTxt();
			
		//instantiate soft assertion
		SoftAssert softAssert = new SoftAssert();
		
		long nbLong= Long.parseLong(number);
		
		//Verify the display of the right message and factorial
		if ( (nbLong>maxFact) && actualMessage.contains(infiniteMessage)) 
		{
			logger.info("Test Passed for "+number);
			softAssert.assertTrue(true);
		}
		else if (nbLong<=maxFact && actualMessage.contains(factorialMessage)) {
			BigInteger fact= factorial(nbLong);
			String actualFact = actualMessage;
			if (actualMessage.contains("+")) 
				actualFact =actualMessage.substring(0,actualMessage.indexOf("+")-1);
			if (actualMessage.contains("."))
					actualFact = actualFact.replace(".","");
			
			if (fact.toString().contains(actualFact)) {
				logger.info("Test Passed for "+number);	
				softAssert.assertTrue(true);
			}
		}
		else
		{
			logger.error("Test Failed for "+number);
			softAssert.assertTrue(false);
			
		}
		softAssert.assertAll();
		logger.info("Completed TC_001:  The factorial of a valid positive number");
  }
  
	//Test Data
	@DataProvider
	public Object[][] getData () throws IOException {
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\tribalescale\\qa\\testData\\TestData.xlsx";
		return getDataFromExcel.getData(path, "TC_FACT_001");

	}
}