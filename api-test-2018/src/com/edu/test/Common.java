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

import com.edu.utils.ReadPro;

import net.sf.json.JSONObject;

public class Common {
	public static CookieStore getLoginCookie(String u_name,String u_pwd) throws IOException, Exception {
		String loginurl="/common/fgadmin/login";
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", u_name);
		user.element("password", u_pwd);
		
		//注意要在创建HTTPClient连接前创建cookie,不然最后cookie获取不到值
		RequestConfig gConfig = RequestConfig.custom().
				setCookieSpec(CookieSpecs.STANDARD).build();
		CookieStore cookie =new BasicCookieStore();
		CloseableHttpClient  httpClient =HttpClients.custom().
				setDefaultRequestConfig(gConfig).
				setDefaultCookieStore(cookie).build();
		
		HttpPost post = new HttpPost(ReadPro.getPropValue("BaseUrl")+loginurl);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity(user.toString(),"utf-8");
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return cookie;
		
		
	}


}
