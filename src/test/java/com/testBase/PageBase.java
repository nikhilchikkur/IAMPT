package com.testBase;

import java.time.Duration;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.cucumber.listener.Reporter;
import com.testRunner.RunCukesTest;
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

/**
 * The PageBase class implements the methods related to the browser actions.
 *
 * @author nikhil
 * @version 1.0
 */
public class PageBase {

	public static int count = 0;

	public String tool = null, appType = null;

	public static String webBrowser = null;

	public String chromeDriverPath = null;

	public String fireFoxDriverPath = null;

	public String deviceName = null;

	public String appName = null;

	public static RemoteWebDriver remoteDriver;
	@SuppressWarnings("rawtypes")
	private static String toolName;
	public static String localgrid = null;
	public static DecimalFormat decim = new DecimalFormat("#.##");

	public PageBase(RemoteWebDriver driver, String tool) {
		this.remoteDriver = driver;
		PageFactory.initElements(remoteDriver, this);
		toolName = tool;
	}

	public PageBase(String toolName) {
		PageFactory.initElements(remoteDriver, this);
	}

	/**
	 * This method is used to launch the url.
	 * 
	 * @param url
	 *            This is the first parameter to launchWebSite method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void launchWebSite(String url) throws Exception {
		remoteDriver.get(url);
	}

	/**
	 * This method is used to navigate to previous page.
	 * 
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void navigateBack() throws Exception {
		remoteDriver.navigate().back();
		remoteDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void refresh() throws Exception {
		remoteDriver.navigate().refresh();
		remoteDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to launch the application.
	 * 
	 * @param browser
	 *            This is the first parameter to RemoteWebDriver method
	 * @return RemoteWebDriver This returns webdriver for execution.
	 * @exception Exception
	 *                On error.
	 */
	@SuppressWarnings("deprecation")
	public static RemoteWebDriver launchSite(String browser) throws Exception {
		webBrowser = browser;

		if (webBrowser.equalsIgnoreCase("chrome")) {
			String userDir = System.getProperty("user.dir");
			String chromeDriverPath = userDir.concat("/Executable_Drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("headless");
			options.addArguments("--test-type");
			options.addArguments("start-maximized");
			options.setExperimentalOption("useAutomationExtension", false);
			
			try {
				try {
					String session = remoteDriver.getSessionId().toString();
				} catch (Exception e) {
					try {
						remoteDriver.quit();
						System.out.println("Webdriver present to Quit");
					} catch (Exception e1) {
						System.out.println("Webdriver not present to Quit");
					}
					Thread.sleep(10000);
					remoteDriver = new ChromeDriver(options);
					remoteDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
					remoteDriver.manage().window().maximize();
					System.out.println("Session webdriver is not empty");
				}
				if(((RemoteWebDriver) remoteDriver).getSessionId().toString()==null) {
					remoteDriver.quit();
					Thread.sleep(10000);
					remoteDriver = new ChromeDriver(options);
					remoteDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
					remoteDriver.manage().window().fullscreen();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		} else if (webBrowser.equalsIgnoreCase("firefox")) {
			String userDir = System.getProperty("user.dir");
			String fireFoxDriverPath = userDir.concat("/Executable_Drivers/chromedriver.exe");
			System.out.println("Path to firefox driver is : " + fireFoxDriverPath);
			System.setProperty("webdriver.gecko.driver", fireFoxDriverPath);

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("marionette", true);
			remoteDriver = new FirefoxDriver(firefoxOptions);
			remoteDriver.manage().window().maximize();
			remoteDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		} else if (webBrowser.equalsIgnoreCase("safari")) {
			remoteDriver = new SafariDriver();
			remoteDriver.manage().window().maximize();
			remoteDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println(remoteDriver.getSessionId());
		}
		return remoteDriver;
	}

	/**
	 * This method is used to get the value from data.properties file.
	 * 
	 * @param key
	 *            This is the first parameter to getAppProperties method
	 * @return String This returns the corresponding value for the key.
	 * @exception IOException
	 *                On input error.
	 */
	public static String getAppProperties(String key) throws IOException {
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

	/**
	 * This method is used to launch the application with pageTitle verification.
	 * 
	 * @param url
	 *            This is the first parameter to enterUrl method
	 * @param pageTitle
	 *            This is the second parameter to enterUrl method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void enterUrl(String url, String pageTitle) throws Exception {
		try {
			remoteDriver.get(url);
			remoteDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Reporter.addStepLog("Entered the URL : " + url);
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addStepLog("Entered URL : " + url + " is incorrect");
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Wrong URL entered : " + url);
		}

	}

	/**
	 * This method is used to perform ENTER action over keyboard.
	 * 
	 * @param e
	 *            This is the first parameter to launchApp method
	 * @return Nothing.
	 * @exception InterruptedException
	 *                On interruption in thread.
	 */
	public void keyBoardEnter(WebElement e) throws InterruptedException {
		WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
		waitSelenium.until(ExpectedConditions.visibilityOf(e));
		waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
		e.sendKeys(Keys.ENTER);
	}

	/**
	 * This method is used to get the current page title.
	 * 
	 * @return title. This is the title of the page
	 * @exception Exception
	 * 
	 */
	public String getTitle() throws Exception {
		String title = remoteDriver.getTitle();
		return title;
	}

	/**
	 * This method is used to perform CLICK action in the application.
	 * 
	 * @param e
	 *            This is the first parameter to click method
	 * @param elementName
	 *            This is the second parameter to click method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public static void click(WebElement e, String elementName) throws Exception {
		double loadtime;
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 90, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
			fluentWait(e);
			e.click();
//			Thread.sleep(5000);
			loadtime = (Long) ((JavascriptExecutor) remoteDriver)
					.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;")
					/ 1000.0;
			Reporter.addStepLog("Click on element " + elementName + "" + " successful ---- ( "
					+ Double.parseDouble(decim.format(loadtime)) + " secs )");
			Reporter.addScreenCaptureFromPath(screenshot());

		}

		catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement " + elementName);

			throw new Exception();
		}

	}
	
	public void clear(WebElement e, String elementName) throws Exception {
		double loadtime;
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 90, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
			e.clear();
			Thread.sleep(5000);
			loadtime = (Long) ((JavascriptExecutor) remoteDriver)
					.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;")
					/ 1000.0;
			Reporter.addStepLog("Click on element " + elementName + "" + " successful ---- ( "
					+ Double.parseDouble(decim.format(loadtime)) + " secs )");
			Reporter.addScreenCaptureFromPath(screenshot());

		}

		catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement " + elementName);

			throw new Exception();
		}

	}

	/**
	 * This method is used to perform CLICK and wait for page load action in the
	 * application.
	 * 
	 * @param e
	 *            This is the first parameter to click method
	 * @param elementName
	 *            This is the second parameter to click method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void clickAndWait(WebElement e, String elementName, int milliseconds) throws Exception {
		double loadtime;
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 90, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			waitSelenium.until(ExpectedConditions.elementToBeClickable(e));

			e.click();
			loadtime = (Long) ((JavascriptExecutor) remoteDriver)
					.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;")
					/ 1000.0;
			Thread.sleep(milliseconds);
			Reporter.addStepLog("Click on element " + elementName + "" + " successful ---- ( "
					+ Double.parseDouble(decim.format(loadtime)) + " secs )");
			Reporter.addScreenCaptureFromPath(screenshot());

		}

		catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement " + elementName);

			throw new Exception();
		}

	}

	/**
	 * This method is used to perform CLICK button and accept the alert action in
	 * the application.
	 * 
	 * @param e
	 *            This is the first parameter to click method
	 * @param elementName
	 *            This is the second parameter to click method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void clickAndAlert(WebElement e, String elementName) throws Exception {
		double loadtime, loadtime1;
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 90, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			waitSelenium.until(ExpectedConditions.elementToBeClickable(e));

			e.click();
			Alert alert = remoteDriver.switchTo().alert();
			alert.accept();
			loadtime = (Long) ((JavascriptExecutor) remoteDriver)
					.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;")
					/ 1000.0;
			Thread.sleep(7000);
			Reporter.addStepLog("Click on element " + elementName + "" + " successful ---- ( "
					+ Double.parseDouble(decim.format(loadtime)) + " secs )");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (UnhandledAlertException e2) {
			try {
				Alert alert = remoteDriver.switchTo().alert();
				alert.accept();
				loadtime = (Long) ((JavascriptExecutor) remoteDriver)
						.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;")
						/ 1000.0;
				Thread.sleep(8000);
				Reporter.addStepLog("Click on element " + elementName + "" + " successful ---- ( "
						+ Double.parseDouble(decim.format(loadtime)) + " secs )");
				Reporter.addScreenCaptureFromPath(screenshot());
			} catch (NoAlertPresentException e3) {
				e3.printStackTrace();
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement " + elementName);
			throw new Exception();
		}
	}
	
	public void alertAbsent() throws Exception {
		try {
			Alert alert = remoteDriver.switchTo().alert();
		} catch (NoAlertPresentException alrt) {
			boolean flag = true;
			Assert.assertTrue(flag, "Alert is not present");
		}
	}

	public void scrollIntoView(WebElement element) throws Exception {
		((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}
	
	public void scrollTillPageBottom() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) remoteDriver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(500);
	}

	/**
	 * This method is used to perform SCROLL DOWN and CLICK action in the
	 * application.
	 * 
	 * @param e
	 *            This is the first parameter to click method
	 * @param elementName
	 *            This is the second parameter to click method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void scrollDownClick(WebElement e, String elementName) throws Exception {
		double loadtime;
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 90, 500);
			((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView(true);", e);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			waitSelenium.until(ExpectedConditions.elementToBeClickable(e));

			e.click();
			loadtime = (Long) ((JavascriptExecutor) remoteDriver)
					.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;")
					/ 1000.0;
			Thread.sleep(7000);
			Reporter.addStepLog("Click on element " + elementName + "" + " successful ---- ( "
					+ Double.parseDouble(decim.format(loadtime)) + " secs )");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement " + elementName);

			throw new Exception();
		}

	}

	/**
	 * This method is used to perform CLICK action using link text in the
	 * application.
	 * 
	 * @param partialLinkText
	 *            This is the first parameter to clickByLinkText method
	 * @param elementName
	 *            This is the second parameter to clickByLinkText method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void clickByLinkText(String partialLinkText, String elementName) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium
					.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByPartialLinkText(partialLinkText)));
			waitSelenium.until(ExpectedConditions
					.elementToBeClickable(remoteDriver.findElementByPartialLinkText(partialLinkText)));
			remoteDriver.findElementByPartialLinkText(partialLinkText).click();

			Reporter.addStepLog("Click on element " + elementName + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + elementName);
			throw new Exception();
		}

	}

	/**
	 * This method is used to perform CLICK action using css in the application.
	 * 
	 * @param e
	 *            This is the first parameter to clickByCSS method
	 * @param text
	 *            This is the second parameter to clickByCSS method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void clickByCSS(String e, String text) throws Exception {
		try {
			remoteDriver.findElementByCssSelector(e).click();

			Reporter.addStepLog("Click on element " + e + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + e);
			throw new Exception();
		}

	}

	/**
	 * This method is used to select an option in the dropdown using the text
	 * 
	 * @param e
	 *            This is the dropdown WebElement
	 * @param text
	 *            This is the option to be selected
	 * @param elementName
	 *            This is the name of the dropdown field
	 * @throws Exception
	 */
	public void selectOPtionByVisibleText(WebElement e, String text, String elementName) throws Exception {
//		Thread.sleep(5000);
		fluentWait(e);
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 80, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));

			e.click();
			Select sl = new Select(e);
			sl.selectByVisibleText(text);

			Reporter.addStepLog("Click on element " + e + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + e);
			throw new Exception();
		}

	}
	
	public void selectOPtionByValue(WebElement e, String text, String elementName) throws Exception {
//		Thread.sleep(5000);
		fluentWait(e);
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 80, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));

