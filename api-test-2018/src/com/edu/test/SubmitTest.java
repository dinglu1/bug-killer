package com.edu.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class SubmitTest {
	CloseableHttpClient httpClient =null;
	@BeforeClass
	public void startUp() {
		httpClient= HttpClients.createDefault();
	}
	@AfterClass
	public void tearDown() throws Exception {
		httpClient.close();
	}
	
	@Test(priority=0)
	public void loginSuccess() throws Exception {
		JSONObject success_user = new JSONObject();
		success_user.element("phoneArea", "86");
		success_user.element("phoneNumber", "20000000000");
		success_user.element("password", "netease123");
		
		
		String url="http://study-perf.qa.netease.com/common/fgadmin/login";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity(success_user.toString());
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("login"+content);
		EntityUtils.consume(entity);
		respone.close();
	
	}
	public String address;
	public float fee;
	
	@Test(priority=1)
	public void submit() throws Exception {
		
		String url="http://study-perf.qa.netease.com/fgadmin/orders/submit";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity("{\"skuIds\":\"2,3\",\"skuNumbers\":\"1,1\",\"stockIds\":\"74966312,74966313\",\"receiverName\":\"传真机\",\"cellPhone\":\"18877779999\"\r\n" + 
				",\"addressDetail\":\"浙江大学\",\"province\":\"浙江省\",\"city\":\"杭州市\",\"area\":\"滨江区\",\"voiceStatus\":0,\"needInvoice\"\r\n" + 
				":0,\"invoiceHead\":\"\",\"transportFee\":0,\"logisticsCompanyId\":1,\"accessSource\":\"noSource\",\"accessDevice\"\r\n" + 
				":0}");
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("submit"+content);
		EntityUtils.consume(entity);
		respone.close();
	
	}

	@Test(priority=1)
	public void submit1() throws Exception {
		
		String url="http://study-perf.qa.netease.com/fgadmin/orders/submit";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity("{\"skuIds\":\"2,3\",\"skuNumbers\":\"1,1\",\"stockIds\":\"74966312,74966313\",\"receiverName\":\"传真机\",\"cellPhone\":\"gg\"\r\n" + 
				",\"addressDetail\":\"浙江大学\",\"province\":\"浙江省\",\"city\":\"杭州市\",\"area\":\"滨江区\",\"voiceStatus\":0,\"needInvoice\"\r\n" + 
				":0,\"invoiceHead\":\"\",\"transportFee\":0,\"logisticsCompanyId\":1,\"accessSource\":\"noSource\",\"accessDevice\"\r\n" + 
				":0}");
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("submit"+content);
		EntityUtils.consume(entity);
		respone.close();
		
	
	}

	@Test(priority=1)
	public void submit11() throws Exception {
		
		String url="http://study-perf.qa.netease.com/fgadmin/orders/submit";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity("{\"skuIds\":\"2,3\",\"skuNumbers\":\"1,1\",\"stockIds\":\"74966312,74966313\",\"receiverName\":\"传真机\",\"cellPhone\":\"777777777777777777777777777\"\r\n" + 
				",\"addressDetail\":\"浙江大学\",\"province\":\"浙江省\",\"city\":\"杭州市\",\"area\":\"滨江区\",\"voiceStatus\":0,\"needInvoice\"\r\n" + 
				":0,\"invoiceHead\":\"\",\"transportFee\":0,\"logisticsCompanyId\":1,\"accessSource\":\"noSource\",\"accessDevice\"\r\n" + 
				":0}");
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("submit"+content);
		EntityUtils.consume(entity);
		respone.close();

}
}
