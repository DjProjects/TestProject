package CommonLibraries;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import PageObjects.CommonObjects;
import SupportLibraries.SeleniumHelper;

public class CommonHelper extends SeleniumHelper {
	
	/*
	 * Author: Team2
	 * Date: 
	 * Description: This is class contains common methods to navigate to different tabs and validations  
	 *  	
	 */
	

	public static void login_into_application(Map<String, String> inputData){
		new Select(driver.findElement(CommonObjects.currency_dropdown)).selectByVisibleText(inputData.get("CURRENCY"));
		driver.findElement(CommonObjects.language_dropdown).click();
		driver.findElement(By.xpath("//a[@data-langname='"+inputData.get("LANGUAGE")+"']")).click();
		ValidateBrowserReady();
		driver.findElement(CommonObjects.myaccount_dropdown).click();
		driver.findElement(CommonObjects.myaccount_login_btn).click();
		ValidateBrowserReady();		
		driver.findElement(CommonObjects.email_txt).sendKeys(inputData.get("USERNAME"));
		driver.findElement(CommonObjects.password_txt).sendKeys(inputData.get("PASSWORD"));
		driver.findElement(CommonObjects.login_btn).click();
		ValidateBrowserReady();
	}
	
	
	
}