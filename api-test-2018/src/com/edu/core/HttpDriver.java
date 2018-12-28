package com.edu.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.edu.utils.ReadPro;

//import bsh.This;
import net.sf.json.JSONObject;

public class HttpDriver {
	static CloseableHttpClient httpClient = null;
	static CloseableHttpResponse respone = null;

	public static String doGet(String url) {
		httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(ReadPro.getPropValue("BaseUrl")+url);
		get.addHeader("Content-Type", "application/json");
		get.addHeader("csrfToken","csrfToken");
		try {
			respone = httpClient.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = respone.getEntity();
		String content = null;
		try {
			content = EntityUtils.toString(entity, "utf-8");
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
		return content;
	}

	
	public static String doGet(String url, String headerC,String headerA) throws Exception {
		httpClient = HttpClients.createDefault();
		String BaseUrl = ReadPro.getPropValue("BaseUrl");
		HttpGet get = new HttpGet(BaseUrl+url);
		get.addHeader(headerC, headerA);
		respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return content;
	}
	
	public static String doGet(String url, JSONObject data) {
		String para = null;
		try {
			para = URLEncoder.encode(data.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(ReadPro.getPropValue("BaseUrl")+url + "?" + para);
		get.addHeader("Content-Type", "application/json");
		get.addHeader("csrfToken","csrfToken");
		try {
			respone = httpClient.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = respone.getEntity();
		String content = null;
		try {
			content = EntityUtils.toString(entity, "utf-8");
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
		return content;
	}

	public static String doGet(String url, String para) throws Exception {
		httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(ReadPro.getPropValue("BaseUrl")+url + "?" + para);
		get.addHeader("Content-Type", "application/json");
		get.addHeader("csrfToken","csrfToken");
		respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return content;
	}


	public static String mapToString(Map<String, Object> para) {
		
				StringBuilder sBuilder = new StringBuilder();
				String content = null;
				int size = para.size();
				for (Entry<String, Object> entry : para.entrySet()) {
					sBuilder.append(entry.getKey() + "=" + entry.getValue());
					size--;
					if (size >= 1) {
						sBuilder.append("&");
					}

				}
				return sBuilder.toString();
	}
	public static String doGet(String url, Map<String, Object> para) {
		String content=null;
		httpClient = HttpClients.createDefault();

		HttpGet get = new HttpGet(ReadPro.getPropValue("BaseUrl")+url + "?" + mapToString(para));
		get.addHeader("Content-Type", "application/json");
		get.addHeader("csrfToken","csrfToken");
		try {
			respone = httpClient.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = respone.getEntity();

		try {
			content = EntityUtils.toString(entity, "utf-8");
		} catch (ParseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			respone.close();
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}
	
	public static String doGet(String url,CookieStore cookie) {
		String content=null;
		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();

		HttpGet get = new HttpGet(ReadPro.getPropValue("BaseUrl")+url);
		get.addHeader("Content-Type", "application/json");
		get.addHeader("csrfToken","csrfToken");
		try {
			respone = httpClient.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = respone.getEntity();

		try {
			content = EntityUtils.toString(entity, "utf-8");
		} catch (ParseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			respone.close();
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}
	
	
	public static String doGet(String url, Map<String, Object> para,CookieStore cookie) {
		String content=null;
		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();

		HttpGet get = new HttpGet(ReadPro.getPropValue("BaseUrl")+url + "?" + mapToString(para));
		get.addHeader("Content-Type", "application/json");
		get.addHeader("csrfToken","csrfToken");
		try {
			respone = httpClient.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = respone.getEntity();

		try {
			content = EntityUtils.toString(entity, "utf-8");
		} catch (ParseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			respone.close();
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}
	public static String doGet(String url, String para,CookieStore cookie) {
		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();
		HttpGet get = new HttpGet(ReadPro.getPropValue("BaseUrl")+url + "?" + para);
		get.addHeader("Content-Type", "application/json");
		get.addHeader("csrfToken","csrfToken");
		try {
			respone = httpClient.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = respone.getEntity();
		String content = null;
		try {
			content = EntityUtils.toString(entity, "utf-8");
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
		return content;
	}

	public static String doPost(String url, JSONObject para) {
		httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(ReadPro.getPropValue("BaseUrl")+url);
		post.addHeader("Content-Type", "application/json");
		post.addHeader("csrfToken","csrfToken");
		HttpEntity data;
		String content = null;
		try {
			data = new StringEntity(para.toString());

			post.setEntity(data);
			respone = httpClient.execute(post);

			HttpEntity entity = respone.getEntity();
			content = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			respone.close();
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;

	}
	
	public static String doPostByForm(String url, Map<String,Object> para) {
		httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(ReadPro.getPropValue("BaseUrl")+url);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		post.addHeader("csrfToken","csrfToken");
		HttpEntity data;
		String content = null;
		try {
			String s=mapToString(para);
			System.out.println(s);
			data = new StringEntity(s);

			post.setEntity(data);
			respone = httpClient.execute(post);

			HttpEntity entity = respone.getEntity();
			content = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			respone.close();
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;

	}
	
	public static String doPostByForm(String url, String para) {
		httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(ReadPro.getPropValue("BaseUrl")+url);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		post.addHeader("csrfToken","csrfToken");
		HttpEntity data;
		String content = null;
		try {
			data = new StringEntity(para);

			post.setEntity(data);
			respone = httpClient.execute(post);

			HttpEntity entity = respone.getEntity();
			content = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			respone.close();
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;

	}

	public static String doPost(String url, JSONObject para, CookieStore cookie) {
		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();
		HttpPost post = new HttpPost(ReadPro.getPropValue("BaseUrl")+url);
		post.addHeader("Content-Type", "application/json");
		post.addHeader("csrfToken","csrfToken");
		HttpEntity data = new StringEntity(para.toString(),"utf-8");
		post.setEntity(data);
		CloseableHttpResponse respone = null;
		try {
			respone = httpClient.execute(post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpEntity entity = respone.getEntity();
		String content = null;
		try {
			content = EntityUtils.toString(entity, "utf-8");
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
		return content;

	}

}
