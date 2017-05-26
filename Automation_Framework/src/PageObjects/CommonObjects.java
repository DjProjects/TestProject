package PageObjects;

import org.openqa.selenium.By;


public class CommonObjects {

	/*
	 * Author: Team2
	 * Date: 
	 * Description: This is class contains locators for common objects/elements  
	 *  	
	 */
	
	
	public static By currency_dropdown = By.id("currency");
	public static By language_dropdown = By.xpath("//li[@class='pull-left width_change']/a");
	public static By myaccount_dropdown = By.xpath("//a[contains(text(),'My Account')]");
	public static By myaccount_login_btn = By.xpath("//a[normalize-space(text())='Login']");
	public static By email_txt = By.name("username");
	public static By password_txt = By.name("password");
	public static By login_btn = By.xpath("//button[contains(@class,'loginbtn')]");
	
}
