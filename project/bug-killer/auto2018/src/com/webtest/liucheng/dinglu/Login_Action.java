package com.webtest.liucheng.dinglu;

import com.webtest.core.WebDriverEngine;

public class Login_Action {
	  private WebDriverEngine webtest;
	  public Login_Action(WebDriverEngine webtest) {
		  this.webtest=webtest;
	  }
	  
	  public void login(String email,String password) throws InterruptedException 
	  {
			webtest.click("link=登录");
			webtest.type("xpath=//input[@name='adminuser']", email);
			webtest.type("xpath=//input[@type='password']", password);
			webtest.click("xpath=//button[@class='webbtn']");
			Thread.sleep(3000);
	  }
	  
}
