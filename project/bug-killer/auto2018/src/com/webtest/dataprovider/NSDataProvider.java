package com.webtest.dataprovider;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import com.webtest.utils.ReadProperties;

public class NSDataProvider {
	public String sheet = null;

	@DataProvider(name = "txt1")
	public Object[][] getTxtData1() throws IOException {
		return new TxtDataProvider().getTxtUser(ReadProperties.getPropertyValue("text1_path"));
	}
	
	@DataProvider(name = "txt2")
	public Object[][] getTxtData2() throws IOException {
		return new TxtDataProvider().getTxtUser(ReadProperties.getPropertyValue("text2_path"));
	}
	
	@DataProvider(name = "txt3")
	public Object[][] getTxtData3() throws IOException {
		return new TxtDataProvider().getTxtUser(ReadProperties.getPropertyValue("text3_path"));
	}

	@DataProvider(name = "excel1")
	public Object[][] getExcelData1() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel(ReadProperties.getPropertyValue("excel1_path"),
				"Sheet1");
	}

	@DataProvider(name = "mysql")
	public Object[][] getMysqlDada() throws IOException {
		return new MysqlDataProvider().getTestDataByMysql("SELECT filmname, petname\r\n" + "FROM `mm_movie` ");
	}
	
	@DataProvider(name = "custom_id")
	public Object[][] getTxtData5() throws IOException {
		return new TxtDataProvider().getTxtUser(ReadProperties.getPropertyValue("txt2_path"));
	}

	@DataProvider(name = "addcustomer")
	public Object[][] getExcelData7() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel(ReadProperties.getPropertyValue("excel_path"),
				"AddCustomer");
	}
	
	@DataProvider(name = "addsell")
	public Object[][] getExcelData2() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel(ReadProperties.getPropertyValue("excel_path"),
				"AddSell");
	}
	
	@DataProvider(name = "addagreement")
	public Object[][] getExcelData3() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel(ReadProperties.getPropertyValue("excel_path"),
				"AddAgreement");
	}
	
	@DataProvider(name = "addrecipt")
	public Object[][] getExcelData4() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel(ReadProperties.getPropertyValue("excel_path"),
				"AddRecipt");
	}
	
	@DataProvider(name = "addReminSetting")
	public Object[][] getExcelData6() throws IOException {
		return new ExcelDataProvider().getTestDataByExcel(ReadProperties.getPropertyValue("excel_path"),
				"AddReminSetting");
	}


}
