package com.catracker.utility;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.catracker.objectrepository.Locators;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

import com.catracker.utility.ReportManager;
import com.google.common.base.Function;


public class GlobalFunctions extends ReportManager{
	
	public static WebDriver driver;
	public static Map<String, String> data;
	static public final int DRIVER_IMPLICIT_WAIT = 20;
    static public final int PAGE_LOAD_WAIT = 30;
    static public final int Element_LOAD_WAIT = 20;
    public String fileInput;
    public static String inputfield;
    public static String ScroolBarStatus;
  //  public static String ScrollBarPresentY="Scroll Bar IS Present In Application";
  //  public static String ScrollBarPresentN="Scroll Bar IS Not Present In Application";
   
	public static By getLocator(String input){
			  By value = null; //By.id("login");
			  if(input.startsWith("XPH::"))
				  value = By.xpath(input.substring(5));
			  if(input.startsWith("ID::"))
				  value = By.id(input.substring(4));
			  if(input.startsWith("LT::"))
				  value = By.xpath(input.substring(4));
			  if(input.startsWith("CSS::"))
				  value = By.cssSelector(input.substring(5));
			  if(input.startsWith("CLASSNAME::"))
				  value = By.xpath(input.substring(11));
			  if(input.startsWith("NAME::"))
				  value = By.xpath(input.substring(6));
			  
			  
			  return value;
			  
			  
			  
		  }
		 public static WebDriver launchBrowser(WebDriver driver) throws Exception {
			 
			 String firefox="firefox";
			 String chrome="chrome";
		     String ie="ie";
		     System.setProperty("webdriver.chrome.driver","D:\\YuvaRaju\\CATracker1\\CATracker1\\Data\\chromedriver.exe");
		     
		  //   System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Library\\chromedriver.exe");
		     
		  
				
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\Library\\IEDriverServer.exe");
				
				
		      
				if(firefox.equals(launchProperties("browser"))){
					  driver=new FirefoxDriver();
					  driver.manage().window().maximize();
				//	  driver.get(launchProperties("url"));
					//  waitForPageToLoad( driver);
					  
				  }
			   else if(chrome.equals(launchProperties("browser"))) {
					driver=new ChromeDriver();
					driver.manage().window().maximize();
				//	driver.get(launchProperties("url"));
				//	waitForPageToLoad( driver);
				}
			   else if(ie.equals(launchProperties("browser"))) {
					driver=new InternetExplorerDriver();
					driver.manage().window().maximize();
					driver.get(launchProperties("url"));
					waitForPageToLoad( driver);
					
				}
				return driver;
	  }
		 public static WebDriver openUrl(WebDriver driver, String url) throws Exception{
			 driver.get(launchProperties(url));
			 waitForPageToLoad( driver);
			return driver;
		      
		  }
		 
	/*	 public static WebDriver login(WebDriver driver) throws Exception{
			 GlobalFunctions.getpropertiesData(driver,Locators.ClientCode, "clientcode");
			 GlobalFunctions.getpropertiesData(driver,Locators.UserName, "username");
			 GlobalFunctions.getpropertiesData(driver,Locators.PassWord, "Pwd");
			 GlobalFunctions.click(driver, Locators.SignInBtn);
			  waitForPageToLoad(driver);
			 
			
			 ApplicationFunctions.validatingLoginFunctionality(driver);
			 return driver;
		      
		  }
		  */
		 
