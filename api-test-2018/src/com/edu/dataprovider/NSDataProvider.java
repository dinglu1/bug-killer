package com.edu.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NSDataProvider {

	
	@DataProvider(name="txt")
	public  Object[][] getTxtData() throws IOException{
		return new  TxtDataProvider().getTxtUser("data/user.txt");
	}
	@DataProvider(name="movie")
	public  Object[][] getMovieData() throws IOException{
		return new  ExcelDataProvider().getTestDataByExcel("data/movie.xlxs","Sheet1");
	}
//	@Test(dataProvider="txt")
//	public void getData(String a,String b) {
//		System.out.println(a+" "+b);
//		
//	}

//	@DataProvider(name="user")
//	public Object[][] getExcelDada() throws IOException{
//		return new ExcelDataProvider().getTestDataByExcel("data/user.xlsx","Sheet1");
//	}
	
	@DataProvider(name="submitTestCase")
	public Object[][] getExcelDada() throws IOException{
		return new ExcelDataProvider().getTestDataByExcel("D:\\drive\\a.xlsx","Sheet1");
	}
	
	
	@DataProvider(name="mysql")
	public Object[][] getMysqlDada() throws IOException{
		return new MysqlDataProvider().getTestDataByMysql("SELECT filmname, petname\r\n" + 
				"FROM `mm_movie` ");
	}
	
}
