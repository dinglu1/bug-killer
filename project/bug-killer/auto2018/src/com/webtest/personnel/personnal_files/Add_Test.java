package com.webtest.personnel.personnal_files;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.personnel.action.Add_Action;
import com.webtest.personnel.action.File_Action;
import com.webtest.personnel.action.Login_Action;

public class Add_Test extends BaseTest{

	Login_Action Login = null;
	Add_Action Add = null;
	@BeforeMethod
	public void initAction() {
		Login = new Login_Action(webtest);
		Add = new Add_Action(webtest);
		Login.login();
	}
	@Test
	public void testNewContract() {
		Add.newContract("管理员", "信呼开发团队", "合同", "2018-12-06", "2018-12-25","");
	}

	@Test
	public void testAddOutRecord() {
		Add.addOutRecord("外出", "师大", "2018-12-01", "2018-12-30", "考试");
	}

	
}
