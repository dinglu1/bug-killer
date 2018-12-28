package com.edu.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

public class SkuListTest {
	Checker check;
	String subUrl = "/common/skuList/";
	String result=null;
	public String skulist() throws Exception {
		result = HttpDriver.doGet(subUrl);
		check = new Checker(result);
		System.out.println(result);
		return result;
	}
	
	public String skuListById(String goodsId) {
		Map map = new HashMap();
		map.put("goodsId",goodsId);
		String result = HttpDriver.doGet(subUrl,map);
		check = new Checker(result);
		System.out.println(result);
		return result;
		
	}
	//get sku success
	@Test
	public void testSkuList1() throws Exception {
		result = this.skulist();
		check.verifyTextPresent("message");
	}
	// get goodsId=1 success
	@Test
	public void testSkuList2() throws Exception {
		result = this.skuListById("1");
		check.verifyXpath("message", "success");
	}

	//get goodsId not exits
	@Test
	public void testSkuLlist3() throws Exception {
		result = this.skuListById("23");
		check.verifyTextPresent("ID");
	}
	
	//goodsId type is wrong
	@Test
	public void testSkuLlist4() throws Exception {
		String goodsId=URLEncoder.encode("\"1\"", "UTF-8");
		result=this.skuListById(goodsId);
		check.verifyXpath("message", "Failed");
	}
	//header type is wrong
		@Test
		public void testSkuList6() throws Exception {
			String headerC="Content-Type";
			String headerA="application/javaScript";
			result = HttpDriver.doGet(subUrl,headerC,headerA);
			System.out.println(result);
			check=new Checker(result);
			check.verifyTextPresent("header");
		}
	
}
