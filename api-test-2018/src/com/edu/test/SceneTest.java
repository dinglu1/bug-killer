package com.edu.test;

import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

public class SceneTest {
	public CookieStore login() throws Exception {
		String url = "http://study-perf.qa.netease.com/common/fgadmin/login";
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "200000000034");
		user.element("password", "netease123");
		
		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		CookieStore cookie = new BasicCookieStore();
		String para = user.toString();
		CloseableHttpClient httpclient = HttpClients.custom().
				setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data = new StringEntity(user.toString());
		
		post.setEntity(data);
		CloseableHttpResponse response = httpclient.execute(post);
		
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity,"utf-8");
		System.out.println("LoginSucess"+content);
		EntityUtils.consume(entity);
		response.close();
		httpclient.close();
		return cookie;
	}
	
	@Test
	public void testSkuList() throws Exception {
		String subUrl = "/common/skuList/";
		String result=null;
		CookieStore cookie = this.login();
		result = HttpDriver.doGet(subUrl,cookie);
		Checker check = new Checker(result);
		check.assertXpath("message", "success");
	}
	
	@Test
	public void testAddress() throws Exception {
		String subUrl = "/fgadmin/address/list";
		String result=null;
		CookieStore cookie = this.login();
		result = HttpDriver.doGet(subUrl,cookie);
		Checker check = new Checker(result);
		check.assertXpath("message", "success");
	}
	@Test
	public void testTransport() throws Exception {
		String subUrl = "/common/getTransportFee?id=1&addressDetail=浙江省_杭州市_滨江区";
		String result=null;
		CookieStore cookie = this.login();
		result = HttpDriver.doGet(subUrl,cookie);
		Checker check = new Checker(result);
		check.assertXpath("message", "success");
	}
	@Test
	public void testSubmit() throws Exception {
		String subUrl = "/fgadmin/orders/submit";
		String result=null;
		CookieStore cookie = this.login();
		JSONObject json = new JSONObject();

		json.element("skuIds","2,3");
		json.element("skuNumbers","1,1");
		json.element("stockIds","74966312,74966313");
		json.element("receiverName","张三");
		json.element("cellPhone","12615813537");
		json.element("addressDetail","1 栋 3 单元");
		json.element("province","浙江省");
		json.element("city","杭州市");
		json.element("area","滨江区");
		json.element("voiceStatus",0);
		json.element("needInvoice",0);
		json.element("invoiceHead","");
		json.element("transportFee",0);
		json.element("logisticsCompanyId",1);
		json.element("accessSource","noSource");
		json.element("accessDevice",0 );
		result = HttpDriver.doPost(subUrl,json,cookie);
		Checker check = new Checker(result);
		check.assertXpath("message", "success");
	}
}
