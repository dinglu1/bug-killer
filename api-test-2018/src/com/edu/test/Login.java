package com.edu.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
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

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

public class Login extends BaseTest{
	Checker check;
	String loginUrl="http://study-perf.qa.netease.com/common/fgadmin/login";
	String result=null;
	public String login(String phoneArea,String phoneNumber,String password) throws Exception{
		JSONObject user = new JSONObject();
		user.element("phoneArea", phoneArea);
		user.element("phoneNumber", phoneNumber);
		user.element("password", password);
		
		HttpPost post = new HttpPost(loginUrl);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity(user.toString());
		post.setEntity(data);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse respone = httpclient.execute(post);		
		HttpEntity entity = respone.getEntity();
		String result = EntityUtils.toString(entity, "utf-8");
		check = new Checker(result);
		return result;		
	}

}
