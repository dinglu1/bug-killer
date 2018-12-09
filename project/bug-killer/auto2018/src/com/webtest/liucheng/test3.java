package com.webtest.liucheng;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

/**
 *
 * @author DELL
 *
 */
public class test3 extends BaseTest{
	Login_Action action;
	
	@BeforeClass
	public void testLogin() throws Exception  {
		action = new Login_Action(webtest);
		webtest.open("http://10.7.1.9:8686/xinhu");
		action.login("admin", "123456aa");
		Thread.sleep(2000); 
		webtest.click("xpath=//span[contains(@pmenuid,'40')]");
	}
	
	@Test
	public void test1()
	{
		webtest.click("id=menu_list_applymy");
		webtest.click("xpath=//a[@oi='0']");
		webtest.click("xpath=//li[@temp='0']");
		webtest.click("id=winiframe_spancancel");
	}
	@Test
	public void test2()
	{
		webtest.click("xpath=//a[@oi='0']");
		webtest.click("xpath=//li[@temp='1']");
		webtest.switchWidow(1);
		webtest.click("xpath=//a[@class='webbtn']");
		webtest.click("xpath=//li[@lx='5']");
		webtest.tapEnter();
		webtest.click("xpath=//a[@class='webbtn']");
		webtest.click("xpath=//li[@lx='3']");
	}
}
