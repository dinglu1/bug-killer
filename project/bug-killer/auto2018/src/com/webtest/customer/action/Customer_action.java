package com.webtest.customer.action;

import com.webtest.core.WebDriverEngine;

public class Customer_action {
	/**
	 * @author wangdonghui
	 * 客户页面操作按钮
	 */

	public WebDriverEngine webtest = null;

	public Customer_action(WebDriverEngine webtest) {
		this.webtest = webtest;
		Login_action login = new Login_action(webtest);
		login.Login_Action("admin", "123456aa","管理员");
	}

	public void customer() {
		// 点击客户
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webtest.click("xpath=//span[contains(.,'客户')]");
		// 点击客户
		webtest.click("xpath=//div[@id='menu_list_num64']");
		// 点击我的客户
		webtest.click("xpath=//div[@id='menu_list_num112']");
	}

}
