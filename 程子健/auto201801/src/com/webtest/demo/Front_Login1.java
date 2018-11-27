package com.webtest.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Front_Login1 extends BaseTest{


	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://localhost:8032/MyMovie/admin.php/Index/index.html");
		webtest.click("link=登录");
		webtest.type("name=username", "admin");
		webtest.type("name=password", "admin");
		webtest.click("xpath=//input[@value='马上登录']");
		//assertTrue(webtest.ifContains("退出"));
		webtest.click("xpath=//input[@type = 'submit']");
		webtest.click("xpath=.//*[@id='sidebar']/div[2]/div[5]/h2");//影片管理
		webtest.click("xpath=.//*[@id='sidebar']/div[2]/div[6]/ul/li[1]/ul/li[2]/div/a/span");//添加影片信息
		//添加影片
		webtest.type("name=filmname","软件学院");
		webtest.type("name=petname","software");
		webtest.type("name=director","czj");
		webtest.type("name=editor","Wizard");
		webtest.type("name=nation","中国");
		webtest.type("name=language","汉语");
		webtest.type("name=picname","C:\\Users\\asus\\Pictures\\lovewallpaper");
		webtest.click(".//*[@id='navTab']/div[2]/div[2]/div/form/div[2]/ul/li[1]/div/div/button");
	}


	}

