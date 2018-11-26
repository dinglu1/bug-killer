package com.webtest.demo;

import org.testng.annotations.Test;

import com.webtest.core.Sendmail;

public class Sendmail_test {

	
	@Test
	public void test() throws Exception
	{
		Sendmail sendmail = new Sendmail();
		sendmail.Sendmail("m15227177568_2@163.com","490709869@qq.com");
	}
	
}
