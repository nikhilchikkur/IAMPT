package com.pageObjects;

import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.stepDefinitions.InitializationStepDef;
import com.testBase.PageBase;

public class Pateint_workflow extends PageBase {
	public static String toolName = InitializationStepDef.toolName;

	 @FindBy(id = "username")
	 public static WebElement uname;
	 @FindBy(id = "password")
	 public static WebElement password;
	 @FindBy(id = "btnLogin")
	 public static WebElement login;
	 @FindBy(id = "lnkHep")
	 public static WebElement Hep;
	 @FindBy(xpath = "//div//a[text()='View Exercise Logs']")
	 public static WebElement excercise;
	 @FindBy(xpath = "//div//a[text()='2']")
	 public static WebElement nextpage;
	 @FindBy(xpath = "//div//button[text()='Back']")
	 public static WebElement back;
	 @FindBy(xpath = "//span[@class='caret']")
	 public static WebElement dropdown;
	 @FindBy(xpath = "//a[@href='/Patient/Patient?patientIdentifier=FPjbTXG5APXIH49YGJcvVw%253d%253d']")
	 public static WebElement myprofile;
	 @FindBy(id = "postedFile")
	 public static WebElement uploadDP;
	 @FindBy(id = "btnSubmit")
	 public static WebElement submitprofile;
	 @FindBy(xpath = "//span[text()='iAMPT']")
	 public static WebElement home;
	  @FindBy(xpath = "//a[@href='/Schedule/Index']")
	 public static WebElement Appointment;
	 @FindBy(xpath = "//td//span[text()='month']")
	 public static WebElement month;
	 @FindBy(xpath = "//td//span[text()='week']")
	 public static WebElement week;
	 @FindBy(xpath = "//a[@href='/MessageBoard/Index']")
	 public static WebElement msg;
	 @FindBy(xpath = "//div//select[@id='ClinicId']//option[text()='Midland']")
	 public static WebElement msgbox;
	 @FindBy(xpath = "//div//input[@class='choices__input choices__input--cloned']")
	 public static WebElement receiver1;
	 @FindBy(xpath = "//div[@class='dropdown1']//div[text()='Biller']")
	 public static WebElement receiver;
	 @FindBy(id = "StrPatientMessage")
	 public static WebElement pmessage;
	 @FindBy(id = "btn-send")
	 public static WebElement sendmessage;
	 @FindBy(xpath = "//li//a//b[text()='Sent']")
	 public static WebElement viewsent;
	 @FindBy(xpath = "//b//a[text()='See Details']")
	 public static WebElement viewdetails;
	 @FindBy(id = "btn-close")
	 public static WebElement closepopup;
	 @FindBy(id = "checkBoxAll")
	 public static WebElement selectall;
	 @FindBy(id = "lnkPatientIntake")
	 public static WebElement PatientIntake;
	 
	 
	 
public  Pateint_workflow()

{

	super(toolName);
}
public static void Patient_dash() throws Exception
{
	
	uname.sendKeys("Ganeshk");
	password.sendKeys("Liventus22@");
	click(login, "submit" );
	Thread.sleep(5000);
	click(Hep,"Click hep");
	Thread.sleep(5000);
	click(excercise,"Clickexcercise logs");
	Thread.sleep(5000);
	click(nextpage,"nextpage clicked");
	Thread.sleep(5000);
	click(back,"back clicked");
	Thread.sleep(5000);
	click(dropdown,"dropdown clicked");
	Thread.sleep(2000);
	click(myprofile,"profile");
	Thread.sleep(5000);
	uploadDP.sendKeys("C:\\Users\\nchikkur\\Downloads\\upload.jpg");
	Thread.sleep(5000);
	click(submitprofile,"profile updated");
	click(home,"redirected to home");
	click(Appointment,"redirected to Appointment");
	click(month,"redirected to month");
	click(week,"redirected to week");
	click(home,"redirected to home");
	click(msg,"redirected to msg board");
	click(msgbox,"redirected to msg box");
	click(receiver1,"redirected to receiver");
	click(receiver,"redirected to receiver");
	pmessage.sendKeys("patient message");
	click(sendmessage,"message sent");
	click(viewsent,"viewsent message");
	click(viewdetails,"viewdetails message");
	Thread.sleep(5000);
	click(closepopup,"close popup");
	click(home,"redirected to home");
	click(Hep,"Click hep");
	Thread.sleep(5000);
	click(selectall,"Click hep");
	Thread.sleep(5000);
	click(excercise,"Clickexcercise logs");
	Thread.sleep(5000);
	click(home,"redirected to home");
	Thread.sleep(5000);
	click(PatientIntake,"redirected to home");
	 
	 
}
}
