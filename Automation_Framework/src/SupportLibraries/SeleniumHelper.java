package SupportLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import PageObjects.GlobalVariables;

public class SeleniumHelper {
	
	/*
	 * Author: Team2
	 * Date: 
	 * Description: This is class contains generic methods to lanuch the browser, close the browser, taking screen shots,..etc  
	 *  	
	 */
	
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	
	@BeforeMethod
	public void beforeMethod(Method caller) throws Exception{		
		setup();		
		ComplexReportFactory.getTest(caller.getName(), "Automation Script Execution Summary Report");
	}
	
	@AfterMethod
	public void afterMethod(Method caller) {
		teardown();
		ComplexReportFactory.closeTest(caller.getName());
	
	}
	
	@AfterSuite
	public void afterSuite() {
		ComplexReportFactory.closeReport();
	}


	public static void setup() throws Exception{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_a");
		Date date = new Date();		
		String sTimeStamp = "Run_"+dateFormat.format(date).toString();
		String path = new File(".").getCanonicalPath();		
		String sResultsPath = path+"\\src\\Results\\"+sTimeStamp;	
		String sScreenshotsPath = path+"\\src\\Results\\"+sTimeStamp+"\\Screenshots\\";		
		if(GlobalVariables.sRunTimeFolderName.equals("")){
			new File(sResultsPath).mkdirs();
			new File(sScreenshotsPath).mkdirs();
			GlobalVariables.sRunTimeFolderName = sResultsPath;
			GlobalVariables.sScreenshotsFolderName = sScreenshotsPath;
		}
		
		
		Properties prop = new Properties();
		try {
            //load a properties file
			prop.load(new FileInputStream("Config.Properties"));
			if(prop.getProperty("Browser").equals("Firefox")){
				driver = new FirefoxDriver();
			}else if(prop.getProperty("Browser").equals("GoogleChrome")){
				System.setProperty("webdriver.chrome.driver", "E:\\SeleniumDrivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}else if(prop.getProperty("Browser").equals("IE")){
				System.setProperty("webdriver.ie.driver", "E:\\SeleniumDrivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if(prop.getProperty("ENV").equals("QA")){
			driver.get(prop.getProperty("QA_URL"));
		}else if(prop.getProperty("ENV").equals("STG")){
			driver.get(prop.getProperty("STG_URL"));
		}
		ValidateBrowserReady();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		GlobalVariables.sProject=prop.getProperty("Project");
		GlobalVariables.sReleaseName=prop.getProperty("Release");
		GlobalVariables.sEnvironment=prop.getProperty("ENV");
		GlobalVariables.sUser=prop.getProperty("User");
		
	}
	
	public static void teardown(){
		driver.quit();
	}
	
	public static String CapturePageScreenshot() throws Exception{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		int iScreenshotsCnt = new File(GlobalVariables.sScreenshotsFolderName).listFiles().length;
		String sFileName = "Screenshot"+(iScreenshotsCnt+1);
		String destFilePath = GlobalVariables.sScreenshotsFolderName+"\\"+sFileName+".png";
		File destFile = new File(destFilePath);
		FileUtils.copyFile(srcFile, destFile);
		String image= logger.addScreenCapture(destFilePath);
		return image;
	}
	
	public static void ValidateBrowserReady(){
		JavascriptExecutor objJS = ((JavascriptExecutor) driver);
		String status = objJS.executeScript("return document.readyState").toString();		
		while(!(status.equals("complete"))){
			try{
				Thread.sleep(1000);
			}catch(Exception e){				
			}
			status = objJS.executeScript("return document.readyState").toString();
		}
	}

}
