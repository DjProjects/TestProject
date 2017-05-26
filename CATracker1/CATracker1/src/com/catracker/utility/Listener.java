package com.catracker.utility;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;







//extends TestListenerAdapter

//implements ITestListener

public class Listener extends TestListenerAdapter {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	String filepath="F:\\SeleniumWorkSpace\\FrameWork\\src\\ScreenShorts";

	
	@Override
	public void onTestStart(ITestResult tr) {
		
		
		
		System.out.println("Hi Execution Started...");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println(" Method__" + result.getName() + "__ PASSED");

		// This will print the class name in which the method is present
		System.out.println(result.getTestClass());

		// This will print the priority of the method.
		// If the priority is not defined it will print the default priority as
		// 'o'
		System.out.println("Priority of this method is " + result.getMethod().getPriority());

		System.out.println(".....");
		tearDown(result);
		
	}

	@Override
	public void onTestFailure(ITestResult result){

		//tearDown(result);
		System.out.println("Method__" + result.getName() + "__ FAILED");
		System.out.println("Priority of this method is " + result.getMethod().getPriority());
		result.getName();
	  //  takeScreenShot(methodname);
    	System.out.println(".....");
    //	tearDown(result);
    	
	}

	//@Override
	public void onTestSkipped(ITestResult tr) {
		System.out.println("Method__" + tr.getName() + "__ SKIPPED");
		System.out.println(".....");
	}
	
	
	
	public WebDriver takeScreenShot(String str) {
		//driver=TranslationManager.driver;
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name 
		DateFormat df= new SimpleDateFormat("dd-MM-YYYY");
        Date d=new Date();
        String time=df.format(d);
	    try {
			FileUtils.copyFile(f,new File(filepath+"__"+str+"__"+time+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Failed Method Screen Shots Are  Placed In This Path Location"+filepath+"");
	    return driver;
			}  

	
	
	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public ExtentReports tearDown(ITestResult result) {
	    if (result.getStatus() == ITestResult.FAILURE) {
	      test.log(LogStatus.FAIL, "<pre>" + result.getThrowable().getMessage() + "</pre>");
	    }
	  /*  else if (result.getStatus() == ITestResult.SUCCESS) {
	    	test.log(LogStatus.PASS,"Test Case Sucess");
	    } */
	    else if (result.getStatus() == ITestResult.SKIP) {
	    	test.log(LogStatus.INFO,"Test Case Skipped");
	    }
	    
	    driver.quit();
	    extent.endTest(test);
	    extent.flush();
		return extent;
	}
	
	
    
}
	
	



