package com.webtest.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;

public class Front_Login1_Test extends BaseTest{
	/**
	 * @author wangdonghui
	 * @throws Exception
	 */

	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://localhost:8032/mymovie/");
		webtest.click("link=��¼");
		webtest.type("name=username", "qingdao01");
		webtest.type("name=password", "123456");
		webtest.click("xpath=//input[@value='���ϵ�¼']");
		assertTrue(webtest.ifContains("�˳�"));
		

	}

}
