package com.webtest.customer.test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;
import com.webtest.customer.action.Login_action;

public class NewCustract_test extends BaseTest{
	/**
	 * @author wangdonghui
	 * 添加合同
	 */
	@BeforeClass
	public void login() {
		Login_action login = new Login_action(webtest);
		login.Login_Action("admin", "123456aa","管理员");
	}
	
	@Test(dataProvider="addagreement",dataProviderClass = NSDataProvider.class)
	public void addAgreement(String signdt,String xu, String saleid,
			String startdt,String enddt,String type,
			String money,String content,String explain) {
		//点击客户
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webtest.click("xpath=//span[contains(.,'客户')]");
		//点击合同
		webtest.click("xpath=//div[@id='menu_list_num104']");
		//点击我的合同
		webtest.click("xpath=//div[@id='menu_list_num108']");
		//点击新增
		webtest.click("xpath=//button[starts-with(@id,'addbtn_')]");
		
		//进入iframe
		webtest.enterFrame("openinputiframe");
		//签约日期
		webtest.runJs("document.getElementById('div_signdt').firstChild.removeAttribute('readonly')");
		webtest.typeAndClear("name=signdt",signdt);
		//客户名称	
		webtest.click("xpath=//a[contains(.,'选择')]");
		webtest.click("xpath=//input[@xu='" + xu + "']");
		webtest.click("xpath=//input[@value='确定']");
		//销售机会
		webtest.click("name=saleid");
		webtest.click("xpath=//option[contains(.,'[" +saleid+ "')]");
		
		//生效日期
		webtest.runJs("document.getElementById('div_startdt').firstChild.removeAttribute('readonly')");
		webtest.typeAndClear("name=startdt",startdt);
		//截止日期
		webtest.runJs("document.getElementById('div_enddt').firstChild.removeAttribute('readonly')");
		webtest.typeAndClear("name=enddt",enddt);		
		
		//合同金额
//		webtest.runJs("document.getElementsByName('money')[0].removeAttribute('type')");
//		webtest.runJs("document.getElementsByName('money')[0].innerHTML='"+ money +"';");
		webtest.typeAndClear("xpath=//input[@name='money']",money);
		//合同类型
		webtest.click("name=type");
		webtest.click("xpath=//option[contains(.,'"+ type +"')]");
		//合同内容
		webtest.type("name=content", content);
		//说明
		webtest.type("name=explain",explain);
		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.leaveFrame();
		
		assertTrue(webtest.isTextPresent("保存成功"));
		
	}
	
}
