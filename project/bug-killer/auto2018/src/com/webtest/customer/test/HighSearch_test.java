package com.webtest.customer.test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.customer.action.Customer_action;
import com.webtest.customer.action.Search_action;

public class HighSearch_test extends BaseTest {
	/**
	 * @author wangdonghui 
	 * 判断我的客户处高级搜索是否有用
	 */
	@BeforeClass
	public void enter_customer() {
		Customer_action customer = new Customer_action(webtest);
		customer.customer();
	}

	@BeforeMethod
	public void enter_customer_hightsearch() {
		// 点击下三角
		webtest.click("xpath=//button[starts-with(@id,'downbtn')]");
		// 点击高级搜索
		webtest.click("xpath=//li[contains(.,'高级搜索')]");
	}

	// 按客户名称搜索
	@Test
	public void custname() {
		String custname = "微信";
		// 点击重置
		webtest.click("xpath=//a[contains(.,'[重置]')]");
		// 输入客户名称
		webtest.type("xpath=//input[@name='soufields_name']", custname);
		// 点击搜索
		webtest.click("xpath=//a[contains(.,'搜索')]");
		// 断言
		Search_action search = new Search_action(webtest);
		// 方法一
		// assertTrue(search.search(custname, 3));

		// 方法二
		int len = search.len_tr();
		int rigth = 0;
		for (int i = 1; i <= len; i++) {
			String text = webtest.getText("xpath=.//*[starts-with(@id,'tbody_')]/tr[" + i + "]/td[3]");
			if (text.equals(custname)) {
				rigth++;
			}
		}
		assertTrue(rigth == len);
	}


	// 按客户类型搜索
	@Test
	public void type() {
		String type = "互联网";

		webtest.click("xpath=//a[contains(.,'[重置]')]");
		webtest.click("xpath=//select[@name='soufields_type']");
		webtest.click("xpath=//option[@value='" + type + "']");
		webtest.click("xpath=//a[contains(.,'搜索')]");
		// 断言
		Search_action search = new Search_action(webtest);
		// 方法一
		assertTrue(search.search(type, 5));
	}

	// 按联系手机
	@Test
	public void mobile() {
		String mobile = "158";
		webtest.click("xpath=//a[contains(.,'[重置]')]");
		webtest.type("xpath=//input[@name='soufields_mobile']", mobile);
		webtest.click("xpath=//a[contains(.,'搜索')]");
		// 断言
		Search_action search = new Search_action(webtest);
		// 方法一
		assertTrue(search.search(mobile, 9));
	}


	// 按合同数
	@Test
	public void htshu() {
		webtest.click("xpath=//a[contains(.,'[重置]')]");
		int start = 1;
		String s = String.valueOf(start);
		webtest.type("xpath=//input[@name='soufields_htshu_start']", s);
		int end = 2;
		String e = String.valueOf(end);
		webtest.type("xpath=//input[@name='soufields_htshu_end']", e);
		webtest.click("xpath=//a[contains(.,'搜索')]");
		// 断言
		Search_action search = new Search_action(webtest);
		// 方法二
		int len = search.len_tr();
		int rigth = 0;
		for (int i = 1; i <= len; i++) {
			String text = webtest.getText("xpath=.//*[starts-with(@id,'tbody_')]/tr[" + i + "]/td[12]");
			int t = Integer.parseInt(text);
			if (t >= start && t <= end) {
				rigth++;
			}
		}
		assertTrue(rigth == len);
	}

	// 按销售总额
	@Test
	public void moneyz() {
		webtest.click("xpath=//a[contains(.,'[重置]')]");
		int start = 1000;
		String s = String.valueOf(start);
		webtest.type("xpath=//input[@name='soufields_moneyz_start']", s);
		int end = 2000;
		String e = String.valueOf(end);
		webtest.type("xpath=//input[@name='soufields_moneyz_end']", e);
		webtest.click("xpath=//a[contains(.,'搜索')]");
		// 断言
		Search_action search = new Search_action(webtest);
		// 方法二
		int len = search.len_tr();
		int rigth = 0;
		for (int i = 1; i <= len; i++) {
			String text = webtest.getText("xpath=.//*[starts-with(@id,'tbody_')]/tr[" + i + "]/td[13]");
			int t = Integer.parseInt(text);
			if (t >= start && t <= end) {
				rigth++;
			}
		}
		assertTrue(rigth == len);
	}

}
