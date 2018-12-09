package com.webtest.personnel.action;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

import com.webtest.core.WebDriverEngine;

public class Add_Action {

	WebDriverEngine webtest = null;

	public Add_Action(WebDriverEngine webtest) {
		this.webtest = webtest;
	}

	// 新增员工合同
	public void newContract(String name, String firm, String contractName, String start, String end, String tqenddt) {
		// click
		webtest.click("xpath=//span[contains(.,'人事考勤')]");
		webtest.click("xpath=//div[@onclick='clickmenu(this,0,-1)']");
		webtest.click("xpath=//div[@onclick='clickmenu(this,0,1)']");

		// 点击新增
		webtest.click("xpath=//button[contains(.,' 新增')]");
		webtest.enterFrame("openinputiframe");
		// 签署人
		webtest.click("xpath=//a[contains(.,'选择')]");
		// 点开层
		if ( name.equals("管理员") || name.equals("张飞") || name.equals("赵子龙")) {
			webtest.click("xpath=//td[contains(.,' 开发部')]");
		} else if (name.equals("小乔") || name.equals("貂蝉")) {
			webtest.click("xpath=//td[contains(.,' 财务部')]");
		} else if (name.equals("大乔")) {
			webtest.click("xpath=//td[contains(.,' 行政人事部')]");
		}else if(name.equals("磐石")) {
			webtest.click("xpath=//td[contains(.,' 管理层')]");
		}
		// 选择签署人
		webtest.click("xpath=//input[@xname='" + name + "']");
		webtest.click("xpath=//input[@value='确定']");
		// 签署单位
		webtest.click("name=//select[contains(@name,'companyid')]");
		if (firm.equals("信呼开发团队")) {
			webtest.click("xpath=//option[contains(@value,'1')]");
		} else if (firm.equals("   ├信呼开发团队(泉州分公司)")) {
			webtest.click("xpath=//option[@value='2']");
		} else if (firm.equals("   ├信呼开发团队(北京分公司)")) {
			webtest.click("xpath=//option[@value='3']");
		} else if (firm.equals("房租家软件公司")) {
			webtest.click("xpath=//option[@value='4']");
		}
		// 合同名称
		webtest.typeAndClear("xpath=//input[@name='name']", contractName);
		// 合同类型
		webtest.click("xpath=//select[contains(@name,'httype')]");
		webtest.click("xpath=//option[contains(.,'劳动合同')]");
		// 开始日期
		webtest.runJs("document.getElementsByName('startdt')[0].removeAttribute('readonly');");
		webtest.typeAndClear("xpath=//input[contains(@name,'startdt')]", start);
		// 截止日期
		webtest.runJs("document.getElementsByName('enddt')[0].removeAttribute('readonly');");
		webtest.typeAndClear("xpath=//input[@name='enddt']", end);
		// 提前终止日期
		webtest.runJs("document.getElementsByName('tqenddt')[0].removeAttribute('readonly');");
		webtest.typeAndClear("xpath=//input[@name='tqenddt']", tqenddt);
		// 点击保存
		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.leaveFrame();
		// 断言
		String html = webtest.getHtmlSource();
		Assert.assertEquals(html.contains("新增保存成功"), true);
	}

	// 考勤信息下的外出记录
	public void addOutRecord(String atype, String address, String outtime, String intime, String reason) {
		// click
		webtest.click("xpath=.//*[@id='topheaderid']/table/tbody/tr/td[2]/div/span[6]");
		webtest.click("xpath=//span[contains(.,'人事考勤')]");
		webtest.click("xpath=//div[@onclick='clickmenu(this,5,-1)']");
		webtest.click("xpath=//div[@onclick='clickmenu(this,5,2)']");

		// 点击新增
		webtest.click("xpath=//button[starts-with(@id,'addbtn_')]");
		webtest.enterFrame("openinputiframe");
		// 类型
		webtest.click("xpath=//select[@name='atype']");
		webtest.click("xpath=//option[contains(.,'" + atype + "')]");
		// 外出地址
		webtest.typeAndClear("xpath=//input[contains(@name,'address')]", address);
		// 外出时间
		webtest.runJs("document.getElementsByName('outtime')[0].removeAttribute('readonly');");
		webtest.typeAndClear("xpath=//input[@name='outtime']", outtime);
		// 预计回岗
		webtest.runJs("document.getElementsByName('intime')[0].removeAttribute('readonly');");
		webtest.typeAndClear("xpath=//input[@name='intime']", intime);
		// 外出理由
		webtest.typeAndClear("xpath=//input[contains(@name,'reason')]", reason);
		// 保存
		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.leaveFrame();
		// 断言
		String html = webtest.getHtmlSource();
		Assert.assertEquals(html.contains("新增保存成功"), true);
	}

}
