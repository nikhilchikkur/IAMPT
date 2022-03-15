package com.pageObjects;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

import java.util.Properties;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.Utilities.ReadDataSheet;
import com.stepDefinitions.InitializationStepDef;
import com.testBase.PageBase;
public class Open_Patient extends PageBase {
	

	

	/**
	 * The Class LoginPage implements the methods related to the actions
	 * performed in Login Page in the application.
	 * 
	 * @author Nchikkur
	 */

	

		/** String has driver type to Page Factory initialise web elements */
		
		public static String toolName = InitializationStepDef.toolName;

		// @FindBy(xpath = "//img[@title='Click to Approve']")
		 //public static WebElement voteApprove;
		 
		

		/**
		 * Instantiates a new Login page.
		 */
		public Open_Patient()

		{

			super(toolName);
		}
		public static void launchPatient() {

			
			remoteDriver.get("https://patient-iam-pt.liventus.com/");
			
			

			}
		
	}


