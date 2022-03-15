package com.pageObjects;

import com.stepDefinitions.InitializationStepDef;
import com.testBase.PageBase;

public class Organization_Login extends PageBase {
	public static String toolName = InitializationStepDef.toolName;

	// @FindBy(xpath = "//img[@title='Click to Approve']")
	 //public static WebElement voteApprove;
	 
	

	/**
	 * Instantiates a new Login page.
	 */
	public Organization_Login()

	{

		super(toolName);
	}
	public static void Browseropen() {

		
		remoteDriver.get("https://org-iam-pt.liventus.com/");
		
		

		}
	
}
