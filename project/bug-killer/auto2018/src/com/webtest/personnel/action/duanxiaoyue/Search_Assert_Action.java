package com.webtest.personnel.action.duanxiaoyue;

import com.webtest.core.WebDriverEngine;

public class Search_Assert_Action {

	WebDriverEngine webtest = null;
	public Search_Assert_Action(WebDriverEngine webtest) {
		this.webtest = webtest;
	}
	//遍历表格断言查询结果
	public boolean assertSearch(String content) {
		
		//获取页面源代码
		String html = webtest.getHtmlSource();
		int HTMLstart = html.indexOf("HTMLstart");
		//截掉菜单的td
		String HTML = html.substring(HTMLstart);
		int table_left = HTML.indexOf("thead");
		int table_right = HTML.lastIndexOf("HTMLend");
		
		String thread = HTML.substring(table_left, table_right);
		int tbody_left = thread.indexOf("tbody");
		int tbody_right = thread.lastIndexOf("tbody");
		//获取搜索后的行代码
		String tbody = thread.substring(tbody_left,tbody_right);
		//切割获取每一行的信息
		String[] trs = tbody.split("<tr");
		boolean isContent = true;
		//遍历每一行，如果找不到，则返回false
		for(int i=1;i<trs.length;i++) {
			if(trs[i].indexOf(content)==-1) {
				isContent = false;
				break;
			}
		}
//		System.out.println(isContent);
		return isContent;
	}
	
public boolean assertSearch(String name,String value) {
		
		//获取页面源代码
		String html = webtest.getHtmlSource();
		int HTMLstart = html.indexOf("HTMLstart");
		//截掉菜单的td
		String HTML = html.substring(HTMLstart);
		int table_left = HTML.indexOf("thead");
		int table_right = HTML.lastIndexOf("HTMLend");
		
		String thread = HTML.substring(table_left, table_right);
		int tbody_left = thread.indexOf("tbody");
		int tbody_right = thread.lastIndexOf("tbody");
		//获取搜索后的行代码
		String tbody = thread.substring(tbody_left,tbody_right);
		//切割获取每一行的信息
		String[] trs = tbody.split("<tr");
		boolean isContent = false;
		//遍历每一行，如果找不到，则返回false
		for(int i=1;i<trs.length;i++) {
			if(trs[i].indexOf(name)!=-1 && trs[i].indexOf(value)!=-1) {
				isContent = true;
				break;
			}
		}
		return isContent;
	}
	
}
