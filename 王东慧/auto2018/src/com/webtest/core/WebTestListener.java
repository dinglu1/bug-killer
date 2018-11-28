package com.webtest.core;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.webtest.utils.Log;
import com.webtest.utils.MailUtil;
import com.webtest.utils.ReadProperties;

public class WebTestListener extends TestListenerAdapter {

	FreemarkerTemplateEngine ft = new FreemarkerTemplateEngine();

	public WebTestListener() {
		super();
	}

	private String writeResultToMailTemplate() {
		ITestNGMethod method[] = this.getAllTestMethods();
		List failedList = this.getFailedTests();
		List passedList = this.getPassedTests();

		List failedList1 = new ArrayList();
		List passedList1 = new ArrayList();
		for (int j = 0; j < failedList.size(); j++) {
			ITestResult tr = (ITestResult) failedList.get(j);
			for (int i = 0; i < method.length; i++) {
				if (tr.getMethod().getMethodName().equals(method[i].getMethodName())) {
					if (method[i].getDescription() != null) {
						tr.setAttribute("name", method[i].getDescription());
					} else {
						tr.setAttribute("name", "");
					}
					break;
				}
			}
			failedList1.add(tr);
		}
		for (int j = 0; j < passedList.size(); j++) {
			ITestResult tr = (ITestResult) passedList.get(j);
			for (int i = 0; i < method.length; i++) {
				if (tr.getMethod().getMethodName().equals(method[i].getMethodName())) {
					if (method[i].getDescription() != null) {
						tr.setAttribute("name", method[i].getDescription());
					} else {
						tr.setAttribute("name", "");
					}
					break;
				}
			}
			passedList1.add(tr);
		}
		Map context = new HashMap();
		context.put("date", new Date());
		context.put("failedList", failedList);
		context.put("passedList", passedList1);
		context.put("casesize", passedList.size() + failedList.size());
		context.put("failcasesize", failedList.size());
		try {
			String content = ft.run(context);
			return content;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);
		// 本地调试
		if (System.getProperty("os.name").contains("dow")) {
			// return;
		}
		try {
			if (ReadProperties.getPropertyValue("enable_email").equals("true")) {
				String emailContent = this.writeResultToMailTemplate();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
				String nowDateTime=sdf.format(new Date());
				String emailTitle = ReadProperties.getPropertyValue("mail_title") + "----" + nowDateTime;
				
				String toMail = ReadProperties.getPropertyValue("to_mail");
				try {
					if (this.getFailedTests() != null && this.getFailedTests().size() > 0) {
						MailUtil.sendEmail(toMail, emailTitle, emailContent);
						Log.info("email send to " + toMail + " success");
					} else {
						MailUtil.sendEmail(ReadProperties.getPropertyValue("success_to_mail"), emailTitle,
								emailContent);
						Log.info("email send to " + ReadProperties.getPropertyValue("success_to_mail") + " success");
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					Log.fatal("email send fail :" + e.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getTime() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
		return f.format(c.getTime());
	}
}