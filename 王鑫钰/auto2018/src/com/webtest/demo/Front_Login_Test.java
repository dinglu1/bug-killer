package com.webtest.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Front_Login_Test extends BaseTest{

	Login_Action action;
	
	@BeforeMethod
	public void setup()
	{
		action=new Login_Action(webtest);
	}

	@Test
	public void testLogin() throws Exception  {
		webtest.open("http://localhost:8032/mymovie/");
		action.login("qingdao01", "123456");
		assertTrue(webtest.ifContains("ÍË³ö1"));
		

	}

}
