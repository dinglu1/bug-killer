package com.webtes.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Admin_Login extends BaseTest{
	@Test(description="登录")
	public void loginTest() {
		webtest.open("http://localhost:8032/MyMovie/admin.php/Login/index.html");
		webtest.type("name=username", "admin");
		webtest.type("name=password", "admin");
		webtest.click("xpath=//input[@type='submit']");
		Reporter.log("lalala");
		assertTrue(webtest.isTextPresent("退出"));
		
	}
	@Test(dependsOnMethods="loginTest",description="添加影片")
	public void addMovie() {
		webtest.click("xpath=//h2[contains(.,'Folder影片管理')]");
		webtest.click("xpath=//span[contains(.,'添加影片信息')]");
		webtest.type("name=filmname", "影片一");
		webtest.type("name=petname", "影片一");
		webtest.type("name=director", "导演一");
		webtest.type("name=editor", "编剧一");
		webtest.type("name=nation", "国家一");
		webtest.type("name=language", "语言一");
		webtest.type("name=picname", "D:\\upupw32\\htdocs\\MyMovie\\Uploads\\Movie\\mPhotos\\52a5fe661a69c.jpg");
		webtest.enterFrame("xhe0_iframe");
		webtest.click("tag=body");
		webtest.type("tag=body","天气很冷");
		webtest.leaveFrame();
		webtest.click("xpath=//button[@type='submit']");
		
	}
	
}
