package com.webtest.xingzheng.qifang;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class TestPart4_tushu extends BaseTest {
	@BeforeClass
	//���복��������
	public void Enter() throws InterruptedException{
		LoginTest login = new LoginTest(webtest);
	   	  login.Login();
	   	  Thread.sleep(3000);
	 		  webtest.click("xpath=//div[@id='menu_list_num139']"); 
	 		  Thread.sleep(3000);    	  
	}
	@Test
	//ͼ�������-����
	public void d1() throws InterruptedException{
		webtest.click("xpath=//div[@onclick='clickmenu(this,3,1)']");
		webtest.click("xpath=//button[contains(.,' ����')]");
		webtest.driver.switchTo().frame("openinputiframe");
		webtest.click("xpath=//a[contains(.,'ѡ��')]");//����
		webtest.click("xpath=//input[@value='1']");
		webtest.click("xpath=//input[@value='ȷ��']");
		webtest.click("xpath=//input[@name='jydt']");//��������
		webtest.click("xpath=//a[contains(.,'����')]");
		webtest.click("xpath=//input[@name='yjdt']");//�黹����
		webtest.click("xpath=//td[@tdaddclick='m']");
		webtest.click("xpath=//td[@class='td01']");
		webtest.click("xpath=//a[contains(.,'ȷ��')]");
		webtest.click("xpath=//input[@value='����(S)']");
		webtest.driver.switchTo().defaultContent();
		
	}

}
