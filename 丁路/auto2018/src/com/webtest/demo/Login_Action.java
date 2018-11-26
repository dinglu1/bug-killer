package com.webtest.demo;

import com.webtest.core.WebDriverEngine;

public class Login_Action {
	private WebDriverEngine webtest;
	  public Login_Action(WebDriverEngine webtest) {
		  this.webtest=webtest;
	  }
	  
	  public void login(String email,String password) 
	  {
	
			webtest.click("link=µÇÂ¼");
			webtest.type("name=username", "qingdao01");
			webtest.type("name=password", "123456");
			webtest.click("xpath=//input[@value='ÂíÉÏµÇÂ¼']");
		
	  }
	  
	  public boolean is_login()
	  {
		  return webtest.isElementPresent("id=logoutLink");
	  }
	  

}
