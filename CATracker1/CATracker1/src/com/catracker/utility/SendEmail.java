package com.catracker.utility;

import java.util.Properties;

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

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SendEmail {

	 
	public static WebDriver generateReports(WebDriver driver) throws Exception{
	    
	    
		final String username=GlobalFunctions.launchProperties("emaild");
		final String pwd=GlobalFunctions.launchProperties("password");
		
		
	 //   final String username = properties.getProperty("e-maild");
	 //   final String password = properties.getProperty("password");

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
	        message.setFrom(new InternetAddress(GlobalFunctions.launchProperties("emaild")));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(GlobalFunctions.launchProperties("ToAddress")));
	        message.setSubject("Testing Subject");
	        message.setText("PFA");

	        MimeBodyPart messageBodyPart = new MimeBodyPart();

	        Multipart multipart = new MimeMultipart();

	        messageBodyPart = new MimeBodyPart();
	     
	        String file=GlobalFunctions.launchProperties("ReportsPath");
	     //   String file = "C:\\Users\\yuvraj reddy\\Desktop\\Automation\\Little Caesars_WorkSpace\\LittileCaesars\\test-output\\emailable-report.html";
	        String fileName = "TestNG_REPORTS";
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
  @SuppressWarnings("unused")
public static WebDriver scroolBarValidate(WebDriver driver){
	  String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
		JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
		Boolean test = (Boolean) (scrollBarPresent.executeScript(execScript));
		if (test == true) {
			System.out.print("Scrollbar is present.");
		} else if (test == false){
			System.out.print("Scrollbar is not present.");
		}
		
		JavascriptExecutor scrollBarWorking = (JavascriptExecutor) driver;
	
		Boolean test1=(Boolean)(scrollBarWorking.executeScript("window.scrollTo(0, document.body.scrollHeight)"));
        if(test==true){
        	System.out.println("Scroll Bar Is Working");
        }else{
        	System.out.println("Scrool Bar Is Not Working"); 
        }
	  
	  
	return driver;
	  
  }
}
