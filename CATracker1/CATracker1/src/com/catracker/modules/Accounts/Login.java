package com.catracker.modules.Accounts;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.catracker.objectrepository.Locators;
import com.catracker.utility.ApplicationFunctions;
import com.catracker.utility.ReportManager;
import com.catracker.utility.DataReader;
import com.catracker.utility.GlobalFunctions;
import com.catracker.utility.GlobalUserDefinedMessages;
import com.relevantcodes.extentreports.LogStatus;



//@Listeners(SampleListners.class)
public class Login extends ReportManager{
	public static WebDriver driver;
	public static Map<String, String> data;
	
	
	@Test ()
	public void TC1() throws Exception{
	
	test=extent.startTest( "Accounts / Account Creation For HUF");
	
	driver=GlobalFunctions.launchBrowser(driver);
	driver=GlobalFunctions.openUrl(driver, "url");
	
	GlobalFunctions.sendKeys(driver, Locators.ID, "YUvaraju");
	GlobalFunctions.click(driver, Locators.emailId);
   
	
	}
	
	
}
