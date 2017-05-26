package com.catracker.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataProvider {

	public static WebDriver driver;

	@Test()
	public static WebDriver sample(WebDriver driver){
		
	System.out.println("");
	return driver;
	}
	
	
	@org.testng.annotations.DataProvider(name="framework")
	
		public Object[][] getExcelData1() {
			Properties properties=new Properties();
			try {
				
				FileInputStream file=new FileInputStream("F:\\SeleniumWorkSpace\\FrameWork\\src\\Config.properties");
				try {
					properties.load(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  	
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Object[][] arrayObject = getExcelData(System.getProperty("user.dir")+"\\Data\\Test Data\\TestData.xls","");
			return arrayObject;
		}
		
	

    	/**
		 * @param File Name
		 * @param Sheet Name
		 * @return
		 */
		public String[][] getExcelData(String fileName, String sheetName) {
			String[][] arrayExcelData = null;
			try {
				FileInputStream fs = new FileInputStream(fileName);
				Workbook wb = Workbook.getWorkbook(fs);
				Sheet sh = wb.getSheet(sheetName);

				int totalNoOfCols = sh.getColumns();
				int totalNoOfRows = sh.getRows();
				
				arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
				
				for (int i= 1 ; i < totalNoOfRows; i++) {

					for (int j=0; j < totalNoOfCols; j++) {
						arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
					}

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				e.printStackTrace();
			} catch (BiffException e) {
				e.printStackTrace();
			}
			return arrayExcelData;
		}
		
	 
  }
	
	
	
	

