package com.webtest.dataprovider;

import java.io.IOException;
import java.text.ParseException;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NSDataProvider {
	public String sheet = null;

	@DataProvider(name = "txt")
	public Object[][] getTxtData1() throws IOException {
		return new TxtDataProvider().getTxtUser("D://Java//practices//wangdonghui//data//Admin_Login.txt");
	}

	@DataProvider(name = "txt2")
	public Object[][] getTxtData2() throws IOException {
		return new TxtDataProvider().getTxtUser("D://Java//practices//wangdonghui//data//MovieReview_data.txt");
	}

	@DataProvider(name = "excel1")
	public Object[][] getExcelData1() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel("D://Java//practices//wangdonghui//data//TestData.xlsx",
				"Sheet1");
	}

	@DataProvider(name = "excel2")
	public Object[][] getExcelData2() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel("D://Java//practices//wangdonghui//data//TestData.xlsx",
				"Sheet2");
	}

	@DataProvider(name = "mysql")
	public Object[][] getMysqlDada() throws IOException {
		return new MysqlDataProvider().getTestDataByMysql("SELECT filmname, petname\r\n" + "FROM `mm_movie` ");
	}

}
