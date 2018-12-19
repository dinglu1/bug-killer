package com.webtest.customer.test.wangdonghui;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.customer.action.wangdonghui.Login_action;
import com.webtest.dataprovider.NSDataProvider;

public class NewRecipt_test extends BaseTest{
	/**
	 * @author wangdonghui
	 * 添加收款单
	 */
	@BeforeClass
	public void login() {
		Login_action login = new Login_action(webtest);
		login.Login_Action("admin", "123456aa","管理员");
	}
	
	@Test(dataProvider="addrecipt",dataProviderClass = NSDataProvider.class)
	public void addRecipt(String contract,String xu,String date,String type,String money,
			String ispayment,String datetime,String explain) {
		//点击客户
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webtest.click("xpath=//span[contains(.,'客户')]");
		//点击收款单
		webtest.click("xpath=//div[@id='menu_list_num105']");
		//点击我的收款单
		webtest.click("xpath=//div[@id='menu_list_num106']");
		//新增
		webtest.click("xpath=//button[starts-with(@id,'addbtn_')]");
		//进入ifream
		webtest.enterFrame("openinputiframe");
		//合同
		webtest.click("name=htid");
		webtest.click("xpath=//option[contains(.,'"+ contract +"')]");
		//客户名称
		webtest.click("xpath=//a[contains(.,'选择')]");
		webtest.click("xpath=//input[@xu='" + xu + "']");
		webtest.click("xpath=//input[@value='确定']");
		//所属日期  
		webtest.runJs("document.getElementById('div_dt').firstChild.removeAttribute('readonly')");
		webtest.typeAndClear("xpath=//input[@inputtype='date']",date);
		//类型
		webtest.click("name=type");
		webtest.click("xpath=//option[contains(.,'"+ type +"')]");
		//金额
		webtest.typeAndClear("name=money",money);
		//是否付款
		webtest.click("name=ispay");
		webtest.click("xpath=//option[contains(.,'" + ispayment  + "')]");
		//收付款时间
		webtest.runJs("document.getElementById('div_paydt').firstChild.removeAttribute('readonly')");
		webtest.typeAndClear("xpath=//input[@inputtype='datetime']",datetime);
		//说明
		webtest.type("name=explain",explain);
		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.leaveFrame();
		
		assertTrue(webtest.isTextPresent("保存成功"));
			
	}
}
