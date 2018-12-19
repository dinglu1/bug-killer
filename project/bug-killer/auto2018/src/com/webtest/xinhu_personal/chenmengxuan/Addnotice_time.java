package com.webtest.xinhu_personal.chenmengxuan;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;

public class Addnotice_time extends BaseTest{
	//个人办公-通知公告-新增(日期的测试)
	@Test
	public void login4() {
		webtest.open("http://10.7.1.9:8686/xinhu");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
	}
	@Test(dependsOnMethods="login4") 
	public void enterNotice() {
		webtest.click("xpath=//span[@class='spanactive']");
		webtest.click("xpath=//div[@id='menu_list_gong']");
//		webtest.click("xpath=//button[contains(.,' 新增')]");
	}
	@Test(dependsOnMethods="enterNotice",dataProvider="txt3",dataProviderClass = NSDataProvider.class)
	public void testTime(String time,String result) {
		webtest.click("xpath=//button[contains(.,' 新增')]");
		webtest.enterFrame("openinputiframe");
		webtest.typeAndClear("name=title", "通知公告");
		webtest.click("xpath=//select[@class='inputs']");
		webtest.click("xpath=//option[@value='通知公告']");
		webtest.typeAndClear("name=zuozhe","技术部");
		WebElement element = webtest.driver.findElement(By.name("indate"));
		JavascriptExecutor js = (JavascriptExecutor) webtest.driver;
		js.executeScript("arguments[0].removeAttribute('readonly')", element);
		webtest.typeAndClear("name=indate", time);
		webtest.click("id=AltS");
		webtest.leaveFrame();
		Assert.assertTrue(webtest.isTextPresent(result));
	}

}
