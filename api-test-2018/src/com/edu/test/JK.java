package com.edu.test;

import java.io.IOException;


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

public class JK {

		public CookieStore login() throws IOException {
		String url = "http://study-perf.qa.netease.com/common/fgadmin/login";
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "20000000001");
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
		System.out.println("登录成功"+content);
		EntityUtils.consume(entity);
		response.close();
		httpclient.close();
		return cookie;
		}
		@Test
		public void newaddress() throws Exception {
			String subUrl = "/fgadmin/address/new";
			String result=null;
			CookieStore cookie = this.login();
			JSONObject json = new JSONObject();
			json.element("addressDetail","江南皮革厂");
			json.element("area","鹿城区");
			json.element("cellPhone","15222223333");
			json.element("city","温州市");
			json.element("id","");
			json.element("province","浙江省");
			json.element("receiverName","黄鹤");
			result = HttpDriver.doPost(subUrl,json,cookie);
			Checker check = new Checker(result);
			check.assertXpath("message", "success");
		}
		@Test
		public void newsameaddress() throws Exception {
			String subUrl = "/fgadmin/address/new";
			String result=null;
			CookieStore cookie = this.login();
			JSONObject json = new JSONObject();
			json.element("addressDetail","江南皮革厂");
			json.element("area","鹿城区");
			json.element("cellPhone","15222223333");
			json.element("city","温州市");
			json.element("id","");
			json.element("province","浙江省");
			json.element("receiverName","黄鹤");

			result = HttpDriver.doPost(subUrl,json,cookie);
			Checker check = new Checker(result);
			check.assertXpath("message", "success");
		}
		@Test
		public void KonDetail() throws Exception {
			String subUrl = "/fgadmin/address/new";
			String result=null;
			CookieStore cookie = this.login();
			JSONObject json = new JSONObject();
			json.element("addressDetail"," ");
			json.element("area","滨江区");
			json.element("cellPhone","15222223333");
			json.element("city","杭州市");
			json.element("id","");
			json.element("province","浙江省");
			json.element("receiverName","张三");
			result = HttpDriver.doPost(subUrl,json,cookie);
			Checker check = new Checker(result);
			check.assertXpath("message", "success");
		}
		@Test
		public void KonreceiverName() throws Exception {
			String subUrl = "/fgadmin/address/new";
			String result=null;
			CookieStore cookie = this.login();
			JSONObject json = new JSONObject();
			json.element("addressDetail","浙江大学");
			json.element("area","滨江区");
			json.element("cellPhone","15222223333");
			json.element("city","杭州市");
			json.element("id","");
			json.element("province","浙江省");
			json.element("receiverName","  ");
			result = HttpDriver.doPost(subUrl,json,cookie);
			Checker check = new Checker(result);
			check.assertXpath("message", "failure");
		}
		
}
