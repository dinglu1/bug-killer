package com.webtest.customer.action.wangdonghui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.webtest.core.WebDriverEngine;

public class Search_action {
	/**
	 * @author wangdonghui 匹配
	 */
	public WebDriverEngine webtest = null;

	public Search_action(WebDriverEngine webtest) {
		this.webtest = webtest;
	}

	// 截取tr
	public Matcher splittr() {
		// 得到源代码
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String html = webtest.getHtmlSource();
		// 截取代码
		int start = html.indexOf("<tbody id=\"tbody_");
		int end = html.indexOf("<!--HTMLend-->");
		String str = html.substring(start, end);
		// 截取tr
		String tr = "<tr[^>]*?>([\\s\\S]*?)</tr>";
		Pattern pt = Pattern.compile(tr);
		Matcher m = pt.matcher(str);
		return m;
	}
	
	//截取所有td
	public Matcher splittd() {
		Matcher m2 = null;
		Matcher m = this.splittr();
		while (m.find()) {
			System.out.println(m.group());
			String tr = "<td( .*?)?>.*?</td>";
			Pattern pt = Pattern.compile(tr);
			m2 = pt.matcher(m.group());		
		}
		return m2;
	}
	
	//共有多少行
	public int len_tr() {
		int len_tr=0;
		Matcher m = this.splittr();
		while (m.find()) {
			len_tr++;
		}
		return len_tr;
	}
	
	//共有多少列
	public int len_td() {
		int len_td = 0;
		// 遍历一下共有多少列
		Matcher m = this.splittr();
		while (m.find()) {
			m.group();
			String tr = "<td( .*?)?>.*?</td>";
			Pattern pt = Pattern.compile(tr);
			Matcher m2 = pt.matcher(m.group());
			while (m2.find()) {
				len_td++;
			}
			break;
		}	
		return len_td;
	}
	

	// 查找搜索后的每一行是否都含有text
	public boolean search(String text) {
		Matcher m = this.splittr();
		while (m.find()) {
			System.out.println(m.group());
			// 判断是否含有text
			if (m.group().contains(text))
				;
			else
				return false;
		}
		return true;
	}

	// 查找搜索后的每一行指定的列中是否都含有text
	public boolean search(String text, int td) {
		Matcher m = this.splittr();
		while (m.find()) {
			System.out.println(m.group());
			String tr = "<td( .*?)?>.*?</td>";
			Pattern pt = Pattern.compile(tr);
			Matcher m2 = pt.matcher(m.group());
			int i = 0;
			while (m2.find()) {
				i++;
				if (td == i) {
					// 判断是否含有text
					if (m2.group().contains(text)) {
						System.out.println(m2.group());
					}
					else {
						return false;
					}
				}					
			}
		}
		return true;
	}
	
	// 找到客户编号是num 的tr,并返回xpath的值
	// 涉及操作按钮时用
	public String troi(String num) {		
		int length = this.len_td();

		int i = Integer.parseInt(num);
		String xpath = ".//*[starts-with(@id,\"tbody_\")]/tr[" + i + "]/td[" + length + "]/a";
		return xpath;
	}

}
