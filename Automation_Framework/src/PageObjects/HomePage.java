package PageObjects;

import org.openqa.selenium.By;


public class HomePage {

	/*
	 * Author: Team2
	 * Date: 
	 * Description: This is class contains locators for common objects/elements  
	 *  	
	 */
	
	public static By time_txt = By.xpath("//div[@id='txt']");
	public static By date_txt = By.xpath("//span[@class='h4']");
	public static By newsletter_button = By.xpath("//span[@class='newsletter-icon']");
	public static By bookings_button = By.xpath("//span[@class='bookings-icon']");
	public static String hotels_menu = "http://www.phptravels.net/hotel/";
}
