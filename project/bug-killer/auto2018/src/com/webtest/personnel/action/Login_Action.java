package com.webtest.personnel.action;

import com.webtest.core.WebDriverEngine;

public class Login_Action {

	WebDriverEngine webtest = null;
	public Login_Action(WebDriverEngine webtest) {
		this.webtest = webtest;
	}
	public void login() {
		webtest.open("http://10.7.1.9:8686/xinhu");
		webtest.type("name=adminuser", "admin");
		webtest.type("xpath=//input[@type='password']", "123456aa");
		webtest.click("xpath=//button[@name='button']");
		
	}
}
