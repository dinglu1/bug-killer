package com.webtest.personnel.personnal_files;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.personnel.action.File_Action;
import com.webtest.personnel.action.Login_Action;

public class File_AdvancedSearch_Test extends BaseTest{

	Login_Action Login = null;
	File_Action File = null;
	
	@BeforeMethod
	public void initAction() {
		Login = new Login_Action(webtest);
		File = new File_Action(webtest);
		Login.login();
	}
	
	@Test
	public void testAdvancedSearch() throws InterruptedException {
		File.click();
		File.advancedSearch("小乔", "财务部", "正式", "", "", "", "");
	}
}
