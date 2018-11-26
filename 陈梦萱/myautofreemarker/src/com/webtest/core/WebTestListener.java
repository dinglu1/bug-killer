package com.webtest.core;
/*
 * author:chenemengxuan
 * ¼àÌıÆ÷£¬ÓÃÀıÊ§°Ü£¬½ØÆÁ
 */

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.webtest.utils.Log;

public class WebTestListener extends TestListenerAdapter {
	@Override
	public void onTestFailure(ITestResult tr) {
		Log.error(tr.getInstance()+"-"+tr.getName()+"ÔËĞĞÊ§°Ü£¬ĞèÒª½ØÆÁ");
		BaseTest tb = (BaseTest)tr.getInstance();
		WebDriver driver = tb.getDriver();
		SeleniumScreenShot ss = new SeleniumScreenShot(driver);
		ss.screenShot();
	}
}
