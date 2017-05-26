package TestScripts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonLibraries.CommonHelper;
import PageObjects.HomePage;
import PageObjects.HotelsPage;
import SupportLibraries.ComplexReportFactory;
import SupportLibraries.ExcelHelper;
import SupportLibraries.SeleniumHelper;

public class ValidateHotelsSection extends SeleniumHelper{
	
	/*
	 * Author: Team2 
	 * Date: 04/08/2017
	 * TC Description: Validate Hotels Section
	 * Comments:
	 * Revision History: 
	 */	
	
  @Test
  public void Validate_HotelsSection() throws Exception{
	  logger = ComplexReportFactory.getTest();	
	  Map<String, String> inputData = ExcelHelper.GetData("4");
	  CommonHelper.login_into_application(inputData);
	  Thread.sleep(3000);
	  driver.navigate().to(HomePage.hotels_menu);		    
	  ValidateBrowserReady();
	  
	  driver.findElement(HotelsPage.location_txt).sendKeys(inputData.get("LOCATION"));
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@id='eac-container-citiesInput']/ul/li/div/b")).click();
	  
	  Calendar objCal = Calendar.getInstance();
	  SimpleDateFormat objFormat = new SimpleDateFormat("MM/dd/y");
      String sCheckinDate = objFormat.format(objCal.getTime());
	  objCal.add(objCal.DATE, 1);
	  String sCheckoutDate = objFormat.format(objCal.getTime());	  
	  driver.findElement(HotelsPage.checkindate_txt).clear();
	  driver.findElement(HotelsPage.checkindate_txt).sendKeys(sCheckinDate);
	  driver.findElement(HotelsPage.checkindate_txt).click();
	  driver.findElement(HotelsPage.checkoutdate_txt).clear();
	  driver.findElement(HotelsPage.checkoutdate_txt).sendKeys(sCheckoutDate);
	  driver.findElement(HotelsPage.checkoutdate_txt).click();
	  driver.findElement(HotelsPage.twostars_radio).click();
	  JavascriptExecutor objJS = (JavascriptExecutor) driver;
	  objJS.executeScript("document.getElementsByClassName('slider-handle round').item(0).style.cssText='left: 25.3165%';");
	  objJS.executeScript("document.getElementsByClassName('slider-handle round').item(1).style.cssText='left: 50.6329%;';");
	  driver.findElement(By.xpath("//label[contains(text(),'"+inputData.get("PROPERTYTYPE")+"')]")).click();
	  driver.findElement(HotelsPage.search_btn).click();
	  ValidateBrowserReady();
	  objJS.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	  
	  String sScreenshot = CapturePageScreenshot();
	  int iActCount = driver.findElements(HotelsPage.hotels_list).size();
	  int iExpectedCount = driver.findElements(By.xpath("//a[text()='"+inputData.get("LOCATION")+"']")).size();
	  if(iActCount==iExpectedCount){
		  logger.log(LogStatus.PASS, "Search Results are displayed as expected", sScreenshot);
	  }else{
		  logger.log(LogStatus.FAIL, "Search Results are not displayed as expected", sScreenshot);
	  }
  }
}
