package com.catracker.utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;






public class DataReader {
 public static Map<String, String> data;
 
 
 
 
 public static Map readData() throws BiffException, IOException   
 {
 
	
 // create map to store web elements
 data = new HashMap<String, String> ();
 
// Workbook workbook = Workbook.getWorkbook(new java.io.File(GlobalFunctions.launchProperties("TestDataPath")));  //// it should be xls file and not xlsx.
 
 Workbook workbook = Workbook.getWorkbook(new java.io.File(System.getProperty("user.dir")+"\\Data\\Test Data\\TestData.xls"));
 
 
 //Sheet sheet = workbook.getSheet("Name Of The Sheet Name");
 Sheet sheet = workbook.getSheet(GlobalFunctions.launchProperties("SheetName")); // Sheet index , Sheet1 = 0, Sheet2 =1 etc

 int rowcount = sheet.getRows();
 
 for(int i=1;i<rowcount;i++)
 {
 

 Cell ObjectName = sheet.getCell(0,i); 
 Cell ObjectValue = sheet.getCell(1,i);
 

 
 data.put(ObjectName.getContents(),ObjectValue.getContents());
 //System.out.println("Input VariableName: "+ObjectName.getContents()+"        Variable Value from Excel::"+ ObjectValue.getContents());
 //System.out.println(ObjectName.getContents());
 
	 
  
 
 }
 return data;
 }
 
 
 
}

