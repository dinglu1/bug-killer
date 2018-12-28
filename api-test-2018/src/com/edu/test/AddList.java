package com.edu.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

public class AddList extends Login{
	Checker check;
	String subUrl="/fgadmin/address/list";
	String result=null;
	CookieStore cookie = null;
	public String addList() throws Exception {
		cookie = Common.getLoginCookie("200000000063", "netease123");
		result = HttpDriver.doGet(subUrl,cookie);
		check = new Checker(result);
		return result;
	}
	public String addListById(String id) throws IOException, Exception {
		cookie = Common.getLoginCookie("200000000063", "netease123");
		Map map = new HashMap();
		map.put("id", id);
		String result = HttpDriver.doGet(subUrl,map,cookie);
		check = new Checker(result);
		return result;
	}
	@Test(description="获取所有用户收货地址成功")
	public void TestAddList1() throws Exception
	{
		result = addList();
		check.verifyXpath("message", "success");
	}
	@Test(description="获取id=82317644的商品sku信息成功")
	public void TestAddList2() throws Exception
	{
		result = this.addListById("82317644");
		check.verifyXpath("message", "success");
	}
	@Test(description="获取id=2147483648的商品sku信息失败")
	public void TestAddList3() throws Exception
	{
		result = this.addListById("2147483648");
		check.verifyXpath("message", "success");
	}
	@Test(description="获取id=-2147483648的商品sku信息失败")
	public void TestAddList4() throws Exception
	{
		result = this.addListById("-2147483648");
		check.verifyXpath("message", "success");
	}
	@Test(description="获取id不存在的商品失败")
	public void TestAddList5() throws Exception
	{
		result = this.addListById("9999");
		check.verifyXpath("message", "success");
	}
	@Test(description="id类型不正确")
	public void TestAddList6() throws Exception
	{
		String id=URLEncoder.encode("\"1\"","utf-8");
		result = this.addListById(id);
		check.verifyXpath("message", "Failed");
	}
}
