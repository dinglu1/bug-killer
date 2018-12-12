package com.webtest.xingzheng;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class TestPart4_tushu extends BaseTest {
	@BeforeClass
	//进入车辆管理部分
	public void Enter() throws InterruptedException{
		LoginTest login = new LoginTest(webtest);
	   	  login.Login();
	   	  Thread.sleep(3000);
	 		  webtest.click("xpath=//div[@id='menu_list_num139']"); 
	 		  Thread.sleep(3000);    	  
	}
	@Test
	//图书管理部分-新增
	public void d1() throws InterruptedException{
		webtest.click("xpath=//div[@onclick='clickmenu(this,3,1)']");
		webtest.click("xpath=//button[contains(.,' 新增')]");
		webtest.driver.switchTo().frame("openinputiframe");
		webtest.click("xpath=//a[contains(.,'选择')]");//书名
		webtest.click("xpath=//input[@value='1']");
		webtest.click("xpath=//input[@value='确定']");
		webtest.click("xpath=//input[@name='jydt']");//借阅日期
		webtest.click("xpath=//a[contains(.,'现在')]");
		webtest.click("xpath=//input[@name='yjdt']");//归还日期
		webtest.click("xpath=//td[@tdaddclick='m']");
		webtest.click("xpath=//td[@class='td01']");
		webtest.click("xpath=//a[contains(.,'确定')]");
		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.driver.switchTo().defaultContent();
		
	}

}
