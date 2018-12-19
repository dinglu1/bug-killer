package com.webtest.customer.test.wangdonghui;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.customer.action.wangdonghui.Customer_action;

public class Redata_test extends BaseTest{
	/**
	 * @author wangdonghui
	 * 重新统计数据按钮
	 */
	@BeforeClass
	public void enter_customer() {
		Customer_action customer = new Customer_action(webtest);
		customer.customer();

	}
	
	@Test
	public void redata() {
		webtest.click("xpath=//button[contains(.,'重新统计金额')]");
		assertTrue(webtest.isTextPresent("统计完成"));
	}

}
