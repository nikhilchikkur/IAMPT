package com.Utilities;

/**
 * This Class has the getters and setters for various attributes
 * 
 * @author AKaivalliam
 * @version 1.0
 *
 */

public class TestParameter {
	
	private String toolName;
	private String browser;
	private String executionType;
	private String testClass;

	public String getTestClass() {
		return testClass;
	}

	public void setTestClass(String testClass1) {
		testClass = testClass1;
	}

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public void TestParameter1(String testClass1) {
		this.testClass = testClass1;
	}

}
