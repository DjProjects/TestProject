package PageObjects;

import org.openqa.selenium.By;


public class HotelsPage {

	/*
	 * Author: Team2
	 * Date: 
	 * Description: This is class contains locators for common objects/elements  
	 *  	
	 */
	
	public static By location_txt = By.id("citiesInput");
	public static By checkindate_txt = By.name("checkin");
	public static By checkoutdate_txt = By.name("checkout");
	public static By twostars_radio = By.xpath("//input[@name='stars' and @value='2']/../ins");
	public static By search_btn = By.id("searchform");
	public static By hotels_list = By.xpath("//div[contains(@class,'itemlabel')]");
}
