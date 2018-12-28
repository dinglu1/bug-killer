package com.edu.utils;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import net.sf.json.JSONObject;

public class Checker {
	static Logger log = LogManager.getLogger(Checker.class.getName());
	String exceptionMessage = "";

	String message = "";

	String actualValue = "";

	String expectValue = "";
	JSONObject json = null;

	public Checker(String result) {
		this.json = JSONObject.fromObject(result);
	}
	public void assertArray(String patten,String key,String content){
		actualValue=this.json.getJSONArray(patten).getJSONObject(0).getString(key);
	assertEquals(actualValue, content);
	}

	public void verifyTextPresent(String patten) throws Exception {

		boolean value = json.containsKey(patten);

		if (value) {
			log.info("the Text: '" + patten + "' is Present!");
		} else {
			log.fatal("the Text: '" + patten + "' is not Present!");
			throw new Exception("the Text: '" + patten + "' is not Present!");
		}
	}

	public void verifyXpath(String locator, String patten){

		actualValue = this.json.getString(locator);
		try {
			verify(patten, actualValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	

	public void verify(String pattern, String actualValue) throws Exception {
		this.actualValue = actualValue;
		this.setExceptionMessage(actualValue, pattern);
		this.setMessage(pattern, actualValue);
		String errormsg = getExceptionMessage();
		String msg = getMessage();
		if (ResultVerifier.verifyStringsByEqualAndExist(pattern, actualValue)) {
			log.info(msg);
		} else {
			log.fatal(errormsg);
			log.info(json);
			throw new Exception(errormsg);
		}
	}
	
	public void assertXpath(String locator, String object) throws Exception {
		// TODO Auto-generated method stub
		actualValue = this.json.getString(locator);
		assertEquals(actualValue,object);
	}
	
	public void setExceptionMessage(String actualValue, String expectValue) {
		this.exceptionMessage = "expectedValue doesn't match actualValue,actual Value is :" + actualValue
				+ "; expected Value is :" + expectValue;
	}

	public void setMessage(String expectValue, String actualValue) {
		this.message = "expectedValue :" + expectValue + "  match actualValue:" + actualValue;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public String getMessage() {
		return message;
	}
}
