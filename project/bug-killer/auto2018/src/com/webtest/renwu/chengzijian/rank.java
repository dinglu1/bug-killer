package com.webtest.renwu.chengzijian;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class rank  extends BaseTest{


	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://10.7.1.9:8686/xinhu/?m=index");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");

		Thread.sleep(3000);
		webtest.click("xpath=.//*[@id='topheaderid']/table/tbody/tr/td[2]/div/span[4]");
		Thread.sleep(3000);
		webtest.click("xpath=.//*[@id='menu_list_num81']");
		Thread.sleep(3000);
		webtest.click("xpath=//i[@class='icon-sort cursor']" );
		Thread.sleep(3000);
		webtest.click("xpath=//i[@class='icon-sort-down cursor']" );
		Assert.assertTrue(webtest.isTextPresent(""));
	}
}
