package TestScripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonLibraries.CommonHelper;
import PageObjects.HomePage;
import PageObjects.NewsLetterPage;
import SupportLibraries.ComplexReportFactory;
import SupportLibraries.ExcelHelper;
import SupportLibraries.SeleniumHelper;

public class ValidateNewsLetterSection extends SeleniumHelper{
	
	/*
	 * Author: Team2 
	 * Date: 04/08/2017
	 * TC Description: Validate News Letter
	 * Comments:
	 * Revision History: 
	 */	
	
  @Test
  public void ValidateNewsLetter() throws Exception{
	  logger = ComplexReportFactory.getTest();	
	  Map<String, String> inputData = ExcelHelper.GetData("2");
	  CommonHelper.login_into_application(inputData);
	  driver.findElement(HomePage.newsletter_button).click();
	  Thread.sleep(3000);
	  boolean bflag = false;
	  boolean bYesBtn = driver.findElement(NewsLetterPage.yes_togglebtn).isDisplayed();
	  String sScreenshot = CapturePageScreenshot();
	  logger.log(LogStatus.INFO, "Initial Toggle Button Status", sScreenshot);
	  driver.findElement(NewsLetterPage.yes_no_togglebtn).click();
	  Thread.sleep(3000);
	  sScreenshot = CapturePageScreenshot();
	  if(bYesBtn){
		  bflag = driver.findElement(NewsLetterPage.no_togglebtn).isDisplayed();
	  }else{
		  bflag = driver.findElement(NewsLetterPage.yes_togglebtn).isDisplayed(); 
	  }	  
	  if(bflag){
		  logger.log(LogStatus.PASS, "Toggle button is working as expected", sScreenshot);
	  }else{
		  logger.log(LogStatus.FAIL, "Toggle button is not working as expected", sScreenshot);
	  }
  }
}