			e.click();
			Select sl = new Select(e);
			sl.selectByValue(text);

			Reporter.addStepLog("Click on element " + e + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + e);
			throw new Exception();
		}

	}

	/**
	 * This method is used to perform CLICK action using xpath in the application.
	 * 
	 * @param xpath
	 *            This is the first parameter to click method
	 * @param elementName
	 *            This is the second parameter to click method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void click(String xpath, String elementName) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			remoteDriver.findElementByXPath(xpath).click();

			Reporter.addStepLog("Click on element " + elementName + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + elementName);
			throw new Exception();
		}
	}

	/**
	 * This method is used to perform CLICK action using id in the application.
	 * 
	 * @param id
	 *            This is the first parameter to click method
	 * @param elementName
	 *            This is the second parameter to click method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void clickbyid(String id, String elementName) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 100, 1000);
			waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			remoteDriver.findElementById(id).click();
			Reporter.addStepLog("Click on element " + elementName + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + elementName);
			throw new Exception();
		}
	}

	/**
	 * This method is used to perform CLICK action using className in the
	 * application.
	 * 
	 * @param className
	 *            This is the first parameter to clickbyClassName method
	 * @param elementName
	 *            This is the second parameter to clickbyClassName method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void clickbyClassName(String className, String elementName) throws Exception {
		try {
			remoteDriver.findElementByClassName(className).click();
			Reporter.addStepLog("Click on element " + elementName + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + elementName);
			throw new Exception();
		}
	}

	/**
	 * This method is used to perform CLICK action using element name in the
	 * application.
	 * 
	 * @param name
	 *            This is the first parameter to clickByElementName method
	 * @param elementName
	 *            This is the second parameter to clickByElementName method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void clickByElementName(String name, String elementName) throws Exception {

		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.elementToBeClickable(By.name(name)));
			remoteDriver.findElementByName(name).click();
			Reporter.addStepLog("Click on element " + elementName + "" + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement" + elementName);
			throw new Exception();
		}
	}

	/**
	 * This method is used to perform DRAG AND DROP action in the application.
	 * 
	 * @param e1
	 *            This is the first parameter to dragAndDrop method
	 * @param e2
	 *            This is the second parameter to dragAndDrop method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */

	public void dragAndDrop(WebElement e1, WebElement e2) throws Exception {

		try {
			Actions action1 = new Actions(remoteDriver);
			action1.dragAndDrop(e1, e2).perform();

		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on clicking webelement");
			throw new Exception();
		}
	}

	/**
	 * This method is used to get text for an element in the application.
	 * 
	 * @param e
	 *            This is the first parameter to getText method
	 * @param elementName
	 *            This is the second parameter to getText method
	 * @return String This returns the element's text value.
	 * @exception Exception
	 *                On error.
	 */
	public String getText(WebElement e, String elementName) throws Exception {
		String text = "";
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			text = e.getText();
			return text;
		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on Get Text from webelement" + elementName);
			throw new Exception();
		}
	}

	/**
	 * This method is used to get text for an element using xpath in the
	 * application.
	 * 
	 * @param elePath
	 *            This is the first parameter to getText method
	 * @param elementName
	 *            This is the second parameter to getText method
	 * @return String This returns the element's text value.
	 * @exception Exception
	 *                On error.
	 */
	public String getText(String elePath, String elementName) throws Exception {
		String text = "";
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElement(By.xpath(elePath))));
			text = remoteDriver.findElement(By.xpath(elePath)).getText();
			Reporter.addStepLog("Get text from " + elementName + " successful");
			Reporter.addScreenCaptureFromPath(screenshot());
			return text;
		} catch (Exception exc) {
			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on Get Text from webelement" + elementName);
			throw new Exception();
		}

	}

	/**
	 * This method is used to switch to next window.
	 * 
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void switchToWindowTitle() throws Exception {

		try {
			for (String winHandle : remoteDriver.getWindowHandles()) {
				remoteDriver.switchTo().window(winHandle);
			}
		} catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * This method states that the assertion is true
	 * 
	 * @param message
	 *            This is the success message
	 * @throws Exception
	 */
	public void assertTrue(String message) throws Exception {
		Reporter.addScreenCaptureFromPath(screenshot());
		Assert.assertTrue(true, message);

	}

	/**
	 * This method states that the assertion is false
	 * 
	 * @param message
	 *            This is the message stating the failure
	 * @throws Exception
	 */
	public void assertFalse(String message) throws Exception {
		Reporter.addStepLog(message);
		Reporter.addScreenCaptureFromPath(screenshot());
		Assert.assertFalse(false, message);
	}

	/**
	 * This method is used to perform assertion by using a condition
	 * 
	 * @param bool
	 *            This is the condition to be verified
	 * @param message
	 *            This is the success message
	 * @throws Exception
	 */
	public void assertTrue(boolean bool, String message) throws Exception {
		Reporter.addStepLog(message);
		Reporter.addScreenCaptureFromPath(screenshot());
		Assert.assertTrue(bool, message);
	}

	/**
	 * This method is used to enter text for an element in the application.
	 * 
	 * @param element
	 *            This is the first parameter to enterText method
	 * @param cred
	 *            This is the second parameter to enterText method
	 * @param elementName
	 *            This is the third parameter to enterText method
	 * @return Nothing.
	 * @exception Exception
	 *                On error.
	 */
	public void enterText(WebElement element, String cred, String elementName) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(cred);
			Reporter.addStepLog("Enter text in " + elementName + " is successful");
			Reporter.addScreenCaptureFromPath(screenshot());
		} catch (Exception exc) {

			exc.printStackTrace();
			Reporter.addScreenCaptureFromPath(screenshot());
			Assert.assertTrue(false, "Exception on Get Text from webelement" + elementName);
			throw new Exception();

		}

	}
	
	public WebElement presence(By xpath) {
		WebElement element = (new WebDriverWait(remoteDriver,5)).until(ExpectedConditions.presenceOfElementLocated(xpath));
		return element;
	}

	/**
	 * This method is used to check if element is displayed in the application.
	 * 
	 * @param e
	 *            This is the first parameter to elementIsDisplayed method
	 * @param ElementName
	 *            This is the second parameter to elementIsDisplayed method
	 * @return boolean This returns if element is displayed or not.
	 * @exception Exception
	 *                On error.
	 */
	public boolean elementIsDisplayed(WebElement e, String ElementName) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 30, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
		} catch (Exception exc) {

		}
		try {
			if (e.isDisplayed()) {
				return true;
			} else
				return false;
		} catch (Exception exc) {
			return false;
		}

	}

	/**
	 * This method is used to wait until an element is clickable
	 * 
	 * @param e
	 * @param ElementName
	 * @return
	 * @throws Exception
	 */
	public boolean elementIsClickable(WebElement e, String ElementName) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 30, 500);
			waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
		} catch (Exception exc) {

		}
		try {
			if (e.isDisplayed()) {
				return true;
			} else
				return false;
		} catch (Exception exc) {
			return false;
		}
	}

	/**
	 * This method is used to wait until an element disappears from the webpage
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void waitUntilLoadingIsOver(WebElement e) throws Exception {
		WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
		waitSelenium.until(ExpectedConditions.invisibilityOf(e));
	}

	/**
	 * This method is used to check if element is displayed without wait in the
	 * application.
	 * 
	 * @param e
	 *            This is the first parameter to elementIsDisplayedNoWait method
	 * @param css
	 *            This is the second parameter to elementIsDisplayedNoWait method
	 * @return boolean This returns if element is displayed or not.
	 * @exception Exception
	 *                On error.
	 */
	public boolean elementIsDisplayedNoWait(WebElement e, String css) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 5, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e.findElement(By.cssSelector(css))));
		} catch (Exception exc) {

		}
		try {
			if (e.findElement(By.cssSelector(css)).isDisplayed()) {
				return true;
			} else
				return false;
		} catch (Exception exc) {
			return false;
		}

	}

	/**
	 * This method is used to verify if the WebElement is visible
	 * 
	 * @param e
	 *            This is the WebElement
	 * @return boolean data
	 * @throws Exception
	 */
	public boolean elementIsEnabled(WebElement e) throws Exception {
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
		} catch (Exception exc) {

		}
		try {
			if (e.isEnabled()) {
				return true;
			} else
				return false;
		} catch (Exception exc) {
			return false;
		}

	}

	/**
	 * This method is used to verify if the WebElement is visible using xpath
	 * 
	 * @param xpath
	 *            This is the xpath (locator) of the WebElement
	 * @return boolean data
	 * @throws Exception
	 */
	public boolean elementIsDisplayed(String xpath) throws Exception {

		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByXPath(xpath)));

		} catch (Exception exc) {
			return false;
		}
		return true;

	}

	/**
	 * This method is used to verify if the WebElement is visible using name
	 * 
	 * @param name
	 *            This is the name (locator) of the WebElement
	 * @return boolean data
	 * @throws Exception
	 */
	public boolean elementIsDisplayedByName(String name) throws Exception {

		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByName(name)));

		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * This method is used to click on a WebElement using Javascript executor
	 * 
	 * @param locator
	 *            This is the xpath (locator) of the WebElement
	 */
	public void clickByJS(String locator) {
		WebElement element = remoteDriver.findElement(By.xpath(locator));
		JavascriptExecutor executor = (JavascriptExecutor) remoteDriver;
		executor.executeScript("arguments[0].click();", element);

	}

	/**
	 * This method is used to click on a WebElement using Javascript executor
	 * 
	 * @param element
	 *            This is the WebElement
	 */
	public void javaScriptClick(WebElement element) {
		((JavascriptExecutor) remoteDriver).executeScript("arguments[0].click();", element);
	}

	/**
	 * This method is used to take a screenshot.
	 * 
	 * @return String This returns image path of screenshot taken.
	 * @exception IOException
	 *                On input error.
	 * @exception InterruptedException
	 *                On interruption in thread.
	 */
	public static String screenshot() throws IOException, InterruptedException {
		String imgPath = null;
		Thread.sleep(2000);
		count = count + 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_MM_SS");
		Date date = new Date();
		String timeStamp = dateFormat.format(date);
		File scrFileSelenium = ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
		imgPath = RunCukesTest.resultFolder + "/screenshots" + "/" + timeStamp + ".png";
		FileUtils.copyFile(scrFileSelenium, new File(imgPath));
		return new File(imgPath).getAbsolutePath();
	}

	/**
	 * This method is used to press keyboard keys
	 * 
	 * @param e
	 *            Wait until the WebElement e is visible
	 * @param key
	 *            Keyboard key to be pressed
	 */
	public void keyboardActions(WebElement e, Keys key) {

		WebDriverWait wait = new WebDriverWait(remoteDriver, 60, 500);
		wait.until(ExpectedConditions.visibilityOf(e));
		e.sendKeys(key);

	}

	/**
	 * This method is used to switch to a specific frame in the application using
	 * the iframe's name
	 * 
	 * @param frameName
	 *            This is the iframe's name
	 * @return Nothing.
	 * @exception Exception
	 */
	public void switchToFrame(String frameName) throws Exception {

		try {
			WebDriverWait wait = new WebDriverWait(remoteDriver, 30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * This method is used to switch to a specific frame in the application using
	 * the iframe's WebElement
	 * 
	 * @param frameName
	 *            Tthis is the iframe's WebElement
	 * @throws Exception
	 */
	public void switchToFrame(WebElement frameName) throws Exception {

		try {
			WebDriverWait wait = new WebDriverWait(remoteDriver, 30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * This method is used to switch to a parent tab in the application.
	 * 
	 * @return Nothing.
	 */
	public void switchToParentTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(remoteDriver.getWindowHandles());
		remoteDriver.switchTo().window(tabs2.get(1));
		remoteDriver.close();
		remoteDriver.switchTo().window(tabs2.get(0));
	}
	
	public static void switchToNewTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(remoteDriver.getWindowHandles());
		remoteDriver.switchTo().window(tabs2.get(1));
	}
	
	public int getNodesFromUI(WebElement element) {
		List<WebElement> nodes = remoteDriver.findElements(By.cssSelector("#lpn_list_area > ul > li"));
		return nodes.size();

	}

	/**
	 * This method is used to move to a specific element in the application.
	 * 
	 * @param element
	 *            This is the WebElement where the control has to move
	 * @param elementName
	 *            This is the name of the WebElement
	 * @return Nothing.
	 * @exception Exception
	 */
	public void moveToElement(WebElement element, String elementName) throws Exception {
		Actions actions;
		try {
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(element));
			actions = new Actions(remoteDriver);
			actions.moveToElement(element).build().perform();

		} catch (Exception exc) {
			Assert.assertTrue(false, "Move Element failed");
		}
	}

	/**
	 * This method is used to double click a WebElement in the webpage
	 * 
	 * @param target
	 *            This is the target webelement to double click
	 * @throws Exception
	 */
	public void doubleClick(WebElement target) throws Exception {

		((JavascriptExecutor) remoteDriver).executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", target);
	}

	/**
	 * This method is used to return a WebElement from the webpage
	 * 
	 * @param value
	 * @return element This is the WebElement to be returned
	 * @throws Exception
	 */
	public WebElement getElement(String value) throws Exception {
		WebElement element = remoteDriver.findElement(By.xpath(value));
		return element;

	}

	/**
	 * This method is used to locate the WebElement from a webpage which is below
	 * another WebElement
	 * 
	 * @param tagName
	 * @param elmnt
	 * @return
	 * @throws Exception
	 */
	public WebElement getBelowElement(String tagName, WebElement elmnt, String fieldName) throws Exception {
		WebElement element = remoteDriver.findElement(withTagName(tagName).below(elmnt));
		Reporter.addStepLog("Element " + fieldName + "" + " located successfully");
		return element;
	}

	/**
	 * This method is used to locate the WebElement from a webpage which is above
	 * another WebElement
	 * 
	 * @param tagName
	 * @param elmnt
	 * @return
	 * @throws Exception
	 */
	public WebElement getAboveElement(String tagName, WebElement elmnt, String fieldName) throws Exception {
		WebElement element = remoteDriver.findElement(withTagName(tagName).above(elmnt));
		Reporter.addStepLog("Element " + fieldName + "" + " located successfully");
		return element;
	}

	/**
	 * This method is used to locate the WebElement from a webpage which is to the
	 * right of another WebElement
	 * 
	 * @param tagName
	 * @param elmnt
	 * @return
	 * @throws Exception
	 */
	public WebElement getRightElement(String tagName, WebElement elmnt, String fieldName) throws Exception {
		WebElement element = remoteDriver.findElement(withTagName(tagName).toRightOf(elmnt));
		Reporter.addStepLog("Element " + fieldName + "" + " located successfully");
		return element;
	}

	/**
	 * This method is used to locate the WebElement from a webpage which is to the
	 * left of another WebElement
	 * 
	 * @param tagName
	 * @param elmnt
	 * @return
	 * @throws Exception
	 */
	public WebElement getLeftElement(String tagName, WebElement elmnt, String fieldName) throws Exception {
		WebElement element = remoteDriver.findElement(withTagName(tagName).toLeftOf(elmnt));
		Reporter.addStepLog("Element " + fieldName + "" + " located successfully");
		return element;
	}

	/**
	 * This method is used to locate the WebElement from a webpage which is near to
	 * another WebElement
	 * 
	 * @param tagName
	 * @param elmnt
	 * @return
	 * @throws Exception
	 */
	public WebElement getNearElement(String tagName, WebElement elmnt, String fieldName) throws Exception {
		WebElement element = remoteDriver.findElement(withTagName(tagName).near(elmnt));
		Reporter.addStepLog("Element " + fieldName + "" + " located successfully");
		return element;
	}

	public void takeScreenshot() throws IOException, InterruptedException {
		Reporter.addStepLog("Elements are verifed");
		Reporter.addScreenCaptureFromPath(screenshot());
	}

	public String removeCharacter(String s, String removeChar) {
		StringTokenizer st = new StringTokenizer(s, removeChar, false);
		String t = "";
		while (st.hasMoreElements())
			t += st.nextElement();
		return t;
	}

	/**
	 * This method is used to wait for an element in the webpage, polling every 5 seconds until the element is present and till 8 minutes/500 seconds 
	 * @param element
	 * 			element is the webelement to wait for visibility in the webpage
	 * @throws Exception
	 */
	public static void fluentWait(WebElement element) throws Exception {

		Wait<RemoteWebDriver> wait = new FluentWait<>(remoteDriver)
				.withTimeout(Duration.ofSeconds(500))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return element.isDisplayed();
			}
		});
	}
	
	public String pasteFromClipboard() throws Exception{
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		System.out.println("String from Clipboard:" + result);
		return result;
	}
	
//	public void imageClick() throws Exception{
//		Screen screen = new Screen();
//		Pattern image = new Pattern("C:\\Users\\AKaivalliam\\Documents\\Cucumber-BDD\\TestData\\searchBtn.png");
//		screen.find(image).highlight(2);
//		screen.find(image).click();
//	}
	
	

}
