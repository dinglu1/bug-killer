package com.edu.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
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

import com.edu.core.HttpDriver;
import com.edu.utils.ReadPro;

import net.sf.json.JSONObject;


public class HttpClientCommon {
	public static CookieStore getLoginCookie(String u_name,String u_pwd) {
		String loginurl=ReadPro.getPropValue("BaseUrl")+"/common/fgadmin/login";
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber",u_name );
		user.element("password", u_pwd);
		
		RequestConfig gConfig = RequestConfig.custom().
				setCookieSpec(CookieSpecs.STANDARD).build();
		CookieStore cookie =new BasicCookieStore();
		CloseableHttpClient  httpClient =HttpClients.custom().
				setDefaultRequestConfig(gConfig).
				setDefaultCookieStore(cookie).build();
		
		HttpPost post = new HttpPost(loginurl);
		post.addHeader("Content-Type","application/json");
		HttpEntity data = null;
		try {
			data = new StringEntity(user.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.setEntity(data);
		CloseableHttpResponse respone = null;
		try {
			respone = httpClient.execute(post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpEntity entity = respone.getEntity();
		try {
			String content = EntityUtils.toString(entity, "utf-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			respone.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cookie;
		
	}


}
