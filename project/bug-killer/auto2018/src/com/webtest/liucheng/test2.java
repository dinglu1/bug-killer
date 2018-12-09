package com.webtest.liucheng;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
/**
 *
 * @author DELL
 *
 */
public class test2 extends BaseTest{
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
	public void test1() throws InterruptedException
	{
		webtest.click("id=menu_list_num42");  
		webtest.click("xpath=.//*[@id='content_num42']/div[1]/table/tbody/tr/td[1]/div[1]/a[1]");
		webtest.enterFrame("openinputiframe");
		webtest.type("name=title", "123");
		webtest.click("name=typename");
		webtest.click("xpath=//option[@value='ͨ通知公告']");
		webtest.click("xpath=//input[@class='webbtn']");
		webtest.leaveFrame();
	}
	@Test
	public void test2() throws InterruptedException
	{
		webtest.click("xpath=.//*[@id='content_num42']/div[1]/table/tbody/tr/td[1]/div[1]/a[1]");
		webtest.enterFrame("openinputiframe");
		webtest.type("name=title", "aa123");
		webtest.click("name=typename");
		webtest.click("xpath=//option[@value='ͨ通知公告']");
		webtest.click("xpath=//input[@class='webbtn']");
		webtest.leaveFrame();
	}
	@Test
	public void test3() throws InterruptedException
	{
		webtest.click("xpath=.//*[@id='content_num42']/div[1]/table/tbody/tr/td[1]/div[1]/a[1]");
		webtest.enterFrame("openinputiframe");
		webtest.type("name=title", "***123");
		webtest.click("name=typename");
		webtest.click("xpath=//option[@value='ͨ通知公告']");
		webtest.click("xpath=//input[@class='webbtn']");
		webtest.leaveFrame();
	}
	@Test
	public void test4() throws InterruptedException
	{
		webtest.click("xpath=.//*[@id='content_num42']/div[1]/table/tbody/tr/td[1]/div[1]/a[1]");
		webtest.enterFrame("openinputiframe");
		webtest.click("name=typename");
		webtest.click("xpath=//option[@value='ͨ通知公告']");
		webtest.click("xpath=//input[@class='webbtn']");
		webtest.leaveFrame();
	}
	@Test
	public void test5() throws InterruptedException
	{  
		webtest.click("xpath=.//*[@id='content_num42']/div[1]/table/tbody/tr/td[1]/div[1]/a[1]");
		webtest.enterFrame("openinputiframe");
		webtest.type("name=title", "123");
		webtest.click("name=typename");
		webtest.click("xpath=//option[contains(.,'-请选择-')]");
		webtest.click("xpath=//input[@class='webbtn']");
		webtest.leaveFrame();
	}
	@Test
	public void test6() throws InterruptedException
	{	
		webtest.click("id=winiframe_spancancel");
		webtest.click("xpath=.//*[@id='content_num42']/div[1]/table/tbody/tr/td[1]/div[1]/a[1]");
		webtest.enterFrame("openinputiframe");
		webtest.click("xpath=//input[@class='webbtn']");
		webtest.leaveFrame();
	}
}
