package com.stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.Utilities.ReadDataSheet;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.pageObjects.LoginPage;
import com.pageObjects.Organization_Login;
import com.pageObjects.Organization_Portal;
import com.pageObjects.Patient_form;
import com.testBase.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Organizationportal extends TestBase {
	public static Organization_Login organization_Login = new Organization_Login();
	public static Organization_Portal organization_Portal = new Organization_Portal();
	File file = new File("datafile.properties");
	static Properties prop = new Properties();
	FileInputStream fileInput = null;
	ReadDataSheet data = new ReadDataSheet();
	private Scenario scenario;

	public Organizationportal() {
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

    @Given("^Sign in to organization portal$")
    public void sign_in_to_organization_portal() throws Throwable {
        Organization_Login.Browseropen();
    }

    @When("^Activate and Deactivate organiztaion$")
    public void activate_and_deactivate_organiztaion() throws Throwable {
    	Organization_Portal.Activate_Deactivate();
    }

    @When("^Verify state of organization$")
    public void verify_state_of_organization() throws Throwable {
        
    }

}
