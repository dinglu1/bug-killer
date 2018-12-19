package com.webtest.personnel.action.duanxiaoyue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.webtest.core.WebDriverEngine;

public class File_Action {

	//人员档案页面所有的方法
	WebDriverEngine webtest = null;
	Search_Action search_action = null;
	Search_Assert_Action assert_action = null;
	String dept = null;
	String job = null;
	boolean a = true;
	String tr ="<tr[^>]*?>([\\s\\S]*?)</tr>";
	String td = "<td[^>]*?>([\\s\\S]*?)</td>";
	String load_start = "window.onload=function(){";
	String load_end = "}";
	public File_Action(WebDriverEngine webtest) {
		this.webtest = webtest;
		search_action = new Search_Action(webtest);
		assert_action = new Search_Assert_Action(webtest);
	}
	
	//点击到人员档案页面
	public void click() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//点击人事考勤
		webtest.click("xpath=//span[contains(.,'人事考勤')]");
		//点击人事档案
		webtest.click("id=menu_list_num84");
		//点击人员档案
		webtest.click("id=menu_list_num85");
	}
	
	//搜索框
	public void search(String content) {
		String searchText = "xpath=//input[@placeholder='关键字']";
		String searchbtn = "xpath=//button[contains(.,'搜索')]";
		search_action.search(searchText, searchbtn, content);
		//断言
		Assert.assertEquals(assert_action.assertSearch(content), true);
	}
	
	//状态搜索
	public void selectStatus(String status) {
		webtest.click("xpath=//select[@id='selstatus_1543456365049_8492']");
		//选择状态
		webtest.click("xpath=//option[contains(.,'"+status+"')]");
		//点击搜索
		webtest.click("xpath=//button[contains(.,'搜索')]");
		//断言
		Assert.assertEquals(assert_action.assertSearch(status), true);
		
	}
	
	//高级搜索
	public void advancedSearch(String name,String deptName,String status,String dateInStart,
			String dateInEnd,String dateFormalStart,String dateFormalEnd) {
		//点击下三角显示出"高级搜索"
		webtest.click("xpath=//button[starts-with(@id,'downbtn_')]");
		
		//点击"高级搜索"
		webtest.click("xpath=//li[contains(.,'高级搜索')]");
		//输入姓名"小乔"
		webtest.type("xpath=//input[@name='soufields_name']", name);
		//输入部门"财务部"
		webtest.type("xpath=//input[contains(@name,'deptname')]", deptName);
		//选择人员状态
		webtest.click("xpath=//select[@name='soufields_state']");
		webtest.click("xpath=//option[contains(.,'"+status+"')]");
		
		
		
		//选择入职日期1
		
		webtest.runJs(load_start+
				"document.getElementsByName('soufields_workdate_start')[0].removeAttribute('readonly')"+load_end);
		webtest.type("xpath=//input[contains(@name,'soufields_workdate_start')]", dateInStart);
		
		//选择入职日期2
		webtest.runJs(load_start+"document.getElementsByName('soufields_workdate_end')[0].removeAttribute('readonly')"+load_end);
		webtest.type("xpath=//input[contains(@name,'soufields_workdate_end')]", dateInEnd);
		
		
		//选择转正日期1
		
		webtest.runJs(load_start+"document.getElementsByName('soufields_positivedt_start')[0].removeAttribute('readonly')"+load_end);
		webtest.type("xpath=//input[contains(@name,'soufields_positivedt_start')]", dateFormalStart);
		
		//选择转正日期2
		webtest.runJs(load_start+"document.getElementsByName('soufields_positivedt_end')[0].removeAttribute('readonly')"+load_end);
		webtest.type("xpath=//input[contains(@name,'soufields_positivedt_end')]", dateFormalEnd);
		
		//点击搜索
		webtest.click("xpath=//a[contains(.,'搜索')]");

		//断言
		Assert.assertEquals(assert_action.assertSearch(name), true);
		Assert.assertEquals(assert_action.assertSearch(deptName), true);
		Assert.assertEquals(assert_action.assertSearch(status), true);
		Assert.assertEquals(assert_action.assertSearch(dateInStart), true);
		Assert.assertEquals(assert_action.assertSearch(dateInEnd), true);
		Assert.assertEquals(assert_action.assertSearch(dateFormalStart), true);
		Assert.assertEquals(assert_action.assertSearch(dateFormalEnd), true);
		
	}
	
	//更新数据
	public void flush() {
		webtest.click("xpath=//button[contains(.,'更新数据')]");
		String html = webtest.getHtmlSource();
		Assert.assertEquals(html.contains("更新中"), true);
	}
	
	//人员状态编辑(要修改的人员姓名，要修改的title以及修改后的值)
	//点击不了指定名字
	public void editStatus(String name,String key,String value) {
		webtest.click("xpath=//tr[contains(.,'"+name+"')]");
		webtest.click("xpath=//button[contains(.,'人员状态编辑')]");
		if(key.equals("名称")) {
			webtest.type("xpath=//input[contains(@name,'name')]", value);
		}
		else if(key.equals("人员状态")) {
			webtest.click("xpath=//select[contains(@name,'state')]");
			webtest.click("xpath=//option[contains(.,'"+value+"')]");
		}
		else if(key.equals("入职日期")) {
			webtest.runJs(load_start+"document.getElementsByName('workdate')[0].removeAttribute('readonly')"+load_end);
			webtest.type("xpath=//input[@name='workdate']", value);
		}
		else if(key.equals("离职日期")) {
			webtest.runJs(load_start+"document.getElementsByName('quitdt')[0].removeAttribute('readonly')"+load_end);
			webtest.type("xpath=//input[@name='quitdt']", value);
		}
		else if(key.equals("试用期到")) {
			webtest.runJs(load_start+"document.getElementsByName('syenddt')[0].removeAttribute('readonly')"+load_end);
			webtest.type("xpath=//input[@name='syenddt']", value);
		}
		else if(key.equals("转正日期")) {
			webtest.runJs(load_start+"document.getElementsByName('positivedt')[0].removeAttribute('readonly')"+load_end);
			webtest.type("xpath=//input[@name='positivedt']", value);
		}
		
		//点击确定
		webtest.click("xpath=//button[contains(.,' 确定')]");
		
		//搜索value值判断是否name与value同时存在
		this.search(name);
		
		//断言
		Assert.assertEquals(assert_action.assertSearch(value), true);
	}	
	
	//导出全部
	public void outputAll() {
		webtest.click("xpath=//button[@click='daochu']");
		webtest.click("xpath=//li[contains(.,'导出全部')]");
		Assert.assertEquals(webtest.ifContains("处理成功"), true);
	}
	
	
	//导出当前页
	public void outputCurrent() {
		webtest.click("xpath=//button[@click='daochu']");
		webtest.click("xpath=//li[contains(.,'导出当前页')]");
		Assert.assertEquals(webtest.ifContains("处理成功"), true);
	}
	
	
	//订阅此列表
	public void subscription() {
		webtest.click("xpath=//button[@click='daochu']");
		webtest.click("xpath=//li[contains(.,'订阅此列表')]");
		webtest.click("xpath=//button[contains(.,' 确定')]");
		webtest.click("xpath=//button[contains(.,' 确定')]");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = df.format(new Date());
		Assert.assertEquals(webtest.ifContains(time), true);
	}
	
	public void operation(String name) {
		String html = webtest.getHtmlSource();
		int start = html.indexOf("HTMLstart");
		int end = html.indexOf("HTMLend");
		String table = html.substring(start,end);
		int thead = table.indexOf("</thead");
		String tbody = table.substring(thead);
		//分割开每一个tr内的内容
		
		Pattern pt = Pattern.compile(tr);
		Matcher m = pt.matcher(tbody);
		List<String> trs = new ArrayList();
		int number=0;
		//获取要操作的行以及部门和职位
		while (m.find())
		{
			number++;
			String tri = m.group(1);
			if(tri.indexOf(name)!=-1) {
				int i = 0;
				Pattern ptn = Pattern.compile(td);
				Matcher mc = ptn.matcher(tri);
				while(mc.find()) {
					i++;
					String str = mc.group(1);
					if(i==3) {
						dept = str;
					}
					else if(i==4) {
						job = str;
						break;
					}
				}
				break;
			}
		}
		//点击操作
		webtest.click("xpath=//a[contains(@oi,'"+(number-1)+"')]");
	}
	//操作下的详情     //输入要查询的名字
	public void operation_details(String name) {
		//通过匹配名字获取序列号，定位到对应的操作下的详情
		a = true;
		//点击详情
		webtest.click("xpath=//li[contains(@temp,'0')]");
		//匹配打开的人员档案的姓名部门和职位是否与记录相符
		webtest.enterFrame("openinputiframe");
		//截取代码段
		String html2 = webtest.getHtmlSource();
		int content_left = html2.indexOf("ptitle");
		int content_right = html2.indexOf("</tbody");
		String message = html2.substring(content_left,content_right);
		Pattern ptn2 = Pattern.compile(tr);
		Matcher mc2 = ptn2.matcher(message);
		int number = 0;
		//验证打开的档案是否是要查看的档案
		while(mc2.find()) {
			String str = mc2.group(1);
			number++;
			if((number==1 && str.indexOf(name)==-1) || (number==2 && str.indexOf(dept)==-1) || (number==3 && str.indexOf(job)==-1)) {
				a = false;
				break;
			}
		}
		Assert.assertEquals(a, true);
	}
	
	//操作下的评论
	public void operation_comment(String name,String content) {
		webtest.click("xpath=//li[contains(.,'评论')]");
		//输入评论
		webtest.type("xpath=//textarea[contains(@class,'input')]", content);
		//点击确定
		webtest.click("xpath=//button[contains(.,' 确定')]");
		//断言 是否有处理成功字段
		Assert.assertEquals(webtest.ifContains("处理成功"), true);
	}
	
	//操作下的添加提醒设置
	public void operation_addReminder(String time) {
		webtest.click("xpath=//li[@temp='3']");
		//添加提醒时间
		webtest.enterFrame("openinputiframe");
		webtest.runJs("document.getElementsByName('rave_pinlvs2')[0].removeAttribute('readonly')");
		webtest.typeAndClear("xpath=//input[@name='rave_pinlvs2']", time);
		
		//点击保存
		webtest.click("xpath=//input[@value='保存(S)']");
		webtest.leaveFrame();

		//断言
		Assert.assertEquals(webtest.ifContains("编辑保存成功"), true);
		
	}
	
}
