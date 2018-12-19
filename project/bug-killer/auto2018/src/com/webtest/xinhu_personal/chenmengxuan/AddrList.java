package com.webtest.xinhu_personal.chenmengxuan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;

public class AddrList extends BaseTest{
	//个人办公--个人中心--个人通讯录的测试
	@Test
	public void login1() {
		webtest.open("http://10.7.1.9:8686/xinhu/");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
	}
	@Test(dependsOnMethods="login1")
	public void addrList() {
		webtest.click("xpath=//span[@class='spanactive']");
		webtest.click("xpath=//div[@id='menu_list_num39']");
		webtest.click("xpath=//div[@id='menu_list_num168']");
	}
	@Test(dataProvider="txt1",dataProviderClass = NSDataProvider.class,dependsOnMethods="addrList")
	public void searchList(String name) {
		webtest.typeAndClear("xpath=//input[@placeholder='标题']",name);
		webtest.click("xpath=//button[@click='search']");
		this.getPage(name);
	}
//	@Test(dependsOnMethods="addrList")
//	public void group() throws InterruptedException {
//		webtest.click("xpath=//div[starts-with(@id,'treebody')]/ul[3]/li[1]/div");
//		webtest.doubleClick("xpath=//div[starts-with(@id,'treebody')]/ul[3]/li[1]/div");
//		
//		Thread.sleep(5000);
////		this.getPage("同事");
//	}
	
	public void getPage(String name) {
		String s = webtest.getHtmlSource();
		int front = s.indexOf("<tbody id=") ;
		String ftbody = s.substring(front);
		int last = ftbody.indexOf("</tbody>");
		String body = ftbody.substring(0,last);
		//获取每一个<tr>
		String[] trs = body.split("<tr");
		for(int i =1;i<trs.length;i++) {
//		System.out.println(trs[i]);
		Assert.assertTrue(trs[i].contains(name));
		}	
	}

}
