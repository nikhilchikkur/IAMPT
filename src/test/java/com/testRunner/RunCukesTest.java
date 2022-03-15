package com.testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;
import com.testBase.PageBase;

/**
 * The RunCukesTest class collectively executes the feature files and creates a
 * BDD report.
 *
 * @author AKaivalliam
 * @version 1.0
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:" }, features = "com.features", glue = "com.stepDefinitions", monochrome = true)

public class RunCukesTest extends AbstractTestNGCucumberTests

{

	public static String resultFolder;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;

	/**
	 * The @BeforeClass method creates the report folder and instance.
	 * 
	 * @return Nothing.
	 */
	@BeforeClass
	public static void setup() {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);

		com.cucumber.listener.ExtentProperties extentProperties = com.cucumber.listener.ExtentProperties.INSTANCE;
		extentProperties.setReportPath(resultFolder + "/TestReport.html");

	}

	/**
	 * The @AfterClass method completes the report instance.
	 */
	@AfterClass
	public static void teardown() throws IOException {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);
		
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") +"/extent-config.xml"));
		try {
			PageBase.remoteDriver.close();
			PageBase.remoteDriver.quit();
			System.out.println("The webdriver is killed");
		} catch (Exception e) {
			System.out.println("The webdriver is not present");
		}
		
	}

	public static String[] featureList;
	static {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);

		try {
			final CucumberOptions old = (CucumberOptions) RunCukesTest.class.getAnnotation(CucumberOptions.class);

			Object handler = Proxy.getInvocationHandler(old);

			Field field = null;
			try {
				field = handler.getClass().getDeclaredField("memberValues");

			} catch (Exception e) {
				e.printStackTrace();
			}
			field.setAccessible(true);
			Map<String, String[]> memberValues;
			try {
				memberValues = (Map<String, String[]>) field.get(handler);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new IllegalStateException(e);
			}

			resultFolder = "Reports";
			File file = new File(resultFolder);
			file.mkdirs();

			Map systemInfo = new HashMap();
			systemInfo.put("Cucumber version", "v1.2.5");

			List<String> featureLists = new ArrayList<String>();
			String tagsLists = "";
			try {
				featureLists = TestRunner.featureList();
				tagsLists = TestRunner.tagsList();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

			int x = featureLists.size();
			featureList = new String[x];
			for (int i = 0; i < featureLists.size(); i++) {
				featureList[i] = featureLists.get(i);
			}

			String[] tags = { tagsLists };

			memberValues.put("features", featureList);
			if (!tagsLists.isEmpty()) {
				memberValues.put("tags", tags);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
