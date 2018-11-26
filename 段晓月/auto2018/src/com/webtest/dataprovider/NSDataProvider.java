package com.webtest.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.webtest.utils.ReadProperties;

public class NSDataProvider {

	
	@DataProvider(name="txt")
	public  Object[][] getTxtData() throws IOException{
		return new  TxtDataProvider().getTxtUser(ReadProperties.getPropertyValue("TxT_Data_Path"));
	}
	
	@DataProvider(name="excel")
	public Object[][] getExcelDada() throws IOException{
		return new ExcelDataProvider().getTestDataByExcel(ReadProperties.getPropertyValue("Excel_Data_Path"),"Sheet1");
	}
	
	@DataProvider(name="mysql")
	public Object[][] getMysqlDada() throws IOException{
		String select = ReadProperties.getPropertyValue("select");
		String from = ReadProperties.getPropertyValue("from");
		return new MysqlDataProvider().getTestDataByMysql("SELECT "+select+"\r\n" + 
				"FROM `"+from+"` ");
	}
	
}
