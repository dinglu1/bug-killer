package com.edu.core;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.edu.utils.ReadPro;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.Cookie;

import net.sf.json.JSONObject;
public class HttpUnitDriver {

	public static WebClient client = null;
	public static WebResponse response = null;
	public static String doGet(String url){
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url),HttpMethod.GET);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
	}
	
	public static String doGet(String url,Set<Cookie> cookie) {
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url),HttpMethod.GET);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Cookie> iterator = cookie.iterator();
		while(iterator.hasNext()) {
			client.getCookieManager().addCookie(iterator.next());
		}
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebResponse response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
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
	public static String doGet(String url,Map<String,Object> para) {
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url+"?"+mapToString(para)),HttpMethod.GET);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
		
	}
	
	public static String doGet(String url,String para){
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url+"?"+para),HttpMethod.GET);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
	}
	
	public static String doGet(String url,String para,Set<Cookie> cookie){
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url+"?"+para),HttpMethod.GET);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Cookie> iterator = cookie.iterator();
		while(iterator.hasNext()) {
			client.getCookieManager().addCookie(iterator.next());
		}
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebResponse response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
	}
	
	public static String doGet(String url,JSONObject json){
		String para = null;
		try {
			para = URLEncoder.encode(json.toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url+"?"+para),HttpMethod.GET);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
		
	}
	
	public static String doPost(String url,String para){
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url),HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.addRequestHeader("Content-type", "application/json");
		client.addRequestHeader("csrfToken", "csrfToken");
		request.setRequestBody(para);
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
	}
	
	public static String doPost(String url,String para,Set<Cookie> cookie){
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url),HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Cookie> iterator = cookie.iterator();
		while(iterator.hasNext()) {
			client.getCookieManager().addCookie(iterator.next());
		}
		client.addRequestHeader("Content-type", "application/json");
		request.setRequestBody(para);
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
	}
	
	
	public static String doPost(String url,JSONObject json) {
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url),HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.addRequestHeader("Content-type", "application/json");
		
		String para = null;
		try {
			para = URLEncoder.encode(json.toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setRequestBody(para);
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
	}
	
	public static String doPost(String url,JSONObject json,Set<Cookie> cookie){
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url),HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Cookie> iterator = cookie.iterator();
		while(iterator.hasNext()) {
			client.getCookieManager().addCookie(iterator.next());
		}
		client.addRequestHeader("Content-type", "application/json");
		String para = null;
		try {
			para = URLEncoder.encode(json.toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setRequestBody(para);
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
	}
	
	public static String doPostByForm(String url,Map<String,Object> para)  {
		client = new WebClient();
		WebRequest request = null;
		try {
			request = new WebRequest(new URL(ReadPro.getPropValue("BaseUrl")+url),HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.addRequestHeader("Content-type", "application/x-www-form-urlencoded");
		request.setRequestBody(mapToString(para));
		Page page = null;
		try {
			page = client.getPage(request);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = page.getWebResponse();
		String result = response.getContentAsString();
		client.close();
		return result;
		
	}
}
