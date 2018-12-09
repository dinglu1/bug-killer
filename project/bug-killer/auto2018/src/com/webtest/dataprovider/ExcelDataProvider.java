package com.webtest.dataprovider;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {


	
	public Object[][] getTestDataByExcel(String fileName, String sheetName)
			throws IOException{
		File file = new File(fileName);
		FileInputStream inputstream = new FileInputStream(file);
		Workbook wbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		System.out.println(fileExtensionName);
		if (fileExtensionName.equals(".xlsx")) {
			wbook = new XSSFWorkbook(inputstream);
		
		} else if (fileExtensionName.equals(".xls")) {
			wbook = new HSSFWorkbook(inputstream);
		}
		Sheet sheet = wbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		List<Object[]> records = new ArrayList<Object[]>();

		for (int i = 1; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);
			String fields[] = new String[row.getLastCellNum()];
			for (int j = 0; j < row.getLastCellNum(); j++) {
		
				//row.getCell(j).setCellType(CellType.STRING);		
				fields[j] = row.getCell(j).getStringCellValue();
			}
			records.add(fields);
		}
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
		}
		return results;
	}
	

}
