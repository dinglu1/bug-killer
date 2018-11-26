package com.webtes.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Admin_Login extends BaseTest{
	@Test(description="��¼")
	public void loginTest() {
		webtest.open("http://localhost:8032/MyMovie/admin.php/Login/index.html");
		webtest.type("name=username", "admin");
		webtest.type("name=password", "admin");
		webtest.click("xpath=//input[@type='submit']");
		Reporter.log("lalala");
		assertTrue(webtest.isTextPresent("�˳�"));
		
	}
	@Test(dependsOnMethods="loginTest",description="���ӰƬ")
	public void addMovie() {
		webtest.click("xpath=//h2[contains(.,'FolderӰƬ����')]");
		webtest.click("xpath=//span[contains(.,'���ӰƬ��Ϣ')]");
		webtest.type("name=filmname", "ӰƬһ");
		webtest.type("name=petname", "ӰƬһ");
		webtest.type("name=director", "����һ");
		webtest.type("name=editor", "���һ");
		webtest.type("name=nation", "����һ");
		webtest.type("name=language", "����һ");
		webtest.type("name=picname", "D:\\upupw32\\htdocs\\MyMovie\\Uploads\\Movie\\mPhotos\\52a5fe661a69c.jpg");
		webtest.enterFrame("xhe0_iframe");
		webtest.click("tag=body");
		webtest.type("tag=body","��������");
		webtest.leaveFrame();
		webtest.click("xpath=//button[@type='submit']");
		
	}
	
}
