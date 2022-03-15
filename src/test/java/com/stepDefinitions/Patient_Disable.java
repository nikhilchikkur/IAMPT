package com.stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.Utilities.ReadDataSheet;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.pageObjects.Admin_disable;
import com.pageObjects.Adminlogin;
import com.pageObjects.LoginPage;
import com.pageObjects.Patient_form;
import com.testBase.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Patient_Disable extends TestBase {
	public static Adminlogin adminlogin = new Adminlogin();
	public static Admin_disable  admin_disable = new Admin_disable();
	File file = new File("datafile.properties");
	static Properties prop = new Properties();
	FileInputStream fileInput = null;
	ReadDataSheet data = new ReadDataSheet();
	private Scenario scenario;

	public void Patientdisable_Stepdef() {
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

@Given("^Login as admin$")
public void login_as_admin() throws Throwable {
	Adminlogin.launchBrowsers();
}

@Then("^disable the patient$")
public void disable_the_patient() throws Throwable {
	Admin_disable.admindetails();
}

@Then("^Login as patient$")
public void login_as_patient() throws Throwable {
 
}

}