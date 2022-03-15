package com.pageObjects;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.stepDefinitions.InitializationStepDef;
import com.testBase.PageBase;


/** String has remoteDriver type to Page Factory initialise web elements */
	
	

public class Patient_form extends PageBase{
	public static String toolName = InitializationStepDef.toolName;

	 @FindBy(id = "UserName")
	 public static WebElement username;
	 @FindBy(id = "Password")
	 public static WebElement password;
	 @FindBy(xpath = "//p//span[text()=' Submit']")
	 public static WebElement submit;
	 @FindBy(id = "footer-schedule")
	 public static WebElement schedule;
	 @FindBy(xpath = "//input[@name='loginfmt']")
	 public static WebElement addemail;
	 
	 @FindBy(id = "idSIButton9")
	 public static WebElement nextbutton;
	 @FindBy(id = "new-patient")
	 public static WebElement newpatient;
	 @FindBy(id = "SSN1")
	 public static WebElement SSN;
	 @FindBy(id = "FirstName")
	 public static WebElement fname;
	 @FindBy(id = "LastName")
	 public static WebElement lname;
	 @FindBy(id = "Email")
	 public static WebElement Email;
	 @FindBy(xpath = "//tr//td//x//input[@value='OK'] ")
	 public static WebElement ok;
	 @FindBy(xpath = "//div//span[contains(text(),'Patient Login and Intake Form')]")
	 public static WebElement patientmail;
	 @FindBy(xpath = "//div//a[contains(text(),'click here')][1]")
	 public static WebElement clickherelink;
	 @FindBy(xpath = "//div//input[@id='username']")
	 public static WebElement patientusrname;
	 @FindBy(id = "password")
	 public static WebElement patientpassword;
	 @FindBy(id = "confirmpassword")
	 public static WebElement confirmpassword;
	 @FindBy(id = "btnLogin")
	 public static WebElement btnLogin;
	 @FindBy(xpath = "//div//a[contains(text(),'click here')][2]")
	 public static WebElement accessclickherelink;
	 @FindBy(id = "username")
	 public static WebElement patientusername;
	 @FindBy(xpath = "//div//button[@id='Pivot28-Tab1']")
	 public static WebElement otheremail;
	 
	 @FindBy(id = "password")
	 public static WebElement patientpasswordd;
public  Patient_form()

{

	super(toolName);
}
public static void Patientdetails() throws Exception
{
	
	username.sendKeys("Bill");
	password.sendKeys("Liventus22@");
	click(submit, "submit" );
	Thread.sleep(5000);
	//schedule.sendKeys(Keys.CONTROL+"n");
	 String chord = Keys.chord(Keys.CONTROL,Keys.ENTER);
	 schedule.sendKeys(chord);
	remoteDriver.get("https://outlook.office365.com/mail/");
	Thread.sleep(5000);
	addemail.sendKeys("Nchikkur@liventus.com");
	Thread.sleep(8000);
	click(nextbutton, "nxtbutton" );
	//click(copy, "copy");
	Set<String>windows = remoteDriver.getWindowHandles();
	Iterator<String> it = windows.iterator();
	String parent=it.next();
	String child=it.next();
	remoteDriver.switchTo().window(child);
	Thread.sleep(5000);
	username.sendKeys("Bill");
	password.sendKeys("Liventus22@");
	click(submit, "submit" );
	Thread.sleep(5000);
	click(newpatient, "newpatient" );
	Thread.sleep(4000);
	SSN.sendKeys("891232956");
	fname.sendKeys("test2");
	lname.sendKeys("test3");
	Email.sendKeys("NChikkur@liventus.com");
	Thread.sleep(8000);
	//ok.click();
	click(ok,"Ok");
	remoteDriver.switchTo().window(parent);
	Thread.sleep(30000);
	click(otheremail,"Ok");
	Thread.sleep(8000);
	click(patientmail,"Ok");
	Thread.sleep(8000);
	click(clickherelink,"Ok");
	ArrayList<String> tabs = new ArrayList<String>(remoteDriver.getWindowHandles());
	remoteDriver.switchTo().window(tabs.get(2));
	Thread.sleep(8000);
	click(patientusrname,"Ok");
	patientusername.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	patientusername.sendKeys(Keys.chord(Keys.CONTROL, "c"));
	//Actions actions = new Actions(remoteDriver);
	//actions.keyDown(Keys.CONTROL).sendKeys("a");
	//actions.sendKeys("c");
	Thread.sleep(8000);
	//patientusrname.actions.keyDown(Keys.CONTROL).sendKeys("a");
	Thread.sleep(4000);
	patientpassword.sendKeys("Niktest");
	confirmpassword.sendKeys("Niktest");
	Thread.sleep(8000);
	click(btnLogin,"Ok");
	Thread.sleep(8000);
	//remoteDriver.switchTo().window(tabs.get(0));
	//Thread.sleep(8000);
	//click(accessclickherelink,"Ok");
	//click(accessclickherelink,"Ok");
	////Thread.sleep(8000);
	//remoteDriver.switchTo().window(tabs.get(1));
	//patientpassword.sendKeys("Niktest");
	//confirmpassword.sendKeys("Niktest");
	//Thread.sleep(8000);
	//click(btnLogin,"Ok");
	//click(accessclickherelink,"Ok");
	patientusername.sendKeys(Keys.chord(Keys.CONTROL, "v"));
//	actions.keyUp(Keys.CONTROL).build().perform();
	patientpasswordd.sendKeys("Niktest");
	click(btnLogin,"Ok");
	
	Thread.sleep(8000);
	//remoteDriver.close();
	remoteDriver.switchTo().window(tabs.get(0));
	click(clickherelink,"Ok");
	Thread.sleep(8000);
	//remoteDriver.switchTo().window(tabs.get(1));
	//Thread.sleep(8000);
	//remoteDriver.close();
	remoteDriver.switchTo().window(tabs.get(3));

	patientpassword.sendKeys("Niktest");
	confirmpassword.sendKeys("Niktest");
	Thread.sleep(8000);
	click(btnLogin,"Ok");
	 
}
}