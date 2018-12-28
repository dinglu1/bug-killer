package com.edu.test;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QGDelete {
	Checker check;
	String AddressDeleteURL = "/fgadmin/address/delete";
	String result = null;
	
	String AddressListURL = "/fgadmin/address/list";
	String message = null;
	
	String name="200000000061";
	String password="netease123";
	
	public String Getid() throws IOException, Exception {
		CookieStore cookie = Common.getLoginCookie(name, password);
		message = HttpDriver.doGet(AddressListURL,cookie);
		JSONObject json = JSONObject.fromObject(message); 
		JSONObject result = json.getJSONObject("result");
		JSONArray array = result.getJSONArray("list");
		JSONObject list = array.getJSONObject(0);
		System.out.println(list.getString("id"));		
		return list.getString("id");
	}
	
	public String AddressDelete(String id) throws IOException, Exception {
		CookieStore cookie = Common.getLoginCookie(name, password);
		JSONObject json = new JSONObject();
		json.element("id", id);
		result = HttpDriver.doPost(AddressDeleteURL, json,cookie);
		System.out.println(new Exception().getStackTrace()[1].getMethodName());
		System.out.println(result);
		return result;
	}
	
	//删除已有地址ַ
	@Test
	public void AddressDeleteSuccess() throws Exception {	
		result = AddressDelete(this.Getid());
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}

	//删除不存在地址
	@Test
	public void AddressDeleteFail1() throws Exception {
		
		result = AddressDelete("");
		check = new Checker(result);
		check.verifyXpath("message", "may not be null");
	}

	//删除id=2147483648的地址(超过int最大取值范围)
	@Test
	public void AddressDeleteFail2() throws IOException, Exception {
		result = AddressDelete("2147483648");
		check = new Checker(result);
		check.verifyXpath("message", "请求失败");
	}
	
	@Test 
	public void AddressDeleteFail3() throws IOException, Exception {
		result = AddressDelete("\"1\"");
		check = new Checker(result);
		check.verifyXpath("message", "请求失败");
	}
	
}
