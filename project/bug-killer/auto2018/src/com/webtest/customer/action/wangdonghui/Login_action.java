package com.webtest.customer.action.wangdonghui;

import static org.testng.Assert.assertTrue;

import com.webtest.core.BaseTest;
import com.webtest.core.WebDriverEngine;

public class Login_action extends BaseTest{
	/**
	 * @author wangdonghui 
	 * 登录动作
	 */
	public WebDriverEngine webtest = null;
	
	public Login_action(WebDriverEngine webtest) {
		this.webtest = webtest;
	}
	
	public void Login_Action(String adminuser,String password,String checktext) {
		webtest.open("http://10.7.1.9:8686/xinhu");
		if(webtest.isTextPresent("管理员")) {
			webtest.click("xpath=//span[contains(.,'管理员    ')]");
			webtest.click("xpath=//li[contains(.,'退出')]");
			webtest.click("xpath=//button[contains(.,' 确定')]");
		}
		

		webtest.typeAndClear("name=adminuser",adminuser);
		webtest.typeAndClear("xpath=//input[@type='password']",password);
		webtest.click("xpath=//button[@onclick='loginsubmit()']");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(webtest.isTextPresent(checktext));
		
	}

}
