package TestScripts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonLibraries.CommonHelper;
import PageObjects.HomePage;
import SupportLibraries.ComplexReportFactory;
import SupportLibraries.ExcelHelper;
import SupportLibraries.SeleniumHelper;

public class ValidateDateandTimeDisplayedonHomePage extends SeleniumHelper{
	
	/*
	 * Author: Team2 
	 * Date: 04/08/2017
	 * TC Description: Validate Date and Time Displayed on Home Page
	 * Comments:
	 * Revision History: 
	 */	
	
  @Test
  public void ValidateDateandTimeDisplayed() throws Exception{
	  logger = ComplexReportFactory.getTest();	
	  Map<String, String> inputData = ExcelHelper.GetData("1");
	  CommonHelper.login_into_application(inputData);
	  Date objDate = new Date();
	  SimpleDateFormat objSDF = new SimpleDateFormat("d MMMM Y");
	  
	  String sExpecteDate = objSDF.format(objDate);
	  String sActualDate = driver.findElement(HomePage.date_txt).getText();
	  String sScreenshot = CapturePageScreenshot();	
	  if(sExpecteDate.equals(sActualDate.trim())){
		  logger.log(LogStatus.PASS, "Date Value is Matched", sScreenshot);
	  }else{
		  logger.log(LogStatus.FAIL, "Date Value is not Matched", sScreenshot);
	  }
	  
	  String sActualTime = driver.findElement(HomePage.time_txt).getText();
	  objSDF = new SimpleDateFormat("HH:mm:ss");
	  String sExpectedTime = objSDF.format(objDate);
	  String[] arrsActualTime = sActualTime.split(":");
	  String[] arrsExpectedTime = sExpectedTime.split(":");
	  if(arrsActualTime[0].equals(arrsExpectedTime[0]) && arrsActualTime[1].equals(arrsExpectedTime[1])){
		  logger.log(LogStatus.PASS, "Time Value is Matched with Hours and Minutes, Actual Time is "+sActualTime+" and Expected Time is "+sExpectedTime, sScreenshot);
	  }else
		  logger.log(LogStatus.FAIL, "Time Value is not Matched Hours and Minutes, Actual Time is "+sActualTime+" and Expected Time is "+sExpectedTime, sScreenshot);
	  }
	  
  }

