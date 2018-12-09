package com.webtest.personnel.action;

import com.webtest.core.BaseTest;
import com.webtest.core.WebDriverEngine;

public class Search_Action {

	//人事档案下的人员档案页面的搜索框测试
	WebDriverEngine webtest = null;
	public Search_Action(WebDriverEngine webtest){
		this.webtest = webtest;
	}
	
	public void search(String SearchText,String SearchButton,String content) {
		
		//点击搜索框
		webtest.click(SearchText);
		//向搜索框中输入内容
		webtest.type(SearchText, content);
		//点击搜索
		webtest.click(SearchButton);
		
	}
	
	
	
}
