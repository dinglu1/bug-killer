package com.webtest.xingzheng;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

public class TestPart2_zichan extends BaseTest{
	 @BeforeClass
	 //进入固定资产部分
	 public void Enter() throws InterruptedException{
   	  LoginTest login = new LoginTest(webtest);
   	  login.Login();
   	  Thread.sleep(3000);
 		  webtest.click("xpath=//div[@id='menu_list_num137']"); 
 		  Thread.sleep(3000);    	  
     }
	 
	 @Test
	 //固定资产部分-搜索
	 public void test_b1() throws InterruptedException{
   	  webtest.type("xpath=//input[@placeholder='资产名称/编号/使用人']","笔记本");
   	  webtest.click("xpath=//button[@click='search']");//搜索
   	  Thread.sleep(3000);
   	  webtest.typeAndClear("xpath=//input[@placeholder='资产名称/编号/使用人']", "");
   	  webtest.click("xpath=//button[@click='search']");//返回全部列表    	  
     } 
	 @Test
	 //固定资产部分-新增
	 public void test_b2() throws InterruptedException{
		 webtest.click("xpath=//button[contains(.,' 新增')]");//点击新增
		 webtest.driver.switchTo().frame("openinputiframe");//转换frame
		 webtest.click("xpath=//select[@name='typeid']");//点击分类下拉菜单
		 webtest.click("xpath=//option[contains(.,'打印机')]");//选择
		 webtest.type("xpath=//input[@name='title']", "打印机1");//填写名称
		 webtest.click("xpath=//select[@name='ckid']");//存放仓库
		 webtest.click("xpath=//option[@value='214']");
		 webtest.click("xpath=//select[@name='laiyuan']");//资产来源
		 webtest.click("xpath=//option[@value='购入']");
		 webtest.click("xpath=//select[@name='state']");//状态
		 webtest.click("xpath=//option[contains(.,'闲置')]");
		 webtest.click("xpath=//input[@value='保存(S)']");//保存
		 webtest.driver.switchTo().defaultContent();//回到原frame
	 }
//	 @Test
//	 //固定资产部分-操作
//	 public void test_b3() throws InterruptedException{
//	     //   详情
//		 webtest.click("xpath=//a[@oi='0']");
//		 webtest.click("xpath=//li[@temp='0']");
//		 webtest.driver.switchTo().frame("openinputiframe");
//		 webtest.click("xpath=//a[contains(.,'操作V')]");
//		 webtest.click("xpath=//li[contains(.,'关闭')]");
//		 webtest.driver.switchTo().defaultContent();
//	     //   领用登记
//		 webtest.click("xpath=//a[@oi='0']");
//		 webtest.click("xpath=//li[contains(.,'领用登记')]");
//		 webtest.driver.switchTo().frame("openinputiframe");
//		 webtest.type("xpath=//textarea[@style='width:310px;height:60px']", "已领用");
//		 webtest.click("xpath=button[contains(.,' 确定')]");
//		 webtest.driver.switchTo().defaultContent();
//	     //   维修登记
//		 webtest.click("xpath=//a[@oi='0']");
//		 webtest.click("xpath=//li[contains(.,'维修登记')]");
//		 webtest.driver.switchTo().frame("openinputiframe");
//		 webtest.type("xpath=//textarea[@id='confirm_input']", "需维修");
//		 webtest.click("xpath=//button[contains(.,' 确定')]");
//		 webtest.driver.switchTo().defaultContent();
//	     //   评论
//		 webtest.click("xpath=//a[@oi='0']");
//		 webtest.click("xpath=//li[contains(.,'评论')]");
//		 webtest.driver.switchTo().frame("openinputiframe");
//		 webtest.type("xpath=//textarea[@class='input']", "评论");
//		 webtest.click("xpath=//button[contains(.,' 确定')]");
//		 webtest.driver.switchTo().defaultContent();
//		 //   +添加提醒设置
//		 webtest.click("xpath=//a[@oi='0']");
//		 webtest.click("xpath=//li[contains(.,'＋添加提醒设置')]");
//		 webtest.driver.switchTo().frame("openinputiframe");
//		 webtest.click("xpath=//select[@name='rave_pinlvs1']");
//		 webtest.click("xpath=//option[contains(.,'仅一次')]");
//		 webtest.click("xpath=//input[@name='rave_pinlvs2']");
//		 webtest.click("xpath=//a[contains(.,'现在')]");
//		 webtest.click("xpath=//input[@name='ratecont']");
//		 webtest.click("xpath=//input[@value='保存(S)']");
//		 webtest.driver.switchTo().defaultContent();
//		 //   编辑
//		 webtest.click("xpath=//a[@oi='0']");
//		 webtest.click("xpath=//li[contains(.,'编辑')]");
//		 webtest.driver.switchTo().frame("openinputiframe");
//		 webtest.click("xpath=//select[@name='rave_pinlvs1']");
//		 webtest.typeAndClear("xpath=//input[@name='title']", "打印机2");//修改名称
//		 webtest.click("xpath=//input[@value='保存(S)']");
//		 webtest.driver.switchTo().defaultContent();
//		 //   删除
//		 webtest.click("xpath=//a[@oi='0']");
//		 webtest.click("xpath=//li[contains(.,'删除')]");
//		 webtest.driver.switchTo().frame("openinputiframe");
//		 webtest.click("xpath=//button[contains(.,' 确定')]");
//		 webtest.driver.switchTo().defaultContent();
//	 }

}
