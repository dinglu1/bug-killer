package com.webtest.utils;

import java.io.FileNotFoundException;

public class JavaMailtest {
	public static void main(String [] args) throws FileNotFoundException {
		JavaMailSend jm =new JavaMailSend("982339661@qq.com", "739242114@qq.com");
		jm.send();
	}

}
