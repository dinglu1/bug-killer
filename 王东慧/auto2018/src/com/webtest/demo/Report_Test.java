package com.webtest.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.webtest.core.SendReport;

public class Report_Test {
	/**
	 * @author wangdonghui
	 */
	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String nowDateTime=sdf.format(new Date());
		String srcDir ="D:\\Java\\practices\\wangdonghui\\test-output\\html";
		String filename = "D:\\Java\\practices\\wangdonghui\\test-output\\html"+nowDateTime+".zip";
		try {
			SendReport.sendReport(srcDir, filename);
		} catch (Exception e) {
			System.out.println("∑¢ÀÕ” º˛ ß∞‹");
			e.printStackTrace();
		}
	}
}
