package com.webtest.demo;

import org.testng.annotations.Test;

import com.webtest.dataprovider.NSDataProvider;

public class Para {
	
	@Test(dataProvider="txt",dataProviderClass=NSDataProvider.class)
	public void test1(String name,String password) {
		System.out.println(name+password);
		
		
		
		
	}

}
