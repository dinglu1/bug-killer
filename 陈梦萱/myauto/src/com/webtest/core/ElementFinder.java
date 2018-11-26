package com.webtest.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webtest.utils.Log;

import org.openqa.selenium.By;


/*
 * author:chenmengxuan
 * 
 */

public class ElementFinder {
	WebDriver driver=null;
	public ElementFinder(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement findElement(String target) {
		WebElement element = null;

		try {
			element = findElementByPrefix(target);
		} catch (Exception e) {

			Log.info(e.toString());
		}
		return element;

	}

public WebElement findElementByPrefix(String locator) {
	String target = locator.trim();
	if(target.startsWith("id=")) {
		locator = locator.substring("id=.".length());
		return driver.findElement(By.id(locator));
	}else if(target.startsWith("class=")) {
		locator = locator.substring("class=".length());
		return driver.findElement(By.className(locator));
	}else if(target.startsWith("name=")) {
		locator = locator.substring("name=".length());
		return driver.findElement(By.name(locator));
	}else if(target.startsWith("link=")) {
		locator = locator.substring("link=".length());
		System.out.println(locator);
		return driver.findElement(By.linkText(locator));
	}else if(target.startsWith("css=")) {
		locator = locator.substring("css=".length());
		return driver.findElement(By.cssSelector(locator));
	}else if(target.startsWith("xpath=")) {
		locator = locator.substring("xpath=".length());
		return driver.findElement(By.xpath(locator));
	}else if(target.startsWith("tag=")) {
		locator = locator.substring("tag=".length());
		return driver.findElement(By.tagName(locator));
	}else{
		Log.info(locator+"cant't find element by prefix");
		return null;
	}
}
	
	

}
