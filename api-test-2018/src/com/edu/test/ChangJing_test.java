package com.edu.test;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Checker;
import com.edu.utils.ReadPro;

import net.sf.json.JSONObject;

public class ChangJing_test {
	/**
	 * @author wangdonghui 
	 * 场景4 ：完整下单流程（已登录，有收货地址）
	 */
	
	String skulistUrl = "/common/skuList";
	String addresslistUrl = "/fgadmin/address/list";
	String getTransportFeeUrl = "/common/getTransportFee";
	String submitUrl = "/fgadmin/orders/submit";

	CloseableHttpClient httpClient = null;

	String name="200000000061";
	String password="netease123";
	
	Checker check;
	
	@Test
	public void changjing4() throws IOException, Exception {
		// 登录
		CookieStore cookie = Common.getLoginCookie(name, password);
		
		//创建httpclient
		RequestConfig gconfig  = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(gconfig).setDefaultCookieStore(cookie).build();
		
		//查询收货地址		
		HttpGet addresslist_get = new HttpGet(ReadPro.getPropValue("BaseUrl")+addresslistUrl);
		addresslist_get.addHeader("Content-Type","application/json");
		CloseableHttpResponse addresslist_response = httpClient.execute(addresslist_get);
		HttpEntity addresslist_entity = addresslist_response.getEntity();		
		String content1 = EntityUtils.toString(addresslist_entity,"utf-8");
		System.out.println("查询收货地址  "+content1);
		check = new Checker(content1);
		check.verifyXpath("message","success");
		EntityUtils.consume(addresslist_entity);
		addresslist_response.close();
		
		//计算运费
		String data = "id=1&addressDetail=江苏省_南京市_鼓楼区";
		HttpGet transportfee_get = new HttpGet(ReadPro.getPropValue("BaseUrl")+getTransportFeeUrl+ "?" +data);
		System.out.println(ReadPro.getPropValue("BaseUrl")+getTransportFeeUrl+ "?" +data);
		transportfee_get.addHeader("Content-Type","application/json");
		CloseableHttpResponse transportfee_response = httpClient.execute(transportfee_get);
		HttpEntity transportfee_entity = transportfee_response.getEntity();	
		String content2 = EntityUtils.toString(transportfee_entity,"utf-8");
		System.out.println("计算运费 "+content2);
		check = new Checker(content2);
		check.verifyXpath("message","success");
		EntityUtils.consume(transportfee_entity);
		transportfee_response.close();
		
		//skulist接口
		JSONObject json = new JSONObject();
		json.element("goodsId", "1");
		String data1 = URLEncoder.encode(json.toString(),"utf-8");
		HttpGet skulist_get = new HttpGet(ReadPro.getPropValue("BaseUrl")+skulistUrl+ "?" +data1);
		System.out.println(ReadPro.getPropValue("BaseUrl")+skulistUrl+ "?" +data1);
		transportfee_get.addHeader("Content-Type","application/json");
		CloseableHttpResponse skulist_response = httpClient.execute(skulist_get);
		HttpEntity skulist_entity = skulist_response.getEntity();	
		String content4 = EntityUtils.toString(skulist_entity,"utf-8");
		System.out.println("skulist接口 "+content4);
		check = new Checker(content4);
		check.verifyXpath("message","success");
		EntityUtils.consume(skulist_entity);
		skulist_response.close();
		
		//提交订单
		JSONObject json2 = new JSONObject();
		json2.element("skuIds", "2,3");
		json2.element("skuNumbers", "1,1");
		json2.element("stockIds", "74966312,74966313");
		json2.element("receiverName", "李四");
		json2.element("cellPhone", "20000000004");
		json2.element("addressDetail", "南京大学");
		json2.element("province", "江苏省");
		json2.element("city", "南京市");
		json2.element("area", "鼓楼区");
		json2.element("voiceStatus", 0);
		json2.element("needInvoice", 0);
		json2.element("invoiceHead", "");
		json2.element("transportFee", 0);
		json2.element("logisticsCompanyId", 1);
		json2.element("accessSource", "noSource");
		json2.element("accessDevice", 0);
		HttpPost post = new HttpPost(ReadPro.getPropValue("BaseUrl")+submitUrl);
		post.addHeader("Content-Type","application/json");
		post.addHeader("csrfToken","csrfToken");
		HttpEntity data2 = new StringEntity(json2.toString(),"utf-8");
		post.setEntity(data2);
		CloseableHttpResponse submit_response = httpClient.execute(post);
		HttpEntity submit_entity = submit_response.getEntity();
		String content3 =  EntityUtils.toString(submit_entity, "utf-8");
		System.out.println("提交订单 "+content3);
		check = new Checker(content3);
		check.verifyXpath("message","success");
		EntityUtils.consume(submit_entity);
		submit_response.close();
		
		//释放连接
		httpClient.close();

	}
}
