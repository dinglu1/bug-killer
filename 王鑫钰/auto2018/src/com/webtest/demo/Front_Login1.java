package com.webtest.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Front_Login1 extends BaseTest{


	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://localhost:8032/mymovie/");
		webtest.click("link=µÇÂ¼");
		webtest.type("name=username", "qingdao01");
		webtest.type("name=password", "123456");
		webtest.click("xpath=//input[@value='ÂíÉÏµÇÂ¼']");
		assertTrue(webtest.ifContains("ÍË³ö"));
		

	}

}
