package com.webtest.customer.test;

import static org.testng.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.core.WebDriverEngine;
import com.webtest.customer.action.Customer_action;
import com.webtest.customer.action.Login_action;
import com.webtest.customer.action.Search_action;

public class Search_test extends BaseTest{
	/**
	 * @author wangdonghui
	 * 搜索框是否有效
	 */
	@BeforeClass
	public void enter_custom() {
		Customer_action customer = new Customer_action(webtest);
		customer.customer();
	}
	
	@Test
	public void Search() throws InterruptedException {
		//找到搜索框
		webtest.tapClick();
		webtest.tapClick();
		//在搜索框中输入内容
		webtest.type("xpath=//input[@placeholder='关键字']", "信呼");
		//点击搜索
		webtest.tapClick();
		webtest.tapEnter();
		Thread.sleep(3000);
		//判断是不是都含有"信呼"
		Search_action find = new Search_action(webtest);	
		assertTrue(find.search("信呼"));
		
	}

}
