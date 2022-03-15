package com.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.stepDefinitions.InitializationStepDef;
import com.testBase.PageBase;



public class Organization_Portal extends PageBase{
	public static String toolName = InitializationStepDef.toolName;

	 @FindBy(id = "UserName")
	 public static WebElement Uname;
	 @FindBy(id = "Password")
	 public static WebElement Password;
	 @FindBy(xpath = "//div//button[text()='Submit']")
	 public static WebElement submitbtn;
	 @FindBy(xpath = "//div//h2[text()='Organization']")
	 public static WebElement organization;
	 @FindBy(xpath = "//div//span//select[@id='ddlActive']//option[text()='Deactive']")
	 public static WebElement deactive;
	 @FindBy(xpath = "//div//span//select[@id='ddlActive']//option[text()='Active']")
	 public static WebElement active;
	 @FindBy(xpath = "//span//a[@id='btnDeleteOrg_1']")
	 public static WebElement deleteuser;

	 @FindBy(id = "btn_DeleteOk")
	 public static WebElement confirmdelete;
	 @FindBy(xpath = "//div//button[@id='btn_ActivateOk']")
	 public static WebElement btnactivate;
	// @FindBy(xpath = "//div//button[text()='Ok']")
	//public static WebElement confirmdeletedeactivate;
	 @FindBy(id = "btnOpenOrganisation")
	 public static WebElement addorganization;
	 @FindBy(id = "Name")
	 public static WebElement orgname;
	 @FindBy(id = "ShortName")
	 public static WebElement orgshortname;
	 @FindBy(id = "AccountLimit")
	 public static WebElement userlimit;
	 @FindBy(xpath = "//label//input[@id='BillingEnabled']")
	 public static WebElement billing;
	 @FindBy(xpath = "//label//input[@value='True']")
	 public static WebElement Yestrue;
	 @FindBy(id = "btnAddOrganisation")
	 public static WebElement submitorg;
	 @FindBy(id = "btn_SuccessOk")
	 public static WebElement submitsuccess;
	 @FindBy(xpath = "//li//a[text()='Home']")
	 public static WebElement home;
	 @FindBy(xpath = "//div//h2[text()='Organization Admin']")
	 public static WebElement orgadmin;
	 
	 
	 public  Organization_Portal()

	 {

	 	super(toolName);
	 }
	 public static void Activate_Deactivate() throws Exception
	 {
	 	
	 	Uname.sendKeys("Bill");
	 	Password.sendKeys("Liventus22@");
	 	click(submitbtn, "submit" );
	 	Thread.sleep(5000);
	 	click(organization, "submit" );
	 	Thread.sleep(5000);
	 	click(deactive, "submit" );
	 	click(active, "submit" );
	 	Thread.sleep(5000);
	 	click(deleteuser, "submit" );
	 	click(confirmdelete, "submit" );
	 	click(deactive, "submit" );
	 	WebElement verifydeactivate=remoteDriver.findElement(By.xpath("//tr//td[text()='APTA Chapters']"));
	 	Assert.assertTrue(verifydeactivate.isDisplayed());
		System.out.println("verified deactivated user ");
		Thread.sleep(5000);
		click(active, "submit" );
		Thread.sleep(5000);
		click(deactive, "submit" );
		Thread.sleep(5000);
	 	click(deleteuser, "submit" );
	 	Thread.sleep(5000);
		click(btnactivate, "submit" );
		Thread.sleep(5000);
		//click(confirmdeletedeactivate, "submit" );
		WebElement verifyeactivate=remoteDriver.findElement(By.xpath("//tr//td[text()='APTA Chapters']"));
	 	Assert.assertTrue(verifyeactivate.isDisplayed());
		System.out.println("verified activated user ");
		Thread.sleep(5000);
		click(addorganization, "add new org" );
		orgname.sendKeys("TestORG2");
		orgshortname.sendKeys("Testshortnamee");
		userlimit.sendKeys("5");
		click(billing, "add new org" );
		click(Yestrue, "add new org" );
		click(submitorg, "add new org" );
		click(submitsuccess, "add new org" );
		WebElement verifyorg=remoteDriver.findElement(By.xpath("//tr//td[text()='TestORG2']"));
	 	Assert.assertTrue(verifyorg.isDisplayed());
		System.out.println("verified new organization added ");
		click(home, "navigate to home" );
		click(orgadmin, "navigate to home" );

}
}
