package com.stepDefinitions;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import com.Utilities.ReadDataSheet;
import com.aventstack.extentreports.gherkin.model.Scenario;

import com.pageObjects.LoginPage;
import com.pageObjects.Patient_form;
import com.testBase.TestBase;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class Login_Stepdef extends TestBase {
	public static LoginPage loginpage = new LoginPage();
	public static Patient_form  patient_form = new Patient_form();
	File file = new File("datafile.properties");
	static Properties prop = new Properties();
	FileInputStream fileInput = null;
	ReadDataSheet data = new ReadDataSheet();
	private Scenario scenario;

	public Login_Stepdef() {
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
    @Given("^I have launched the Iampt application$")
    public void i_have_launched_the_iampt_application() throws Throwable {
    	LoginPage.launchBrowser();
    }

    @When("^I fill the Patient form$")
    public void i_fill_the_patient_form() throws Throwable {
    	Patient_form.Patientdetails();
    }

    @When("^I login as Patient$")
    public void i_login_as_patient() throws Throwable {
        throw new PendingException();
    }

    @And("^I Fill the details$")
    public void i_fill_the_details() throws Throwable {
        throw new PendingException();
    }


}
