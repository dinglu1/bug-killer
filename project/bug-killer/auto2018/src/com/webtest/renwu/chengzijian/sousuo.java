package com.webtest.renwu.chengzijian;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class sousuo extends  BaseTest{
	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://10.7.1.9:8686/xinhu/?m=index");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
		Thread.sleep(3000);
		webtest.click("xpath=.//*[@id='topheaderid']/table/tbody/tr/td[2]/div/span[4]");
		webtest.click("xpath=.//*[@id='menu_list_num81']");
		Thread.sleep(3000);
		webtest.click("xpath=//button[@style='padding-left:8px;padding-right:8px']" );
		webtest.click("xpath=//li[@temp='0']" );
		Thread.sleep(3000);
		webtest.type("xpath=/html/body/div[1]/div[2]/div/form/table/tbody/tr[1]/td[2]/input", "信呼");
		webtest.click("xpath=//*[@id=\"searchhigh_btn0\"]");

		
		
		webtest.click("xpath=//button[@style='padding-left:8px;padding-right:8px']" );
		webtest.click("xpath=/html/body/div[1]/div/ul/li[2]");
		webtest.click("xpath=//*[@id=\"searchhigh_btn0\"]");
		Assert.assertTrue(webtest.isTextPresent("保存中"));
		
		webtest.click("xpath=//button[@style='padding-left:8px;padding-right:8px']" );
		webtest.click("xpath=/html/body/div[1]/div/ul/li[3]");
		
}
}
