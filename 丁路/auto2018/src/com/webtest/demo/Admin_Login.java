package com.webtest.demo;

import org.testng.Assert;
import org.testng.annotations.*;

import com.webtest.core.BaseTest;

public class Admin_Login extends BaseTest{
	@Test
	public void testLogin() throws InterruptedException
	{
		//打开页面
		webtest.open("http://localhost:8032/MyMovie/admin.php/Login/index.html");
		//文本框输入
		Thread.sleep(3000);
		webtest.type("name=username", "admin");
		webtest.type("name=password", "admin");
	//	webtest.click("xpath=//input[@type='submit']");
		Thread.sleep(3000);
		Assert.assertTrue(webtest.isTextPresent("退出"));
	}
}
