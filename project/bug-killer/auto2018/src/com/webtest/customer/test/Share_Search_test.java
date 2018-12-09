package com.webtest.customer.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;
import com.webtest.customer.action.Customer_action;
import com.webtest.customer.action.Search_action;

public class Share_Search_test extends BaseTest{
	/**
	 * @author wangdonghui 
	 * 判断操作下的共享给中的搜索是否有用
	 * 不完善
	 */
	@BeforeClass
	public void enter_customer() {
		Customer_action customer = new Customer_action(webtest);
		customer.customer();

	}

	@Test(dataProvider = "custom_id", dataProviderClass = NSDataProvider.class)
	public void share_search(String id) {
		// 点击操作
		Search_action action = new Search_action(webtest);
		webtest.click("xpath=" + action.troi(id));
		//点击共享给按钮
		webtest.click("xpath=//li[contains(.,'共享给...')]");
		//查找
		webtest.type("xpath=//input[@placeholder='部门/姓名/职位']", "董事长");
		//点击查找按钮
		webtest.click("xpath=//input[@value='查找']");
		//断言
		
	}
}
