package com.webtest.renwu;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class xinjian extends BaseTest{
	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://10.7.1.9:8686/xinhu/?m=index");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
		Thread.sleep(3000);
		webtest.click("xpath=.//*[@id='topheaderid']/table/tbody/tr/td[2]/div/span[4]");
		webtest.click("xpath=//*[@id=\"menu_list_num79\"]");
		webtest.click("xpath=//*[@id=\"menu_list_num69\"]");
		Thread.sleep(3000);
		webtest.click("xpath=//button[contains(@class,'btn btn-primary')]");
		Thread.sleep(3000);
		webtest.enterFrame("openinputiframe");
		webtest.click("xpath=//select[contains(@name,'type')]");
		webtest.click("xpath=//option[contains(@value,'一般项目')]");
		webtest.type("xpath=//input[contains(@name,'title')]","123");
		webtest.click("xpath=//a[contains(@id,'btnchange_runuser')]");
		webtest.click("xpath//td[contains(.,'开发部')]");
		webtest.click("xpath=//td[@deptxu='3_1']");
		webtest.click("xpath=//input[@value='1']");
		webtest.click("xpath=//input[@xname='管理员']");
		Assert.assertTrue(webtest.isTextPresent("信呼开发团队"));
		webtest.click("xpath=//input[@value='确定']");
		

		webtest.click("xpath=//a[@href='javascript:;'][contains(@id,'fuze')][contains(.,'选择')]");
		webtest.click("xpath//td[contains(.,'开发部')]");//kaifabu
		webtest.click("xpath=//td[@deptxu='3_1']");
		webtest.click("xpath=//input[@value='1']");
		webtest.click("xpath=//input[@xname='管理员']");
		Assert.assertTrue(webtest.isTextPresent("管理员"));
		webtest.click("xpath=//input[@value='确定']");

		
		
		
		

		webtest.click("xpath=//input[contains(@class,'webbtn')]");
	}
}
