package com.webtest.core;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.webtest.utils.Log;
import com.webtest.utils.ReadProperties;




/*
 * author:Dreamy
 * ³õÊ¼»¯ä¯ÀÀÆ÷
 */

public abstract class BaseTest {
	
	public WebDriverEngine webtest;
	private WebDriver driver;
	public String driverType;
	
	private WebDriver newWebDriver(String driverType) throws IOException {
		WebDriver driver=null;
		if(driverType.equalsIgnoreCase("firefox")) {
			String firefox_driver = ReadProperties.getPropertyValue("gecko_driver");
//			String firefox_path = ReadProperties.getPropertyValue("firefox_path");
			System.setProperty("webdriver.gecko.driver",firefox_driver);
//			System.setProperty("webdriver.firefox.bin", firefox_path);
			driver = new FirefoxDriver();
			Log.info("Using Firefox");
		}else if (driverType.equalsIgnoreCase("chrome")) {
			String chrome_path = ReadProperties.getPropertyValue("chrome_path");
			System.setProperty("webdriver.chrome.driver",chrome_path);
			driver = new ChromeDriver();
			Log.info("Using Chrome");
			
		}else{
			return null;
		}
		return driver;
	}
	
	@BeforeSuite
	public void doBeforeSuite() throws IOException {
		driverType=ReadProperties.getPropertyValue("driverType");
		driver = this.newWebDriver(driverType);
		driver.manage().window().maximize();
		Log.info(driverType); 
		webtest = new WebDriverEngine(driver);
	}
	
	@AfterSuite
	public void doAfterMethod() {
		if(this.driver != null) {
			this.driver.quit();
		}
		Log.info("Quiied Browser");
	}
	public WebDriver getDriver() {
		return driver;
	}

	public static String getCfgProperty(String string)  {
		try {
			return ReadProperties.getPropertyValue(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
