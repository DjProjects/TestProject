package TestScripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonLibraries.CommonHelper;
import PageObjects.HomePage;
import SupportLibraries.ComplexReportFactory;
import SupportLibraries.ExcelHelper;
import SupportLibraries.SeleniumHelper;

public class ValidateBookingsSection extends SeleniumHelper{
	
	/*
	 * Author: Team2 
	 * Date: 04/08/2017
	 * TC Description: Validate Booking Section
	 * Comments:
	 * Revision History: 
	 */	
	
  @Test
  public void Validate_BookingsSection() throws Exception{
	  logger = ComplexReportFactory.getTest();	
	  Map<String, String> inputData = ExcelHelper.GetData("3");
	  CommonHelper.login_into_application(inputData);
	  driver.findElement(HomePage.bookings_button).click();
	  ValidateBrowserReady();	  
	  ArrayList CntList = new ArrayList();	  
	  int iRowCnt = driver.findElements(By.xpath("(//div[@id='bookings']/div[@class='row'])")).size();	  
	  for(int i=1;i<=iRowCnt;i++){
		  int iStartCntTemp = driver.findElements(By.xpath("(//div[@id='bookings']/div[@class='row'])["+i+"]/div[1]/span[1]/i[contains(@class,'voted')]")).size();
		  	  CntList.add(iStartCntTemp);		  
	  }
	  Set hs = new HashSet<>();
	  hs.addAll(CntList);
	  CntList.clear();
	  CntList.addAll(hs);
	  Collections.sort(CntList);	 
	  int iLeastStar = (int)CntList.get(0);
	  int iMaxStar = (int)CntList.get(CntList.size()-1);
	  String sScreenshot = CapturePageScreenshot();
	  for(int i=1;i<=iRowCnt;i++){
		  int iStartCntTemp = driver.findElements(By.xpath("(//div[@id='bookings']/div[@class='row'])["+i+"]/div[1]/span[1]/i[contains(@class,'voted')]")).size();
		  if(iStartCntTemp == iLeastStar)	{
			  String sHotelName = driver.findElement(By.xpath("(//div[@id='bookings']/div[@class='row'])["+i+"]/div[1]/a/b")).getText();
			  logger.log(LogStatus.INFO, "Hotel "+sHotelName+" Has a Least Rating "+iLeastStar, sScreenshot);
		  }
		  if(iStartCntTemp == iMaxStar)	{
			  String sHotelName = driver.findElement(By.xpath("(//div[@id='bookings']/div[@class='row'])["+i+"]/div[1]/a/b")).getText();
			  logger.log(LogStatus.INFO, "Hotel "+sHotelName+" Has a Maximum Rating "+iMaxStar, sScreenshot);
		  }
	  }
  }
}
