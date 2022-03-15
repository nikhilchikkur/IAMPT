package com.stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.Utilities.ReadDataSheet;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.pageObjects.Open_Patient;
import com.pageObjects.Pateint_workflow;
import com.pageObjects.Patient_form;
import com.testBase.PageBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Patient  {
	public static Open_Patient  Open_Patient = new Open_Patient();
	public static Pateint_workflow Pateint_workflow = new Pateint_workflow();
	File file = new File("datafile.properties");
	static Properties prop = new Properties();
	FileInputStream fileInput = null;
	ReadDataSheet data = new ReadDataSheet();
	private Scenario scenario;

	public Patient() {
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
	 @Given("^Patient portal app$")
	    public void patient_portal_app() throws Throwable {
	        Open_Patient.launchPatient();;
	    }

	    @When("^Edit patient details$")
	    public void edit_patient_details() throws Throwable {
	    	Pateint_workflow.Patient_dash();
	    }

}
