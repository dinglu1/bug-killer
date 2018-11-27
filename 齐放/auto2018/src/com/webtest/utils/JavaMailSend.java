package com.webtest.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

public class JavaMailSend {
	//配置
	//收件人邮箱
	String to;
	//发件人邮箱
	String from;
	//指定发送邮件主机(QQ邮箱)
	String host = "smtp.qq.com";
	
	//构造
	public JavaMailSend(String to,String from) {
		this.to = to;
		this.from = from;
	}
	
	//发送邮件方法
	public void send() throws FileNotFoundException {
		//获取系统属性
		Properties properties = System.getProperties();
		//设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		//配置Authenticator对象
		Authenticator auth =new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("739242114@qq.com","paisqczeyuzobedc");	
			}	
		};
		//配置session对象
		Session session = Session.getDefaultInstance(properties,auth);
		//配置测试报告格式
		try {
			//创建默认MimeMessage对象
			MimeMessage message = new MimeMessage(session);
			//配置
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject("测试报告");
			//配置正文
			MimeBodyPart text = new MimeBodyPart();
			text.setContent("附件为本次测试报告", "text/html;charset=UTF-8");//避免乱码
			//压缩测试报告
			TestZip tzip = new TestZip();
			String srcDir = "D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\html";
			String outsrc = "D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\tzip.zip";
			FileOutputStream fos = new FileOutputStream(new File(outsrc));
	        TestZip.toZip(srcDir, fos, true);
	        DataSource source = new FileDataSource("D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\tzip.zip");
			//创建邮件附件
			MimeBodyPart attach = new MimeBodyPart();
			DataHandler dh = new DataHandler(new FileDataSource("D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\tzip.zip"));
			attach.setDataHandler(dh);
			attach.setFileName(dh.getName());
			//创建容器描述数据关系
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(text);
			mp.addBodyPart(attach);
			mp.setSubType("mixed");        
			message.setContent(mp);
			message.saveChanges();
			//发送消息
			Transport.send(message);
			System.out.println("发送成功");
		}catch(MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