		 public static WebDriver waitForPageToLoad(WebDriver driver) {
				
				
		        JavascriptExecutor js = (JavascriptExecutor)driver;
		        String command = "return document.readyState";
		        try {
		            for (int i = 0; i < PAGE_LOAD_WAIT; i++) {
		            	
		            	
		            	Thread.sleep(1000);
		                
		                if (js.executeScript(command).toString().equals("complete")) {
		                    break;
		                }
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				return driver;
		    }

			public static WebDriver threadSleep(WebDriver driver,By input) throws Exception{
				int seconds =60;
				  
				  while(seconds>=1){
				   try{
				    
				    boolean visible =  driver.findElement(input).isDisplayed();
				    
				    if(visible)
				     break;
				    else{
				     Thread.sleep(1000);
				     seconds--;
				    }
				     
				     
				   }
				   catch(Exception e){
				    Thread.sleep(1000);
				    seconds--;
				   }
				   
				  }
				
				
				return driver;
				
			}
			
			
		
			
			
			public static void takeScreenshot(WebDriver driver,String EnterScreenName) throws Exception {
				//File ff=new File("ScreenShorts");
			//	ff.mkdir();
				
				DateFormat df=new SimpleDateFormat("yyyy_MMM_dd hh_mm_ss");
				  Date d=new Date();
				  String time=df.format(d);
				//  System.out.println(time);
		File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"\\Data\\"+ "\\Screen Shorts\\"+EnterScreenName+time+".png"));		
			}

				
				
			
      public static String launchProperties(String prop){
	
	  
	     Properties properties = new Properties();
	   try{
		   
		
		     FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir")+"\\Config.properties");

		   properties.load(fileInput);
		
	      }catch(Exception e){
		      e.printStackTrace();
	     }
	
	         return properties.getProperty(prop);
}
      
      public static WebDriver doubleClick(WebDriver driver,By input){
    	  Actions a=new Actions(driver);
	        a.moveToElement(driver.findElement(input)).doubleClick().build().perform();
    	  
		return driver;
    	  
      }
      
      
      public static WebDriver clickUsingJavaScript(WebDriver driver,String Locator) throws Exception {
    	  
    	  GlobalFunctions.threadSleep(driver, getLocator(Locator));
    	  WebElement element = driver.findElement(getLocator(Locator));
    	  JavascriptExecutor executor = (JavascriptExecutor)driver;
    	  executor.executeScript("arguments[0].click();", element);
    	  
    	return driver;
     }
      public static WebDriver double_Click(WebDriver driver,String input){
    	  Actions a=new Actions(driver);
    	  WebElement dc=(WebElement) getLocator(input);
	        a.moveToElement(dc).doubleClick().build().perform();
	        
	   
    	  
		return driver;
    	  
      }
      
      public static WebDriver sendEmail(WebDriver driver) throws Exception{
    	    
    	    
    		final String username=launchProperties("emaild");
    		final String pwd=launchProperties("password");
    		
    		
  		    
    	

    	    Properties props = new Properties();
    	    props.put("mail.smtp.auth", true);
    	    props.put("mail.smtp.starttls.enable", true);
    	    props.put("mail.smtp.host", "smtp.gmail.com");
    	    props.put("mail.smtp.port", "587");

    	    Session session = Session.getInstance(props,
    	            new javax.mail.Authenticator() {
    	                protected PasswordAuthentication getPasswordAuthentication() {
    	                    return new PasswordAuthentication(username,pwd);
    	                }
    	            });

    	    try {

    	        Message message = new MimeMessage(session);
    	        message.setFrom(new InternetAddress(launchProperties("emaild")));
    	        message.setRecipients(Message.RecipientType.TO,
    	                InternetAddress.parse(launchProperties("ToAddress")));
    	        message.setSubject("VERTIV PRODUCT AUTOMATION REPORTS");
    	        message.setText("PFA");

    	        MimeBodyPart messageBodyPart = new MimeBodyPart();

    	        Multipart multipart = new MimeMultipart();

    	        messageBodyPart = new MimeBodyPart();
    	        
    	
    	    
    	        String time=GlobalFunctions.getTime();
    	        String file=System.getProperty("user.dir")+"\\Data\\"+ "\\Report Manager\\"+"CA Tracker"+time+".html";
    	    
    	       
    	        
    	        String fileName = "Crud Screens Automation Reports";
    	        DataSource source = new FileDataSource(file);
    	        messageBodyPart.setDataHandler(new DataHandler(source));
    	        messageBodyPart.setFileName(fileName);
    	        multipart.addBodyPart(messageBodyPart);

    	        message.setContent(multipart);

    	        System.out.println("Sending");

    	        Transport.send(message);

    	        System.out.println("Done");

    	    } catch (MessagingException e) {
    	        e.printStackTrace();
    	    }
			return driver;
			
    	    
    	  }
      
