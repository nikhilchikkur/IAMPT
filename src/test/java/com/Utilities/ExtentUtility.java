package com.Utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * This class is used to configure the extent report to be generated in a
 * specific folder with screenshots and required informations
 * 
 * @author AKaivalliam
 * @version 1.0
 */

public class ExtentUtility {

	public static ExtentTest test;
	public static ExtentReports extent;
	public static String reportFolder = "";
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static Logger logger = Logger.getLogger(ExtentUtility.class);
	
	private ExtentUtility()
	{
		logger.info("Building extent report for Test results");
	}

	public synchronized static ExtentReports getReporter() {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		logger.info("Name of current method : " + nameofCurrMethod);

		if (extent == null) {
			
			String extentReportPath = new File(System.getProperty("user.dir") +"/Reports/TestReport.html").getPath();
			System.out.print("extent report name is : " + extentReportPath);
			extent = new ExtentReports(extentReportPath, true, Locale.ENGLISH);
			extent.addSystemInfo("Selenium Version", "3.7.1");
			extent.addSystemInfo("Environment", "QA");
			extent.assignProject("Test Project");
		}
		return extent;
	}

	public static synchronized ExtentTest getTest() {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

		logger.info("Name of the current method: " + nameofCurrMethod);

		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

		logger.info("Name of Current method: " + nameofCurrMethod);

		extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	}

	public static synchronized ExtentTest startTest(String testName) {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

		logger.info("Name of current method: " + nameofCurrMethod);

		return startTest(testName, "");
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

		logger.info("Name of current method: " + nameofCurrMethod);

		ExtentTest test = extent.startTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
