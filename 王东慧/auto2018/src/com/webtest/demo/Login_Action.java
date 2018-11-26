package com.webtest.demo;

import com.webtest.core.WebDriverEngine;

public class Login_Action {
	/**
	 * @author wangdonghui
	 */
	private WebDriverEngine webtest;
	  public Login_Action(WebDriverEngine webtest) {
		  this.webtest=webtest;
	  }
	  
	  public void login(String email,String password) 
	  {
		  if(is_login()) {
			webtest.click("link=��¼");
			webtest.type("name=username", "qingdao01");
			webtest.type("name=password", "123456");
			webtest.click("xpath=//input[@value='���ϵ�¼']");
		  }
		
	  }
	  
	  public boolean is_login()
	  {
		  return webtest.isElementPresent("link='�˳�'");
	  }
	  

}
