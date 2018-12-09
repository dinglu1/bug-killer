package com.webtest.customer.test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;
import com.webtest.dataprovider.NSDataProvider;
import com.webtest.customer.action.Customer_action;
import com.webtest.customer.action.Search_action;

public class CaoZuo_test extends BaseTest {
	/**
	 * @author wangdonghui 
	 * 判断操作下各个按钮是否有用
	 * 
	 */
	@BeforeClass
	public void enter_customer() {
		Customer_action custom = new Customer_action(webtest);
		custom.customer();
	}

	//详情
	@Test(dataProvider = "custom_id", dataProviderClass = NSDataProvider.class)
	public void detil(String id) {
		// 点击操作
		Search_action action = new Search_action(webtest);
		webtest.click("xpath=" + action.troi(id));
		// 点击详情
		webtest.click("xpath=//li[@temp='0']");
		//进入ifream
		webtest.enterFrame("openinputiframe");
		//查看详情页面的第一行
		String custname1 = webtest.getText("xpath=html/body/div[1]/div/div[4]/div/table/tbody/tr[1]/td[2]");
		System.out.println(custname1);
		//退出ifream
		webtest.leaveFrame();
		//查看我的客户中的客户名称
		String custname2 = webtest.getText("xpath=" + ".//*[starts-with(@id,'tbody_')]/tr[" + id + "]/td[3]");
		System.out.println(custname2);
		//断言
		assertTrue(custname1.equals(custname2));	
	}
	
	//共享给
	@Test(dataProvider = "custom_id", dataProviderClass = NSDataProvider.class)
	public void share(String id) {
		// 点击操作
		Search_action action = new Search_action(webtest);
		webtest.click("xpath=" + action.troi(id));
		//点击共享给按钮
		webtest.click("xpath=//li[contains(.,'共享给...')]");
		//选择共享给谁
		webtest.click("xpath=//td[contains(.,' 开发部')]");
		webtest.click("xpath=//input[@xname='张飞']");
		//点击确定
		webtest.click("xpath=//input[@value='确定']");
		//断言
		assertTrue(webtest.isTextPresent("处理成功"));
	}
	
	//添加提醒设置
	@Test(dataProvider = "addReminSetting", dataProviderClass = NSDataProvider.class)
	public void addReminSetting(String id, String enddt, String explain, String rate, String date,
			String time1,String time2,String time3,String time4) throws InterruptedException {
		// 点击操作
		Search_action action = new Search_action(webtest);
		webtest.click("xpath=" + action.troi(id));
		// 点击添加提醒设置
		webtest.click("xpath=//li[contains(.,'提醒设置')]");
		// 进入ifream
		webtest.enterFrame("openinputiframe");
		// 输入截止时间
		webtest.runJs("document.getElementById('div_enddt').firstChild.removeAttribute('readonly')");
		webtest.typeAndClear("name=enddt", enddt);
		// 输入提醒内容
		webtest.typeAndClear("xpath=//textarea[@class='textarea']", explain);
		// 提醒给
		webtest.click("xpath=//a[contains(.,'选择')]");
		// 选择管理层
		webtest.click("xpath=//input[@xname='管理层']");
		// 点击确定
		webtest.click("xpath=//input[@value='确定']");
		// 频率设置
		// 点击频率设置中的下三角
		webtest.click("xpath=//select[@name='rave_pinlvs1']");
		if (rate.equals("每小时")) {
			webtest.click("xpath=//option[contains(.,'每小时')]");
			webtest.runJs("document.getElementsByName('rave_pinlvs2')[0].removeAttribute('readonly')");
			webtest.runJs("document.getElementsByName('rave_pinlvs3')[0].removeAttribute('readonly')");
			Thread.sleep(3000);
			webtest.type("name=rave_pinlvs2", time1);
			Thread.sleep(3000);
			webtest.type("name=rave_pinlvs3", time2);
		} else {
			webtest.click("xpath=//option[contains(.,'" + rate + "')]");
			webtest.runJs("document.getElementsByName('rave_pinlvs2')[0].removeAttribute('readonly')");
			if (rate.equals("仅一次"))
				webtest.typeAndClear("name=rave_pinlvs2", date);
			else if (rate.equals("每月"))
				webtest.typeAndClear("name=rave_pinlvs2", time3);
			else if (rate.equals("每年"))
				webtest.typeAndClear("name=rave_pinlvs2", time4);
		}

		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.leaveFrame();
		// 断言
		assertTrue(webtest.isTextPresent("成功"));

	}
	
	//删除
	@Test(dataProvider="custom_id",dataProviderClass = NSDataProvider.class)
	public void delete(String id) {
		// 点击操作
		Search_action action = new Search_action(webtest);
		webtest.click("xpath=" + action.troi(id));
		// 点击删除
		webtest.click("xpath=//li[contains(.,'删除')]");
		// 点击确定
		webtest.click("xpath=//button[contains(.,' 确定')]");
		//验证
		assertTrue(webtest.isTextPresent("处理成功"));	
	}
}
