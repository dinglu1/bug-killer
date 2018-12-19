package com.webtest.xinhu_personal.chenmengxuan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;

public class Login extends BaseTest{
	@Test
	public void openBrower() {
		webtest.open("http://10.7.1.9:8686/xinhu/");
	}
	@Test(dependsOnMethods="openBrower",dataProvider="txt2",dataProviderClass=NSDataProvider.class)
	public void  login(String name,String password,String content) {
		webtest.typeAndClear("name=adminuser", name);
		webtest.typeAndClear("xpath=//input[@type='password']", password);
		webtest.click("name=button");
		Assert.assertTrue(webtest.isTextPresent(content));
	}
	@Test(dependsOnMethods="login")
	public void loginsuccess() {
		webtest.typeAndClear("name=adminuser", "admin");
		webtest.typeAndClear("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
		Assert.assertTrue(webtest.isTextPresent("管理员"));
	}
}
