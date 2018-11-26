package com.webtest.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;
import com.webtest.utils.ReadProperties;

public class Front_Login_Test extends BaseTest{

	Login_Action action;
	
	@BeforeMethod
	public void setup()
	{
		action=new Login_Action(webtest);
	}

	@Test(dataProvider="txt",dataProviderClass=NSDataProvider.class)
	public void testLogin(String username,String password) throws Exception  {
		webtest.open(ReadProperties.getPropertyValue("base_url"));
		action.login(username, password);
		assertTrue(webtest.ifContains("ÍË³ö"));
	}
	
}
