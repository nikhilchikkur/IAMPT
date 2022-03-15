package com.pageObjects;

import com.stepDefinitions.InitializationStepDef;
import com.testBase.PageBase;

public class Adminlogin extends PageBase {
	public static String toolName = InitializationStepDef.toolName;

	// @FindBy(xpath = "//img[@title='Click to Approve']")
	 //public static WebElement voteApprove;
	 
	

	/**
	 * Instantiates a new Login page.
	 */
	public Adminlogin()

	{

		super(toolName);
	}
	public static void launchBrowsers() {

		
		remoteDriver.get("https://app-iam-pt.liventus.com/");
		
		

		}
	
}

