package com.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;

/**
 * This Class is used to get the number of rows and columns, get the values from
 * excel and property file
 * 
 * @author AKaivalliam
 * @version 1.0
 *
 */

public class ReadDataSheet {
	public WebDriver dr;
	public HSSFWorkbook wb;
	public HSSFSheet ws;
	public int rowCount;
	public String className;
	public String sheetName;
	public String colName;
	public String value;
	List<TestParameter> list;

	/**
	 * This method is used to get a particular row number of a testcase
	 * 
	 * @param testCaseName
	 * @param colHeader
	 * @return rownumber
	 * @throws MPException
	 */
	public int getRownumber(String testCaseName, String colHeader) throws MPException {
		int rownumber = 0;
		rowCount = ws.getLastRowNum();

		for (int j = 1; j <= rowCount; j++) {
			HSSFRow row = ws.getRow(j);
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
				rownumber = j;
				break;
			}
		}
		if (rownumber == 0) {
			throw new MPException("Class name is not found in the DataSheet");
		}

		getColumnNumber(colHeader);
		return rownumber;
	}

	/**
	 * This method is used to get a particular column number of a test case
	 * 
	 * @param columnHeader
	 * @return columnNumber
	 * @throws MPException
	 */
	public int getColumnNumber(String columnHeader) throws MPException {
		HSSFRow row = ws.getRow(0);
		int columnNumber = 0;
		int isValid = 0;
		for (int j = ws.getFirstRowNum(); j < row.getPhysicalNumberOfCells(); j++) {
			if (row.getCell(j).toString().equalsIgnoreCase(columnHeader)) {
				columnNumber = j;
				isValid = 1;
				break;
			}

		}
		if (isValid == 0) {
			throw new MPException("Enter proper column in DataSheet");
		}
		return columnNumber;
	}

	/**
	 * This method is used to get the string value from the excel sheet -
	 * DataSheet.xls
	 * 
	 * @param SheetName
	 * @param className
	 * @param columnHeader
	 * @return
	 * @throws MPException
	 */
	public String getValue(String SheetName, String className, String columnHeader) throws MPException {

		try {
			FileInputStream file = new FileInputStream(new File("./DataSheet.xls"));

			wb = new HSSFWorkbook(file);

			ws = wb.getSheet(SheetName);
			int rownumber = getRownumber(className, columnHeader);
			int columnNumber = getColumnNumber(columnHeader);
			HSSFCell cell = ws.getRow(rownumber).getCell(columnNumber);
			if (cell != null) {
				value = cell.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	/**
	 * This method is used to get the string value from the data.properties file
	 * 
	 * @param key
	 *            This is the variable that holds the value
	 * @return value
	 * @throws IOException
	 */
	public String getAppProperties(String key) throws IOException {
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

}
