package com.webtest.demo;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class Front_Login1 extends BaseTest{


	@Test
	public void testSearch() throws Exception  {
		webtest.open("http://localhost:8032/MyMovie/admin.php/Index/index.html");
		webtest.click("link=��¼");
		webtest.type("name=username", "admin");
		webtest.type("name=password", "admin");
		webtest.click("xpath=//input[@value='���ϵ�¼']");
		//assertTrue(webtest.ifContains("�˳�"));
		webtest.click("xpath=//input[@type = 'submit']");
		webtest.click("xpath=.//*[@id='sidebar']/div[2]/div[5]/h2");//ӰƬ����
		webtest.click("xpath=.//*[@id='sidebar']/div[2]/div[6]/ul/li[1]/ul/li[2]/div/a/span");//���ӰƬ��Ϣ
		//���ӰƬ
		webtest.type("name=filmname","���ѧԺ");
		webtest.type("name=petname","software");
		webtest.type("name=director","czj");
		webtest.type("name=editor","Wizard");
		webtest.type("name=nation","�й�");
		webtest.type("name=language","����");
		webtest.type("name=picname","C:\\Users\\asus\\Pictures\\lovewallpaper");
		webtest.click(".//*[@id='navTab']/div[2]/div[2]/div/form/div[2]/ul/li[1]/div/div/button");
	}


	}

