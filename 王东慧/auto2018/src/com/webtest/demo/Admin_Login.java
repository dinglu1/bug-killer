package com.webtest.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;

public class Admin_Login extends BaseTest{
	/**
	 * @author wangdonghui
	 */
	@Test(dataProvider="txt1",dataProviderClass = NSDataProvider.class)
	public void testLogin(String name,String password) {
		//打开页面
		webtest.open("http://localhost:8032/MyMovie/admin.php/Login/index.html");
		//文本框输入
		webtest.type("name=username", name);
		webtest.type("name=password", password);
		webtest.click("xpath=//input[@type='submit']");
		Assert.assertTrue(webtest.isTextPresent("退出"));
	}
}
