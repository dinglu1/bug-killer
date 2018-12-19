package com.webtest.xingzheng.qifang;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.core.WebDriverEngine;

public class TestPart1_wupin extends BaseTest{
	 
	 
      //物品管理部分
      @BeforeClass
      //进入物品管理部分
      public void Enter() throws InterruptedException{
    	  LoginTest login = new LoginTest(webtest);
    	  login.Login();
    	  Thread.sleep(3000);
  		  webtest.click("xpath=//div[@id='menu_list_num28']"); 
  		  Thread.sleep(3000);    	  
      }
      @Test
      //物品列表部分-新增
      public void test_a1() throws InterruptedException{
    	  webtest.click("xpath=//div[@onclick='clickmenu(this,0,0)']");
    	  Thread.sleep(3000);
    	  //新增部分
    	  webtest.click("xpath=//button[contains(.,' 新增')]");
    	  Thread.sleep(3000);
    	  webtest.driver.switchTo().frame("openinputiframe");
    	  webtest.type("xpath=//input[@onblur='c.inputblur(this, 0)']","dd");//名称栏
    	  webtest.click("xpath=//select[@name='typeid']");//对应分类栏
    	  webtest.click("xpath=//option[@value='64']");
    	  webtest.click("xpath=//select[@name='unit']");//单位栏
    	  webtest.click("xpath=//option[@value='台']");
    	  webtest.click("xpath=//input[@value='保存(S)']");
    	  webtest.driver.switchTo().defaultContent();//回到原frame
      }
      @Test
      //物品列表部分-搜索
      public void test_a2() throws InterruptedException{
    	  webtest.click("xpath=//div[@onclick='clickmenu(this,0,0)']");
    	  webtest.type("xpath=//input[@placeholder='物品名']","联想显示器");
    	  webtest.click("xpath=//button[@click='search']");//搜索
    	  Thread.sleep(3000);
    	  webtest.type("xpath=//input[@placeholder='物品名']", "");
    	  webtest.click("xpath=//button[@click='search']");//返回全部列表    	  
      } 
      @Test
      //物品列表部分-入库
      public void test_a3() throws InterruptedException{
    	  webtest.click("xpath=//div[@onclick='clickmenu(this,0,0)']");//到原界面（物品列表）
    	  Thread.sleep(3000);
    	  webtest.click("xpath=//button[contains(.,'入库')]");
    	  Thread.sleep(3000);
    	  webtest.type("xpath=//input[@placeholder='名称']", "鼠标");
    	  webtest.tapClick();
    	  webtest.tapEnter();//搜索
    	  Thread.sleep(3000);
    	  webtest.type("xpath=//input[@valid='2']","10");//数量
    	  webtest.click("xpath=//button[@rockdatepickerbool='true']");
    	  webtest.click("xpath=//a[contains(.,'现在')]");//日期
    	  webtest.click("xpath=//select[contains(@id,'9777')]");
    	  webtest.click("xpath=//option[contains(.,'采购入库')]");//下拉菜单
    	  webtest.click("xpath=//button[contains(.,' 确认提交')]");//提交
    	  Thread.sleep(3000);
      }
      @Test
      //物品列表部分-出库
      public void test_a4() throws InterruptedException{
    	  webtest.click("xpath=//div[@onclick='clickmenu(this,0,0)']"); //到原界面（物品列表）
    	  Thread.sleep(3000);
    	  webtest.click("xpath=//button[contains(.,'出库')]");
    	  webtest.type("id=key_1543911717414_4452", "鼠标");
    	  webtest.tapClick();
    	  webtest.tapEnter();
    	  webtest.type("xpath=//input[@valid='2']","10");//数量
    	  webtest.click("xpath=//button[@click='clickdt,1']");
    	  webtest.click("xpath=//a[contains(.,'现在')]");//日期
    	  webtest.click("xpath=//select[contains(@id,'7280')]");
    	  webtest.click("xpath=//option[contains(.,'领用出库')]");//下拉菜单
    	  webtest.click("xpath=//button[contains(.,' 确认提交')]");//提交
    	  Thread.sleep(3000);
    	  webtest.click("xpath=//div[@onclick='clickmenu(this,0,0)']"); //到原界面（物品列表）
      }
      @Test
      //物品列表部分-删除
      public void test_a5() throws InterruptedException{
    	  webtest.click("xpath=//div[@onclick='clickmenu(this,0,1)']");
    	  webtest.click("xpath=//input[@oi='6']");
    	  webtest.click("xpath=//button[@click='del']");
    	  webtest.click("xpath=//button[contains(.,' 确定')]");
      }
}
