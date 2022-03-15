package com.testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import com.Utilities.ReadDataSheet;
import com.aventstack.extentreports.gherkin.model.Scenario;

import cucumber.api.java.Before;

/**
 * The TestBase class implements the methods related to the driver objects.
 *
 * @author Nikhil
 * @version 1.0
 */
public class TestBase{

	public static long startTime;
	public static String startTimeUpdate;
	public static long endTime;
	public static long totalTime;
	public static String totalTimeTaken;
	public static String osType = System.getProperty("os.name");
	public static String toolName = "";
	public static String appType = "";
	public static String localGrid = "";
	public static String currentTest = "";
	ReadDataSheet data = new ReadDataSheet();
	protected Scenario scenario;

	/**
	 * This method is used to call the driver initiating method.
	 * 
	 * @param list
	 *            This is the first parameter to initDriver method
	 * @return String This returns tool name used for execution.
	 * @exception IOException
	 *                On input error.
	 */

	public String initDriver(List<String> list) throws IOException {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);

		try {
			toolName = initiateDriver(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toolName;
	}

	/**
	 * This method is used to close the driver.
	 * 
	 * @param toolName
	 *            This is the first parameter to closeDriver method
	 * @return Nothing.
	 */

	public void closeDriver() throws Exception {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);

		System.out.println("After Test");
		Thread.sleep(5000);
//		PageBase.remoteDriver.close();
		PageBase.remoteDriver.quit();
	}

	/**
	 * This method is used to initiate the driver for execution.
	 * 
	 * @param list
	 *            This is the first parameter to initiateDriver method
	 * @return String This returns tool name used for execution.
	 */

	@SuppressWarnings("null")
	public String initiateDriver(List<String> list) {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);

		try {
			toolName = "Selenium";
			
			String browser = list.get(0);
			System.out.println("The browser name is : " + browser);
			PageBase.remoteDriver = PageBase.launchSite(browser);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("toolname testbase-" + toolName);
		return toolName;

	}
	

	/**
	 * This method is used to get the value from data.properties file.
	 * 
	 * @param key
	 *            This is the first parameter to getPropertyValue method
	 * @return String This returns the corresponding value for the key.
	 * @exception IOException
	 *                On input error.
	 */

	public static String getPropertyValue(String key) throws IOException {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);

		String value = "";
		try {
			FileInputStream fileInputStream = new FileInputStream("datafile.properties");
			Properties property = new Properties();
			property.load(fileInputStream);
			value = property.getProperty(key);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	public static String getValue(String key, String path) throws IOException {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("Name of current method: " + nameofCurrMethod);

		String value = "";
		try {
			FileInputStream fileInputStream = new FileInputStream("path");
			Properties property = new Properties();
			property.load(fileInputStream);
			value = property.getProperty(key);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static int findRow(HSSFSheet sheet, String cellContent) {
	    for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
	                    return row.getRowNum();  
	                }
	            }
	        }
	    }               
	    return 0;
	}
	
//	public String scenarioName(Scenario scenario) {
//		System.out.println("Feature file name is : "+ scenario.getId().split(";")[0]);
//		String featureFileName = scenario.getId().split(";")[0]+".feature";
//		String newFeatureFileName = featureFileName.replace('-', '_');
//		System.out.println("New feature file name is : "+ newFeatureFileName);
//		return newFeatureFileName;
//	}

}
