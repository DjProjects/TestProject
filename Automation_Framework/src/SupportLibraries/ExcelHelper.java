package SupportLibraries;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelHelper {
	/*
	 * Author: Team2
	 * Date: 
	 * Description: This is class contains utility to read data from excel file for given test case id
	 *  	
	 */
	public static Map<String,String> GetData(String sTCID){
		HashMap hm= new HashMap();
	    try {

			String path = new File(".").getCanonicalPath();
			String sTestDataFile = path+"\\src\\TestData\\TestData.xls";
			File inputWorkbook = new File(sTestDataFile);
		    Workbook w;
		    int iDataRow=0;
		    w = Workbook.getWorkbook(inputWorkbook);
		    Sheet sheet = w.getSheet(0);   
		    for (int j = 0; j < sheet.getColumns()-1; j++) {
	          for (int i = 0; i < sheet.getRows()-1; i++) {
	            Cell TCID = sheet.getCell(j, i+1);
	            if(TCID.getContents().equals(sTCID)){
	            	//System.out.println("if loop");
	            	iDataRow = i;
	            	break;
	            }	            
	          }
	        }
		   // System.out.println(iDataRow);
		    for (int j = 0; j < sheet.getColumns()-1; j++) {
		      Cell sColumn = sheet.getCell(j+1,0);
		      Cell Data = sheet.getCell(j+1,iDataRow+1);
		     // System.out.println(sColumn.getContents()+":"+ Data.getContents());
		      hm.put(sColumn.getContents(), Data.getContents());
		    }
	    }catch(Exception e){
	    	System.out.println(e);
	    }
	    return hm;
	}


}
