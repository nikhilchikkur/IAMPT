package com.testRunner;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * The ExecuteTest class is used to trigger the execution of the suite.
 *
 * @author AKaivalliam
 * @version 1.0
 */
public class ExecuteTest extends TestRunner {
	
	Logger logger = Logger.getLogger(ExecuteTest.class);

	/**
	 * This is the main method to be executed.
	 * 
	 * @param args
	 *            Unused.
	 * @return Nothing
	 */

	@Test
	public void executeTest() {

		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		logger.info("Name of current method: " + nameofCurrMethod);

		testRunner();
	}

}
