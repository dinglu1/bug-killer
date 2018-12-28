package com.edu.dataprovider;

import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MysqlDataProvider {
	

	
	public  Object[][] getTestDataByMysql(String sql) {
		String url = "jdbc:mysql://127.0.0.1:3306/foodfun";
		List<Object[]> records = new ArrayList<Object[]>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager
					.getConnection(url, "root", "123456");
			if (!conn.isClosed()) {
				System.out.println("已连接");
			}
			
			Statement stmt = conn.createStatement();
			
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
		
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int cols = rsMetaData.getColumnCount();
			System.out.println(cols);
			while (rs.next()) {
				String fields[] = new String[cols];

				int col=0;
				for (int i = 0; i < cols; i++) {
					fields[col] = rs.getString(i+1);//��ȡ��ǰ��ָ������
					col++;
				}
				records.add(fields);
			
			}
			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
		}
		return results;
	}


}
