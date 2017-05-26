package com.catracker.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class ReportManager {
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected WebDriver driver;
    String time=GlobalFunctions.getTime();
  

    @AfterMethod
    protected void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, result.getThrowable().getMessage()+ "</pre>");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            test.log(LogStatus.PASS, "Test passed");
        }
        
        extent.endTest(test);     
        extent.flush();
        
        
       
    }
    
    @BeforeSuite
    public void beforeSuite() {
    	 if (extent == null) {
    
    		
    		String time=GlobalFunctions.getTime();
  
    extent = new ExtentReports(System.getProperty("user.dir")+"\\Data\\"+ "\\Report Manager\\"+"CA Tracker"+time+".html", true);
    //   
	 extent.config()
     .documentTitle("C2D SoftWare Private LTD")
     .reportName("CA Tracker");
	 extent
	 .addSystemInfo("Host Name", "Yuva")
     .addSystemInfo("Environment", "QA");
                         }
    
    }
    @AfterSuite
    protected void afterSuite() throws Exception {
        extent.close();
       // driver.quit();
    //	GlobalFunctions.sendEmail(driver);
    }
    
    
}
