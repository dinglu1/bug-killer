package com.webtest.liucheng.dinglu;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

/**
 *
 * @author DELL
 *
 */
public class test5 extends BaseTest{
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
	public void test() throws InterruptedException {
		webtest.click("id=menu_list_applymy");
		webtest.click("xpath=//a[@oi='0']");
		webtest.click("xpath=//li[@temp='5']");
		webtest.click("xpath=//button[@id='confirm_btn1']");
		
		webtest.type("xpath=//textarea[@class='input']","123");
		Thread.sleep(1000);
		webtest.click("xpath=//button[@id='confirm_btn1']");
	}
}
