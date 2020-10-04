package com.tribalescale.qa.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tribalescale.qa.base.BaseClass;

public class FactorialPage extends BaseClass{
	
public WebDriver ldriver;
	
	//Constructor
	
		public FactorialPage(WebDriver rdriver)
		{
			ldriver=rdriver;
			PageFactory.initElements(rdriver, this);
		}
	
	//PageFactory
	
	//Capture calculate Button
		@FindBy(xpath="//button[@id='getFactorial']")
		@CacheLookup
		WebElement calculateBtn;
		
		//Capture input 
		@FindBy(xpath="//input[@id='number']")
		@CacheLookup
		WebElement numberInput;	

		//Capture message
		@FindBy(xpath="//p[@id='resultDiv']")
		@CacheLookup
		WebElement resultTxt;	
		
		//click calculate button
		public void clkCalculateBtn() 
		{
			calculateBtn.click();
		}
		
		//Set number
		public void setNumberInput(String nb) 
		{
			numberInput.sendKeys(nb);
		}
		
		// return order now text
		public String getMessageTxt() 
		{
			resultTxt = driver.findElement(By.xpath("//p[@id='resultDiv']"));
			
			WebDriverWait wait = new WebDriverWait(ldriver, 100);
			wait.until(ExpectedConditions.visibilityOf(resultTxt));
			return resultTxt.getText();
		}

}
