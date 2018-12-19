package com.webtest.customer.test.wangdonghui;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.customer.action.wangdonghui.Login_action;
import com.webtest.dataprovider.NSDataProvider;

public class NewSell_test extends BaseTest{
	/**
	 * @author wangdonghui
	 * 添加我的销售机会
	 */
	@BeforeClass
	public void login() {
		Login_action login = new Login_action(webtest);
		login.Login_Action("admin", "123456aa","管理员");
	}
	
	@Test(dataProvider="addsell",dataProviderClass = NSDataProvider.class)
	public void addSell(String xu,String state,String laiyuan,String money,String explain) {
		//点击客户
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webtest.click("xpath=//span[contains(.,'客户')]");
		//点击销售机会
		webtest.click("xpath=//div[@id='menu_list_num73']");
		//点击我的销售机会
		webtest.click("xpath=//div[@id='menu_list_num110']");
		//新增
		webtest.click("xpath=//button[starts-with(@id,'addbtn_')]");
		//进入ifream
		webtest.enterFrame("openinputiframe");
		//客户
		webtest.click("xpath=//a[contains(.,'选择')]");
		webtest.click("xpath=//input[@xu='" + xu + "']");
		webtest.click("xpath=//input[@value='确定']");
		
		//状态
		webtest.click("name=state");
		webtest.click("xpath=//option[contains(.,'" + state + "')]");
		//来源
		webtest.click("name='laiyuan");
		webtest.click("xpath=//option[@value='" + laiyuan + "']");
		//金额
		webtest.typeAndClear("name=money",money);
		//说明
		webtest.type("name=explain",explain);
		webtest.click("xpath=//input[@value='保存(S)']");
		//离开frame
		webtest.leaveFrame();
		
		assertTrue(webtest.isTextPresent("保存成功"));
	}
}
