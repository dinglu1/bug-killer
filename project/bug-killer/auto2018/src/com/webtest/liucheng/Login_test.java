package com.webtest.liucheng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Login_test extends BaseTest{

	
	Login_Action action;
	
	@BeforeMethod
	public void setup()
	{
		action=new Login_Action(webtest);
	}

	@Test
	public void testLogin() throws Exception  {
		webtest.open("http://10.7.1.9:8686/xinhu");
		action.login("admin", "123456");
	}
	
}
