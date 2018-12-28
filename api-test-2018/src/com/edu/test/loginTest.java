package com.edu.test;

import org.testng.annotations.Test;

public class loginTest extends Login{
	@Test(description="登录成功")
	public void  test_Login1() throws Exception{
		result = login("86","20000000000","netease123");
		check.verifyXpath("message","success");
	}	
	@Test(description="登录失败，phoneArea参数类型不正确")
	public void test_Login2() throws Exception{
		result = login("'86'","20000000000","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}	
	@Test(description="登录失败，phoneNumber参数类型不正确")
	public void test_Login3() throws Exception{
		result = login("86","'20000000000'","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，password参数类型不正确")
	public void test_Login4() throws Exception{
		result = login("86","20000000000","'netease123'");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，缺少phoneArea参数")
	public void test_Login5() throws Exception{
		result = login("","20000000000","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，缺少phoneNumber参数")
	public void test_Login6() throws Exception{
		result = login("86","","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，缺少password参数")
	public void test_Login7() throws Exception{
		result = login("86","20000000000","");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，电话号码超过11位")
	public void test_Login8() throws Exception{
		result = login("86","200000000000","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，电话号码不足11位")
	public void test_Login9() throws Exception{
		result = login("86","2000000000","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，密码错误")
	public void test_Login10() throws Exception{
		result = login("86","20000000000","wrong");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，用户不存在")
	public void test_Login11() throws Exception{
		result = login("86","10086","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}
	@Test(description="登录失败，区号不存在")
	public void test_Login12() throws Exception{
		result = login("9999","20000000000","netease123");
		check.verifyXpath("message", "用户名或者密码错误");
	}


}
