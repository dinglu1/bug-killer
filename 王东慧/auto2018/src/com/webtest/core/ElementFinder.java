package com.webtest.core;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webtest.utils.Log;
/**
 * author:lihuanzhen
 * 查找元素类
 */
public class ElementFinder {
	
	WebDriver driver;
	public  ElementFinder(WebDriver driver)
	{
		this.driver=driver;
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
	
	public WebElement findElementByPrefix(String locator)
	{
		String target=locator.trim(); //trim()作用：去除字符串两端的多余的空格
		if(target.startsWith("id="))
		{
			//substring:截取字符串，substring(start,end),缺少end则一直截到最后
			locator = locator.substring("id=".length());  
			return driver.findElement(By.id(locator));
		}else if(target.startsWith("class="))
		{
			locator = locator.substring("class=".length());
			return driver.findElement(By.className(locator));
		}else if(target.startsWith("name="))
		{
			locator = locator.substring("name=".length());
			return driver.findElement(By.name(locator));
		}else if(target.startsWith("link="))
		{
			locator = locator.substring("link=".length());
			System.out.println(locator);
			return driver.findElement(By.linkText(locator));
		}else if(target.startsWith("css="))
		{
			locator = locator.substring("css=".length());
			return driver.findElement(By.cssSelector(locator));
		}else if(target.startsWith("xpath="))
		{
			locator = locator.substring("xpath=".length());
			return driver.findElement(By.xpath(locator));
		}else if(target.startsWith("tag="))
		{
			locator = locator.substring("tag=".length());
			return driver.findElement(By.tagName(locator));
		}
		else
		{
			Log.info(locator+"can't find element by prefix.");
			return null;
		}
	}
	
}
