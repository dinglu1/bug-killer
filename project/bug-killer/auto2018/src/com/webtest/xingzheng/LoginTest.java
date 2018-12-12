package com.webtest.xingzheng;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.core.WebDriverEngine;
//登录+跳转至行政
public class LoginTest extends BaseTest{

	private WebDriverEngine webtest;
	public LoginTest(WebDriverEngine webtest)
	{
		this.webtest=webtest;
	}
	@Test
	public void Login( ) throws InterruptedException
	{
		webtest.open("http://10.7.1.9:8686/xinhu");
		webtest.type("xpath=//input[@name='adminuser']", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("xpath=//button[@class='webbtn']");
		Thread.sleep(3000);
		webtest.click("xpath=//span[contains(@pmenuid,'140')]");
		Thread.sleep(3000);
		
	}
}