package com.webtest.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Admin_Login extends BaseTest{
	/**
	 * @author wangdonghui
	 */
	@Test
	public void testLogin() {
		//打开页面
		webtest.open("http://localhost:8032/MyMovie/admin.php/Login/index.html#_blank");
		//文本框输入
		webtest.type("name=username", "admin");
		webtest.type("name=password", "admin");
		webtest.click("xpath=//input[@type='submit']");
		Assert.assertTrue(webtest.isTextPresent("退出"));
	}
}
