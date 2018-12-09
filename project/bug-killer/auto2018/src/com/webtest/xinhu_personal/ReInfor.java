package com.webtest.xinhu_personal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;


//个人办公——个人中心——提醒信息(测试记录状态何时变为已读)
public class ReInfor extends BaseTest{
	@Test
	public void login3() {
		webtest.open("http://10.7.1.9:8686/xinhu/");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("name=button");
	}
	@Test(dependsOnMethods="login3") 
	public void enterInfor() {
		webtest.click("xpath=//span[@class='spanactive']");
		webtest.click("xpath=//div[@id='menu_list_num39']");
		webtest.click("xpath=//div[@id='menu_list_todo']");
	}
	@Test(dependsOnMethods="enterInfor")
	public void reInfor1() {
		webtest.click("xpath=//button[@class='btn btn-success']");
		Assert.assertTrue(webtest.isTextPresent("没有选中行"));
	}
	@Test(dependsOnMethods="enterInfor")
	public void reInfor2(){	
		String c =this.getPage();
		while(c.contains("未读")) {
			webtest.click("xpath=//input[@oi='0']");
			webtest.click("xpath=//button[@class='btn btn-success']");
			Assert.assertTrue(webtest.isTextPresent("处理成功"));
			String s =this.getPage();
			c=s;
		}
		
	}
	public String getPage() {
		String s = webtest.getHtmlSource();
		int front = s.indexOf("class=\"table table-striped table-bordered table-hover");	
		String ftable = s.substring(front);
		int last = ftable.indexOf("</table>");
		String table = ftable.substring(0,last);
		//获取每一个<tr>
		String[] trs = table.split("<tr");
		System.out.println(trs[2]);
		return trs[2];
		
		
		}	
}
