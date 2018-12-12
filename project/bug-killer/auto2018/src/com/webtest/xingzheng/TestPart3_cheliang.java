package com.webtest.xingzheng;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class TestPart3_cheliang extends BaseTest{
	@BeforeClass
	//进入车辆管理部分
	public void Enter() throws InterruptedException{
		LoginTest login = new LoginTest(webtest);
	   	  login.Login();
	   	  Thread.sleep(3000);
	 		  webtest.click("xpath=//div[@id='menu_list_num138']"); 
	 		  Thread.sleep(3000);    	  
	}
	
	@Test
	//车辆列表部分-新增
	public void c1() throws InterruptedException{
		webtest.click("xpath=//div[@onclick='clickmenu(this,2,0)']");
		webtest.click("xpath=//button[@click='clickwin,0']");
  	    webtest.driver.switchTo().frame("openinputiframe");
		webtest.type("xpath=//input[@name='carnum']", "冀A88888");//车牌号
		webtest.click("xpath=//select[@name='cartype']");//类型
		webtest.click("xpath=//option[@value='轿车']");
		webtest.click("xpath=//select[@name='carbrand']");//品牌
		webtest.click("xpath=//option[@value='奔驰']");
		webtest.type("xpath=//input[@name='carmode']", "A8");//型号
		webtest.click("xpath=//input[@name='buydt']");//日期
		webtest.click("xpath=//a[contains(.,'现在')]");
		webtest.type("xpath=//input[@name='buyprice']", "1000000");//价格
		webtest.click("xpath=//select[@name='state']");//状态
		webtest.click("xpath=//option[contains(.,'可用')]");
		webtest.click("xpath=//input[@id='AltS']");
		webtest.driver.switchTo().defaultContent();
	}
	
	@Test
	//车辆列表部分-搜索
	public void c2() throws InterruptedException{
		webtest.click("xpath=//div[@onclick='clickmenu(this,2,0)']");
		webtest.type("xpath=//input[@class='form-control']","N7");
		webtest.click("xpath=//button[@click='searchbtn']");
		webtest.typeAndClear("xpath=//input[@class='form-control']","");
		webtest.click("xpath=//button[@click='searchbtn']");//返回全部
	}
	
	
	@Test
	//车辆预定状态部分-同步性
	public void c3() throws InterruptedException{
		webtest.click("xpath=//div[@onclick='clickmenu(this,2,2)']");
		webtest.click("xpath=//button[contains(.,' 新增')]");
		webtest.driver.switchTo().frame("openinputiframe");
		webtest.click("xpath=//a[@id='btnchange_usename']");//使用者
		webtest.click("xpath=//input[@xname='信呼开发团队']");
		webtest.click("xpath=//input[@value='确定']");
		webtest.typeAndClear("xpath=//input[@name='useren']","2");//人数
		webtest.click("xpath=//input[@name='startdt']");//开始时间
		webtest.click("xpath=//td[@tdaddclick='m']");
		webtest.click("xpath=//td[@xu='3']");
		webtest.click("xpath=//a[contains(.,'确定')]");
		webtest.click("xpath=//input[@name='enddt']");//结束时间
		webtest.click("xpath=//td[@tdaddclick='m']");
		webtest.click("xpath=//td[@xu='8']");
		webtest.click("xpath=//a[contains(.,'确定')]");
		webtest.type("xpath=//input[@name='address']","莫斯科");//目的地
		webtest.type("xpath=//input[@name='xianlines']","a to b");//线路
		webtest.click("xpath=//span[@id='div_carnum']/table/tbody/tr/td[2]");//车
		webtest.click("xpath=//input[@value='3']");
		webtest.click("xpath=//input[@value='确定']");
		webtest.click("xpath=//a[@id='btnchange_jianame']");//驾驶员
		webtest.click("xpath=//td[contains(.,' 行政人事部')]");
		webtest.click("xpath=//input[@xname='大乔']");
		webtest.click("xpath=//input[@value='确定']");
		webtest.click("xpath=//input[@id='AltS']");//保存
		webtest.driver.switchTo().defaultContent();
		webtest.click("xpath=//div[@onclick='clickmenu(this,2,3)']");
	}
	
	@Test
	//车辆预定查询部分-操作
	public void c4() throws InterruptedException{
		webtest.click("xpath=//div[@onclick='clickmenu(this,2,2)']");
		webtest.click("xpath=//select[@id='selstatus_1544511554184_6726']");//筛选待处理预定
		webtest.click("xpath=//option[contains(.,'待处理')]");
		webtest.click("xpath=//button[contains(.,'搜索')]");
		webtest.click("xpath=//a[contains(.,'操作')]");
		webtest.click("xpath=//li[contains(.,'作废申请...')]");
		webtest.type("xpath=//textarea[@class='input']", "dd");
		webtest.click("xpath=//button[@id='confirm_btn1']");
	}
	

}
