package com.tribalescale.qa.utilities;

import java.io.IOException;

public class getDataFromExcel {
	
	static public Object[][] getData(String path, String sheet) throws IOException
	{
		//no of rows  in the XL data sheet
		int rownum=XLUtils.getRowCount(path, sheet);
		// no of columns in the XL data sheet
		int colcount=XLUtils.getCellCount(path,sheet,1); // At-least specify one row to count the no of cols
															// present inside the row
		// Create a two dimensional string array
		// Should be the same size of that of the XL sheet
		Object data[][]=new Object[rownum][colcount]; // rownum and colcount give the exact no of values in the XL sheet
														// that is passed in logindata[][] // now the data size and array size both are equal
		// Read data and store it in a 2 dimensional array
		
		// Starting from 1 since index 0 is the header part
		for(int i=1;i<=rownum;i++)
		{
			// increment the columns
			for(int j=0;j<colcount;j++)// Since the col values start from index 0
			{
				// Extract data from XL
				// Since the data starts from index 1 for rows and 0 for col in XL sheet
				// We need to store the same value in the array
				// so the value index value for row will be i-1 since the array will 
				// store the data from and it will not be taking the header values of the XL sheet
				// for col its same as, the col reads from index 0 and saves it in the array in index 0
				
				// Get data from Xl and store in a 2 dim array
				data[i-1][j]=XLUtils.getCellData(path,sheet, i,j);// i is row and j is col
				
			}
			
		}
		return data; // returning 2 dim arrary
	} 

}
