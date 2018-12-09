package com.webtest.liucheng;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

/**
 *
 * @author DELL
 *
 */
public class test4 extends BaseTest{
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
		webtest.click("xpath=/lect[@class='form-control']");
		webtest.click("xpath=//option[@value='24']");
		webtest.click("xpath=/tton[@click='search']");
		
		webtest.click("xpath=/tton[@click='clickdt,1']");
		webtest.click("xpath=//td[@xu='7']");
		webtest.click("xpath=//a[contains(.,'确定')]");
		webtest.click("xpath=/tton[@click='search']");
		
		webtest.click("class=icon-remove");
		
		webtest.click("id=menu_list_applymy");
		webtest.type("xpath=//input[@placeholder='姓名/部门/单号']", "管理员");
		webtest.click("xpath=/tton[@click='search']");
		webtest.typeAndClear("xpath=//input[@placeholder='姓名/部门/单号']", "HC-20170906-0007");
		webtest.click("xpath=/tton[@click='search']");
		webtest.typeAndClear("xpath=//input[@placeholder='姓名/部门/单号']", "开发部");
		webtest.click("xpath=/tton[@click='search']");
	}

}
