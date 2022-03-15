package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.stepDefinitions.InitializationStepDef;
import com.testBase.PageBase;

public class Admin_disable extends PageBase {
	public static String toolName = InitializationStepDef.toolName;

	 @FindBy(id = "UserName")
	 public static WebElement username;
	 @FindBy(id = "Password")
	 public static WebElement password;
	 @FindBy(xpath = "//p//span[text()=' Submit']")
	 public static WebElement submit;
	 @FindBy(xpath = "//a//x[text()='Organization']")
	 public static WebElement organization;
	 @FindBy(xpath = "//input[@value='Remove']")
	 public static WebElement deletepatients;
	 
	 
	 
	 
	 
public  Admin_disable()

{

	super(toolName);
}



public static void admindetails() throws Exception
{
	
	username.sendKeys("Administrator");
	password.sendKeys("Liventus22@");
	Thread.sleep(5000);
	click(submit, "submit" );
	Thread.sleep(5000);
	click(organization, "submit" );
	Thread.sleep(5000);
	click(deletepatients, "submit" );
}
}