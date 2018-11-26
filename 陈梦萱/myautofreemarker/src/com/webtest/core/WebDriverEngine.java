package com.webtest.core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.webtest.utils.Log;


/**
 *author:chenmengxuan
 *“≥√Ê∂‘œÛ≤Ÿ◊˜¿‡ 
 */

public class WebDriverEngine {
	WebDriver driver = null;
	ElementFinder finder = null;
	Actions action = null;
	
	public WebDriverEngine(WebDriver driver) {
		this.driver = driver;
		driver.manage().window().maximize();
		finder = new ElementFinder(driver);
		action = new Actions(driver);
	}
	private void pause(int time) {
		if (time <= 0) {
			return;
		}
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public String[] getAllWindowTitles() {
		String current = driver.getWindowHandle();
		List<String> attributes = new ArrayList<String>();
		for(String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			attributes.add(driver.getTitle());
		}
		driver.switchTo().window(current);
		return attributes.toArray(new String[attributes.size()]);
	}
	
	public void enterFrame(String frameID) {
		this.pause(3000);
		driver.switchTo().frame(frameID);
		Log.info("Enter iframe" + frameID);
	}
	
	public void leaveFrame() {
		driver.switchTo().defaultContent();
		Log.info("Left the iframe");
	}
	
	public void open(String url) {
		driver.get(url);
		pause(3000);
		Log.info("Opened url" + url);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public boolean isTextPresent(String pattern) {
		String text = driver.getPageSource();
		text = text.trim();
		if(text.contains(pattern)) {
			return true;
		}		
		return false;
	} 
	
	public void typeAndClear(String locator,String value) {
		WebElement element = finder.findElement(locator);
		if(element !=null) {
			element.clear();
			element.sendKeys(value);
		}
	}
	
	public void type(String locator,String value) {
		WebElement element = finder.findElement(locator);
		if(element != null) {
			element.sendKeys(value);
		}
	}
	public boolean isChecked(String locator) {
		WebElement element = finder.findElement(locator);
		return element.isSelected();
	}
	public void click(String locator) {
		WebElement element = finder.findElement(locator);
		if(element != null) {
			element.click();
			this.pause(3000);
		}
	}
	
	public void runJs(String js) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript(js);
	}


	public void clickLonger(String locator) {
		WebElement element = finder.findElement(locator);
		if(element != null) {
			runJs("window.scrollTo(0,"+ element.getLocation().x+")");
			
		}
	}
	
	public void doubleClick(String locator) {
		WebElement element = finder.findElement(locator);
		Actions builder = new Actions(driver);
		builder.doubleClick(element).build().perform();
	}
	
	public void isDisplayed(String locator) {
		WebElement element = finder.findElement(locator);
		if(element !=null) {
			System.out.println(element.isDisplayed());
		}
	}
	public String getText(String locator) {

		return finder.findElement(locator).getText().trim();
	}
	
	public boolean isElementPresent(String locator) {

		WebElement element = null;
		try {
			element = finder.findElement(locator);
		} catch (Exception e) {

			Log.info(e.getMessage());
		}
		if (element != null) {
			return true;
		}
		{
			return false;
		}
	}
	
	public String getValue(String locator) {

		return finder.findElement(locator).getAttribute("value");
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	public void goBack() {
		driver.navigate().back();
	}
	public void goForward() {

		driver.navigate().forward();
	}
	
	public Alert getAlert() {
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	public Select getSelect(String locator) {
		Select inputSelect = new Select(finder.findElement(locator));
		return inputSelect;
	}
	
	public void selectByValue(String locator, String value) {
		getSelect(locator).selectByValue(value);
		this.pause(5000);
	}
	
	
	public void selectByVisibleText(String locator, String value) {
		getSelect(locator).selectByVisibleText(value);
	}
	public void selectByIndex(String locator, int index) {
		getSelect(locator).selectByIndex(index);
	}
	
	public String getAlertTest() {

		return getAlert().getText();
	}
	
	public void alertAccept() {

		getAlert().accept();
	}
	
	public String getHtmlSource() {

		return driver.getPageSource();
	}
	
	public void mouseoverElement(String locator) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(finder.findElement(locator)).perform();
	}
	
	//«–ªª¥∞ø⁄
	public void switchWidow(int i){
	    List<String> windows = new ArrayList<String>();
	    for (String handle : driver.getWindowHandles()) {
	    
	    	windows.add(handle);
	    }
	    driver.switchTo().window(windows.get(i));
	}	
	public void rightClickMouse(String locator) throws InterruptedException {
		action.contextClick(finder.findElement(locator)).perform();
	}
	
	public void tapClick(){
		
		action.sendKeys(Keys.TAB).perform();;
	}
	
	public void tapType(String content){
		
		action.sendKeys(content).perform();
	}
	public void getWindow(int i){
		List<String> windows = new ArrayList<String>();
		for (String handle : driver.getWindowHandles())
		{
			//System.out.println(handle);  //ËøõÂÖ•Âà∞Á¨¨‰∫å‰∏™È°µÈù¢
			windows.add(handle);
		}
		driver.switchTo().window(windows.get(i));
	}
	public boolean ifContains(String content) {
		return driver.getPageSource().contains(content);
	}
	
	
	
	
}