      //@@@ This method is used For Validate Scroll Bar
      public static  String  scroolBarVerification(WebDriver driver){
    	  String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
			JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
			Boolean b = (Boolean) (scrollBarPresent.executeScript(execScript));
			
			if (b == true) {
				test.log(LogStatus.INFO, GlobalUserDefinedMessages.ScrollBarPresentY);
				
				
				
			} else if (b == false){
				test.log(LogStatus.WARNING, GlobalUserDefinedMessages.ScrollBarPresentN);
				
			}
			
			/*JavascriptExecutor scrollBarWorking = (JavascriptExecutor) driver;
		
			scrollBarWorking.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        if(b==true){
	        	//System.out.println("Scroll Bar Is Working");
	        	test.log(LogStatus.INFO, GlobalUserDefinedMessages.ScrollBarPresentY);
	        }else{
	        //	System.out.println("Scrool Bar Is Not Working"); 
	        //	test.log(LogStatus.INFO, GlobalUserDefinedMessages.ScrollBarPresentN);
	        }*/
	        
	        
		//	ScroolDown(driver);
		//	ScroolUp(driver);
			
			return execScript;
    	  
    	  
		
    	  
      }
      
      public WebElement fluentWait(final By locator){
    	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    	            .withTimeout(60, TimeUnit.SECONDS)
    	            .pollingEvery(3, TimeUnit.SECONDS)
    	            .ignoring(NoSuchElementException.class);

    	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
    	        public WebElement apply(WebDriver driver) {
    	            return driver.findElement(locator);
    	        }
    	    });

    	    return foo;
    	};
      
      public static boolean scroolBarValidation(WebDriver driver){
    	  String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
			JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
			Boolean test = (Boolean) (scrollBarPresent.executeScript(execScript));
    	  JavascriptExecutor scrollBarWorking = (JavascriptExecutor) driver;
  		
			scrollBarWorking.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        if(test==true){
	        	System.out.println("Scroll Bar Is Working");
	        }else{
	        	System.out.println("Scrool Bar Is Not Working"); 
	        }
			return test;
      }
      
     
      
      
      
    //@@@@ This Method Is Used For Getting Values From List
      public static WebDriver gettingListValues(WebDriver driver,By Input) throws Exception{
    	//  GlobalFunctions.threadSleep(driver, driver.findElement(Input));
    //	  driver.findElement(getLocator(Locators.listBoxIcon)).click();
    	  Thread.sleep(2000);
    //	  GlobalFunctions.click(driver, GlobalFunctions.getLocator(Locators.listBoxIcon));
    	  List<WebElement> options1=	 new Select(driver.findElement(Input)).getOptions();
		   for(int i=0;i<options1.size();i++){
			  options1.get(i).getText();
			  System.out.println("The List Values Are	:	"+options1.get(i).getText()); 
			
		   }
		 //  System.out.println("The List Values Are	:	"+values);
    	  
		return driver;
    	  
      }
      
      
    //@@@@ This Method Is Used For Getting Values From DropDown
      public static WebDriver gettingDropDownValues(WebDriver driver,String enterlocator) throws Exception{
    	 
    	    data=DataReader.readData();
    	    System.out.println("The Values In The Drop Down Are : ");
  	        GlobalFunctions.threadSleep(driver, getLocator(enterlocator));
  		List<WebElement> s=    new Select(driver.findElement(getLocator(enterlocator))).getOptions();
  		
  		for(int i=0;i<s.size();i++){
  			System.out.println( s.get(i).getText());
  		}
  		
	    	return driver;
	 		
		   }
		
    	  
		
    	  
     
      
      
      
     
      
      
    //@@@@ This Method Is Used For Click On Any Element.
      public static WebDriver click(WebDriver driver,String EnterLocator) throws Exception{
      	 // data=DataReader.readData();
      	  
      	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
      	  driver.findElement(getLocator(EnterLocator)).click();
      	  	  
      	  return driver; 
      	  
      }
      
      
      //@@@@ This Method Is Used For Enter Text To TextBox.
      public static WebDriver getpropertiesData(WebDriver driver,String EnterLocator,String EnterText) throws Exception{
      	  data=DataReader.readData();
      	  
      	  threadSleep(driver, getLocator(EnterLocator));
      	  driver.findElement(getLocator(EnterLocator)).sendKeys(GlobalFunctions.launchProperties(EnterText));
      	  	  
      	  return driver;
      	  
      }

     
      
      
      //@@@@ This Method Is Used For Enter Text To TextBox.
      public static WebDriver sendKeys(WebDriver driver,String EnterLocator,String EnterText) throws Exception{
      	  data=DataReader.readData();
      	  
      	  threadSleep(driver, getLocator(EnterLocator));
      	  driver.findElement(getLocator(EnterLocator)).sendKeys(EnterText);
      	  
      	//data.get("AccountCode");
      	  	  
      	  return driver;
      	  
      }
      
      
      
      
    
      
      
    //@@@@ This Method Is Used For Send Text To TextBox Through Actions.
      public static WebDriver sendKeysThroughActions(WebDriver driver,String EnterLocator,String EnterText) throws Exception{
      	  data=DataReader.readData();
      	  
      	  
      	WebElement actions = driver.findElement(getLocator(EnterLocator));
  	   
   	  Actions navigator = new Actions(driver);
         navigator.click(actions)
             .sendKeys(Keys.END)
             .keyDown(Keys.SHIFT)
             .sendKeys(Keys.HOME)
             .keyUp(Keys.SHIFT)
             .sendKeys(Keys.BACK_SPACE)
            //.sendkeys("9");
             .sendKeys(data.get(EnterText))
             .perform();
      	  
     // 	  GlobalFunctions.threadSleep(driver, GlobalFunctions.getLocator(EnterLocator));
      //	  driver.findElement(GlobalFunctions.getLocator(EnterLocator)).sendKeys(data.get(EnterText));
      	  	  
      	  return driver;
      	  
      }
      
      
      //@@@@ This Method Is Used For Clear Text in To TextBox.
      public static WebDriver clearText(WebDriver driver,String EnterLocator) throws Exception{
      	  data=DataReader.readData();
      	  
      	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
      	  driver.findElement(getLocator(EnterLocator)).clear();
      	  	  
      	  return driver;
      	  
      }
      
      

     

      

				
    //@@@@ This Method Is Used To Get Text From Text Box and soon.
      public static  String getText(WebDriver driver,String EnterLocator) throws Exception{
      	  data=DataReader.readData();
      	  
      	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
      	  String gettext=  driver.findElement(getLocator(EnterLocator)).getText();
      	  return gettext;
      	  
      }
      
     
      
      //@@@@ This Method Is Used to Check Wheather An Element Is Displayed or not
      public static boolean elementIsDisplayed(WebDriver driver,String EnterLocator) throws Exception{
    	  
    	 // data=DataReader.readData();
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
	     boolean b=  driver.findElement(getLocator(EnterLocator)).isDisplayed();
	   	        
	        try{
	        	 if(b==true){
	 	        	System.out.println("Element Is Displayed");
	 		
	 	        }
	        }catch(Exception e){
	        	System.out.println(e);
	        	System.out.println("Element Is Not Displayed");
	        }
    	  return b;
    	  
      }
      
      
      //@@@@ This Method Is Used to Check Wheather An Element Is Enabled or not
      public static boolean elementIsEnabled( WebDriver driver,String EnterLocator) throws Exception{
    	  
    	 // data=DataReader.readData();
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
	     boolean b=  driver.findElement(getLocator(EnterLocator)).isEnabled();
	   	        
	        try{
	        	 if(b==true){
	 	        	System.out.println("Element Is Enabled");
	 		
	 	        }
	        }catch(Exception e){
	        	System.out.println(e);
	        	System.out.println("Element Is Not Enabled");
	        }
			return b;
    	  
    	  
      }
      
      
    //@@@@ This Method Is Used to Check Wheather An Element Is Selected or not
      public static boolean elementIsSelected(WebDriver driver,String EnterLocator) throws Exception{
    	  
    	 // data=DataReader.readData();
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
	     boolean b=  driver.findElement(getLocator(EnterLocator)).isSelected();
	   	        
	        try{
	        	 if(b==true){
	 	        	System.out.println("Element Is Selected");
	 		
	 	        }
	        }catch(Exception e){
	        	System.out.println(e);
	        	System.out.println("Element Is Not Selected");
	        }
    	  return b;
    	  
      }
      
     
      public void f() {
    	  System.out.println("Dropdown Logic For Element Validation");
    	    String id="ctl00_mainContent_ddl_Adult";
    	 List<WebElement> s=new Select(driver.findElement(By.id(id))).getOptions();
    	  System.out.println(s.size());
    	  for (int i = 0; i < s.size(); i++) {
    	  
    	   
    	   
    	   if(s.get(i).isSelected())
    	   {
    	   System.out.println(s.get(i).getText());
    	   
    	   }else
    	   {
    	    System.out.println("Element Is Disabled");
    	   }
    	  }
      }
      
     


			
      
      
   // Unchecking
      
      public static boolean checkbox_Unchecking(WebDriver driver,String EnterLocator) throws Exception {
            threadSleep(driver, getLocator(EnterLocator));
    	  
            WebElement ele=driver.findElement(getLocator(EnterLocator));
    	  boolean checkstatus=  ele.isSelected();
      
      
      if (checkstatus == true) {
    	  ele.click();
      System.out.println("Checkbox is unchecked");
      } else {
      System.out.println("Checkbox is already unchecked");
      }
	return checkstatus;
      }
      
      public static boolean radioButton_Deselect(WebDriver driver,String EnterLocator) throws Exception {
       threadSleep(driver, getLocator(EnterLocator));
  	  
          WebElement ele=driver.findElement(getLocator(EnterLocator));
  	  boolean checkstatus=  ele.isSelected();
    
    
    if (checkstatus == true) {
  	  ele.click();
    System.out.println("Radio Button is deselected");
    } else {
    System.out.println("Radio Button was already Deselected");
    }
	return checkstatus;
    }
      
      public static String getToolTip(WebElement toolTipofWebElement)throws InterruptedException {
    	  
    		  
    		  String tooltip = toolTipofWebElement.getAttribute("title");
    		  System.out.println("Tool text :  " + tooltip);
    		  return tooltip;
    		  }
      
      
      
      public static WebDriver  highlightelement(WebDriver driver,String EnterLocator) throws Exception {
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
      	  
          WebElement element=driver.findElement(getLocator(EnterLocator));
    	  
          for (int i = 0; i < 2; i++) {
  			JavascriptExecutor js = (JavascriptExecutor) driver;
  			js.executeScript(
  					"arguments[0].setAttribute('style', arguments[1]);",
  					element, "color: red; border: 3px solid red;");
  			js.executeScript(
  					"arguments[0].setAttribute('style', arguments[1]);",
  					element, "");
  		}
		return driver;

    	  }
      
      
      public static WebDriver uplodFile(WebDriver driver,String EnterLocator,String EnterPathOfTheFile) throws Exception{
    	  
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
    	  WebElement element=driver.findElement(getLocator(EnterLocator));
  		  element.click();
                 //Which calls the autoit exe file
  		  Runtime.getRuntime().exec(EnterPathOfTheFile);
    	  
    	  
		return driver;
    	  
      }
      
      public static WebDriver selectValueByIndex(WebDriver driver,String EnterLocator,String EnterIndex) throws Exception{
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
    	  
    	  
    	 int sss= Integer.parseInt(data.get(EnterIndex));
    	
  		  new Select(driver.findElement(getLocator(EnterLocator))).selectByIndex(sss);
  		  
    	  
		return driver;
    	  
      }
     
      public static WebDriver selectValueByVisibleText(WebDriver driver,String EnterLocator,String EnterVisibleText) throws Exception{
    	  data=DataReader.readData();
    	  threadSleep(driver, getLocator(EnterLocator));
  		  new Select(driver.findElement(getLocator(EnterLocator))).selectByVisibleText(data.get(EnterVisibleText));
  		//  new Select (driver.findElement(By.cssSelector("div.col-md-4 > select.form-control"))).selectByVisibleText("HUF");
    	  
		return driver;
    	  
      }
      
      public static WebDriver selectValueByVisibleText_Normal(WebDriver driver,String EnterLocator,String EnterVisibleText) throws Exception{
    	  data=DataReader.readData();
    	  threadSleep(driver, getLocator(EnterLocator));
  		  new Select(driver.findElement(getLocator(EnterLocator))).selectByVisibleText(EnterVisibleText);
    	  
		return driver;
    	  
      }
      
      
      public static String verifyTextPresentInTextBox(WebDriver driver,String EnterLocator ) throws Exception{
    	threadSleep(driver, getLocator(EnterLocator));
    	
    	  String textInsideInputBox=driver.findElement(getLocator(EnterLocator)).getAttribute("value");
    	 
    	  
    	  
    	 
    	  boolean b=(boolean)textInsideInputBox.isEmpty() && (textInsideInputBox!=null) ;
    	  if(b==true){
    		  inputfield=" Yes : Input field is Empty";
    		 // System.out.println("Input field is Empty");
    	  }else{
    	      inputfield="Input field is Full With Text And The Text Is  :  "+ textInsideInputBox;
    		 // System.out.println("Input field is Full With Text And The Text Is  :  "+textInsideInputBox);
    	  }
    	  
    	  
  		return inputfield;
    	    	  
      }
      
      
      public static boolean verifyTextPresent(WebDriver driver,String EnterSearchText)throws Exception{
    	  
    	  
    	  boolean b=(boolean)driver.getPageSource().contains(EnterSearchText);
    	  System.out.println("The Status Of The Text After Searching In The Page Is  :  "+b);
		  return b;
    	  
    	  
      }
      
      public static boolean elementPresent(WebDriver driver,String EnterLocator) throws Exception{
    	  threadSleep(driver, getLocator(EnterLocator));
    	 
    	  boolean b=driver.findElements(getLocator(EnterLocator)).size() != 0;
    	  
    	  if(b==true){
    		  System.out.println("Element is Present");
    	  }else{
    		  System.out.println("Element is Not Present");
    	  }
    	  
    	  
    	 
    	  
		return b;
    	  
      }
      
      public static WebDriver rightClick(WebDriver driver,String EnterLocator)throws Exception{
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
    	  WebElement ele=driver.findElement(getLocator(EnterLocator));
    	  Actions action = new Actions(driver).contextClick(ele);
    	  action.build().perform();
		  return driver;
      }
       
      
      public static WebDriver verifyImage(WebDriver driver,String EnterLocator ) throws Exception {
    	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
    		
    		WebElement ImageFile = driver.findElement(getLocator(EnterLocator));
    	        
    	        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
    	        if (!ImagePresent)
    	        {
    	             System.out.println("Image not displayed.");
    	        }
    	        else
    	        {
    	            System.out.println("Image displayed.");
    	        }
				return driver;
    		}
      
    //@@@@ This Method Is Used For Click On Any Element.
      public static WebDriver clickRnD(WebDriver driver,String EnterLocator) throws Exception{
      	  data=DataReader.readData();
      	  
      	  GlobalFunctions.threadSleep(driver, getLocator(EnterLocator));
      	  driver.findElement(getLocator(EnterLocator)).click();
      	  	  
      	  return driver; 
      	  
      }
      
      
      // This Method is used to get Current System Date & Time
      
      public static String getTime(){
    	  
    	  DateFormat df=new SimpleDateFormat("yyyy_MMM_dd hh_mm_ss");
		  Date d=new Date();
		  String time=df.format(d);
		return time;
      }
      
      
      //Scrool
      
      public static boolean  ScroolDown(WebDriver driver){
    	  
    	  JavascriptExecutor jse = (JavascriptExecutor)driver;
    	  
		
		boolean b=	 jse.executeScript("window.scrollBy(0,1000)", "") != null;
		
		if(b=true){
			test.log(LogStatus.INFO, GlobalUserDefinedMessages.ScrollDownY);
		}else{
			test.log(LogStatus.WARNING, GlobalUserDefinedMessages.ScrollDownN);
		}
			return false;
    	  
		
    		
    	 
    	    	  
      }
      
      public static boolean ScroolUp(WebDriver driver) throws Exception{
    	  Thread.sleep(2000);
    	  JavascriptExecutor jse = (JavascriptExecutor)driver;
         boolean b=	  jse.executeScript("scroll(0, -1000);") != null;
    	 
       if(b==true){
        	 test.log(LogStatus.WARNING, GlobalUserDefinedMessages.ScrollUpN);
       }else{
    	   test.log(LogStatus.INFO, GlobalUserDefinedMessages.ScrollUpY);
       }
        	 
			return false;
         
    	      }            
}


			


