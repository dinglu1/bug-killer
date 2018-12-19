package com.webtest.liucheng.dinglu;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class test1 extends BaseTest{
	/** 
	 * @throws InterruptedException 
	 */
	Login_Action action;
	
	@BeforeClass
	public void testLogin() throws Exception  {
		action = new Login_Action(webtest);
		webtest.open("http://10.7.1.9:8686/xinhu");
		action.login("admin", "123456aa");
		Thread.sleep(2000);
		webtest.click("class =spanactive");
	}
	@Test
	public void testT() throws InterruptedException
	{
		webtest.click("id=indesearchmenu");
		webtest.type("id=confirm_input","ͨ通知公告");
		Thread.sleep(1000);
		webtest.click("id=confirm_btn1");
		
		webtest.click("id=indesearchmenu");
		webtest.type("id=confirm_input","ͨ通知公告");
		webtest.tapEnter();
		Thread.sleep(1000);
		webtest.click("id=confirm_btn1");
		
		webtest.click("id=indesearchmenu");
		webtest.type("id=confirm_input","1234");
		Thread.sleep(1000);
		webtest.click("id=confirm_btn1");
		
		webtest.click("id=indesearchmenu");
		webtest.click("id=confirm_btn1");
		
		webtest.click("id=indesearchmenu");
		webtest.type("id=confirm_input","aa1234");
		Thread.sleep(1000);
		webtest.click("id=confirm_btn1");
		
	}
}
