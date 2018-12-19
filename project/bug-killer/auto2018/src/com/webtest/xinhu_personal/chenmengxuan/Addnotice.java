package com.webtest.xinhu_personal.chenmengxuan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;

public class Addnotice extends BaseTest{
	//个人办公--通知公告--新增测试  空的项中时空格
	@Test
	public void login2() {
		webtest.open("http://10.7.1.9:8686/xinhu/");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
	}
	@Test(dependsOnMethods="login2") 
	public void enterNotice() {
		webtest.click("xpath=//span[@class='spanactive']");
		webtest.click("xpath=//div[@id='menu_list_gong']");
	}
	@Test(dependsOnMethods="enterNotice",dataProvider="excel1",dataProviderClass = NSDataProvider.class)
	public void addNotice(String title,String department,String result) {
		webtest.click("xpath=//button[@click='clickwin,0']");
		webtest.enterFrame("openinputiframe");
		webtest.typeAndClear("name=title", title);
		webtest.click("xpath=//select[@class='inputs']");
		webtest.click("xpath=//option[@value='通知公告']");
		webtest.typeAndClear("name=zuozhe", department);
		webtest.click("name=indate");
		webtest.click("xpath=//a[contains(.,'现在')]");
		webtest.click("id=AltS");
		webtest.leaveFrame();
		Assert.assertTrue(webtest.isTextPresent(result));	
	}


}
