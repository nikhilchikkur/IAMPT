package com.testRunner;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.TestNG;

import com.Utilities.TestParameter;

/**
 * The TestRunner class initiates the execution process.
 *
 * @author AKaivalliam
 * @version 1.0
 */
public class TestRunner {
	static List<TestParameter> list;

	/**
	 * This method is used to trigger the execution.
	 * 
	 * @return Nothing.
	 */
	public static void testRunner() {

		try {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

			System.out.println("Name of current method: " + nameofCurrMethod);

			String log4jConfPath = "./log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
			TestNG testNG = new TestNG();

			testNG.setTestClasses(new Class[] { RunCukesTest.class });
			testNG.run();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to get a list of all features to be executed from the
	 * TestRunnerBDD.xls file.
	 * 
	 * @return List<String> This returns list of all features to be executed.
	 * @exception Exception
	 *                On input error.
	 */
	public static List<String> featureList() throws Exception {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

		System.out.println("Name of current method: " + nameofCurrMethod);

		System.out.println("Done");
		List<String> featuresList = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("./TestRunnerBDD.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheet("Features");

		list = new ArrayList<TestParameter>();
		String userDir = System.getProperty("user.dir");

		userDir = userDir.concat("//src//test//java//com//features//");
		for (int count = 1; count <= sheet.getLastRowNum(); count++) {
			HSSFRow row = sheet.getRow(count);
			if (row.getCell(1).toString().equalsIgnoreCase("Yes")) {
				featuresList.add(userDir + row.getCell(0).toString());
			}
		}
		System.out.println("List of feature files are : " + featuresList.toString());
		return featuresList;
	}

	/**
	 * This method is used to get a list of all tags to be executed from the
	 * TestRunnerBDD.xls file.
	 * 
	 * @return List<String> This returns list of all tags to be executed.
	 * @exception Exception
	 *                On input error.
	 */
	public static String tagsList() throws Exception {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

		System.out.println("Name of current method: " + nameofCurrMethod);

		String featuresList = "";
		FileInputStream fis = new FileInputStream("TestRunnerBDD.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheet("Tags");

		list = new ArrayList<TestParameter>();
		for (int count = 1; count <= sheet.getLastRowNum(); count++) {
			HSSFRow row = sheet.getRow(count);
			if (row.getCell(1).toString().equalsIgnoreCase("Yes")) {

				featuresList = featuresList + (row.getCell(0).toString()) + ",";
			}

		}

		return featuresList;
	}

}
