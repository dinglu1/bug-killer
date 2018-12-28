package com.edu.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

public class HttpClientForGetTransportFeeTest {

	String url = "/common/getTransportFee";
	String result = null;
	Checker check = null;
	
	public String getTransportFee() {
		
		Map<String,Object> map = new HashMap();
		map.put("id", 1);
		map.put("addressDetail", "河北省_石家庄市_裕华区");
		result = HttpDriver.doGet(url, map);
		check = new Checker(result);
		
		return result;
	}
	
	public String getTransportFeeByIdError(String id) {
		Map<String,Object> map = new HashMap();
		map.put("id", id);
		map.put("addressDetail", "河北省_石家庄市_裕华区");
		result = HttpDriver.doGet(url, map);
		check = new Checker(result);
		
		return result;
	}
	
	public String getTransportFeeById(int id) {
		Map<String,Object> map = new HashMap();
		map.put("id", id);
		map.put("addressDetail", "河北省_石家庄市_裕华区");
		result = HttpDriver.doGet(url, map);
		check = new Checker(result);
		
		return result;
	}
	
	
	public String getTransportFeeByAddressDetail(String addressDetail) {
		
		Map<String,Object> map = new HashMap();
		map.put("id", 1);
		map.put("addressDetail",addressDetail);
		result = HttpDriver.doGet(url, map);
		check = new Checker(result);
		
		return result;
	}
	
	
	//getTransportFee-1
	@Test(description="id_addressDetail参数均正确_获取运费成功")
	public void testGetTransportFeeSuccess() throws Exception {
		result = getTransportFee();
		check.verifyXpath("message", "success");
	}
	
	//getTransportFee-2
	@Test(description="id参数类型不正确_获取运费失败")
	public void testGetTransportFeeByIdError() throws Exception {
		result = getTransportFeeByIdError("1");
		check.verifyXpath("message", "success");
	}
	
	//getTransportFee-3
	@Test(description="id参数不正确_获取运费成功")
	public void testGetTransportFeeById() throws Exception {
		result = getTransportFeeById(-1);
		check.verifyXpath("message", "success");
	}
	
	//getTransportFee-4
	@Test(description="id参数为空_获取运费失败")
	public void testGetTransportFeeByNullId() throws Exception {
		result = getTransportFeeByIdError("");
		check.verifyXpath("message", "请求失败");
	}
	
	//getTransportFee-5
	@Test(description="addressDetail参数中区不匹配_请求失败")
	public void testGetTransportFeeByAreaError() throws Exception {
		result = getTransportFeeByAddressDetail("浙江省_杭州市_裕华区");
		check.verifyXpath("message", "success");
	}
	
	//getTransportFee-6
	@Test(description="addressDetail参数中市不匹配_请求失败")
	public void testGetTransportFeeByCityError() throws Exception {
		result = getTransportFeeByAddressDetail("浙江省_石家庄市_西湖区");
		check.verifyXpath("message", "success");
	}
	
	//getTransportFee-7
	@Test(description="addressDetail参数中市区均不匹配_请求失败")
	public void testGetTransportFeeByCityAreaError() throws Exception {
		result = getTransportFeeByAddressDetail("浙江省_石家庄市_昌平区");
		check.verifyXpath("message", "success");
	}

	//getTransportFee-8
	@Test(description="addressDetail参数为直辖市_请求成功")
	public void testGetTransportFeeByDirectly() throws Exception {
		result = getTransportFeeByAddressDetail("北京市_北京市_朝阳区");
		check.verifyXpath("message", "success");
	}
	
	//getTransportFee-9
	@Test(description="addressDetail参数为自治区_请求成功")
	public void testGetTransportFeeByMuni() throws Exception {
		result = getTransportFeeByAddressDetail("宁夏回族自治区");
		check.verifyXpath("message", "success");
	}
	
	//getTransportFee-10
	@Test(description="addressDetail参数为特别行政区_请求成功")
	public void testGetTranportFeeBySpecial() throws Exception {
		result = getTransportFeeByAddressDetail("香港特别行政区");
		check.verifyXpath("message", "success");
	}
	
}
