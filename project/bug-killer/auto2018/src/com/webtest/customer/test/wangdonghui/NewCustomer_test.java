package com.webtest.customer.test.wangdonghui;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.customer.action.wangdonghui.Customer_action;
import com.webtest.dataprovider.NSDataProvider;

public class NewCustomer_test extends BaseTest{
	/**
	 * @author wangdonghui
	 * 添加客户
	 */
	@BeforeClass
	public void enter_customer() {
		Customer_action customer = new Customer_action(webtest);
		customer.customer();
	}
	
	@Test(dataProvider="addcustomer",dataProviderClass = NSDataProvider.class)
	public void addCustomer(String name,String laiyuan,String unitname,String type,
			String linkname,String email,String tel,String mobile,String address,
			String routeline,String explain,String status,String isstat) {
		
		//点击新增
		webtest.click("xpath=//button[contains(.,' 新增')]");
		
		//进入ifream
		webtest.enterFrame("openinputiframe");
		//客户名称
		webtest.type("name=name", name);
		//来源
		webtest.click("name=laiyuan");
		webtest.click("xpath=//option[@value='"+ laiyuan +"']");
		//客户单位
		webtest.type("name=unitname",unitname);
		//客户类型
		webtest.click("name=type");
		webtest.click("xpath=//option[@value='" + type +"']");
		//联系人
		webtest.type("name=linkname", linkname);
		//邮箱
		webtest.type("name=email", email);
		//联系电话
		webtest.type("name=tel", tel);
		//联系手机
		webtest.type("name=mobile", mobile);
		//地址
		webtest.type("name=address",address);
		//交通路线
		webtest.type("name=routeline",routeline);
		//说明
		webtest.type("name=routeline",explain);
		
		webtest.click("name=status");
		webtest.click("xpath=//option[contains(.,'" + status + "')]");
		webtest.click("name=isstat");
		webtest.click("xpath=//option[contains(.,'" + isstat + "')]");
		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.leaveFrame();
	
		assertTrue(webtest.isTextPresent("保存成功"));
		
		
	}
}
