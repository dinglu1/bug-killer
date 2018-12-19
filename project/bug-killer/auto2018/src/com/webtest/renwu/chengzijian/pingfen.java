package com.webtest.renwu.chengzijian;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
//		webtest.click("xpath=");
public class pingfen extends BaseTest{
	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://10.7.1.9:8686/xinhu/?m=index");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
		Thread.sleep(3000);
		webtest.click("xpath=.//*[@id='topheaderid']/table/tbody/tr/td[2]/div/span[4]");
		webtest.click("xpath=//div[@temp='menu'][contains(@id,'num80')][contains(.,'任务督导')]");
		Thread.sleep(3000);
		webtest.click("link=操作");
		webtest.click("xpath=//li[@temp='0'][contains(.,'详情')]");
		Thread.sleep(3000);
		webtest.enterFrame("openinputiframe");
		webtest.click("xpath=//input[contains(@class,'inputs')]");
		webtest.type("xpath=//textarea[@class='inputs'][contains(@id,'explain')]","123");
		webtest.click("xpath=//a[contains(@onclick,'submittijiao()')]");
		Assert.assertTrue(webtest.isTextPresent("处理成功"));

	}
}
