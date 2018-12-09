package com.webtest.renwu;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class shenhe extends BaseTest{
	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://10.7.1.9:8686/xinhu/?m=index");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
		Thread.sleep(3000);
		webtest.click("xpath=.//*[@id='topheaderid']/table/tbody/tr/td[2]/div/span[4]");
		webtest.click("xpath=//*[@id=\"menu_list_workwwc\"]");
		Thread.sleep(3000);
		webtest.click("link=操作");
		webtest.click("xpath=/html/body/div[1]/div/ul/li[1]");
		Thread.sleep(3000);
		webtest.enterFrame("openinputiframe");
		webtest.click("xpath=/html/body/div[1]/div/div[6]/div[2]/div[2]/form/table/tbody/tr[4]/td[2]/div/label[1]/input");
		webtest.click("xpath=//*[@id=\"check_btn\"]");
		webtest.leaveFrame();
		Assert.assertTrue(webtest.isTextPresent("已完成"));
	}
}
