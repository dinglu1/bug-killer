package com.webtest.liucheng.dinglu;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

/**
 *
 * @author DELL
 *
 */
public class test6 extends BaseTest{
	Login_Action action;
	
	@BeforeClass
	public void testLogin() throws Exception  {
		action = new Login_Action(webtest);
		webtest.open("http://10.7.1.9:8686/xinhu");
		action.login("admin", "123456aa");
		Thread.sleep(2000); 
	}
	@Test
	public void test()
	{
		webtest.click("xpath=//input[@class='btn btn-success']");
		webtest.click("xpath=//button[@class='btn btn-default']");
		webtest.click("xpath=//input[@class='btn btn-success']");
	}
}
