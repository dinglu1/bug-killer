package com.webtest.core;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.webtest.utils.ZipUtils;
import com.webtest.utils.ZipUtils.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

public class Sendmail {
	public Properties prop ;
	@Test
	public void Sendmail() throws  Exception
	{
//		String addressname,String toAddressname
//		prop.setProperty("mail.smtp", "smtp.163.com");
//		prop.setProperty("mail.transport.protocol", "smtp");
//		prop.put("mail.smtp.auth", true);
//		
//		Session session = Session.getInstance(prop);
//		MimeMessage message = new MimeMessage(session);
//		Address address = new InternetAddress("addressname");
//		message.setFrom(address);
//		Address toAddress = new InternetAddress("toAddressname");
//		message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
//		
//		//主题
//		message.setSubject("Hello world");
//		//正文
//		message.setText("Hello world");
//		message.saveChanges();
//		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
//		session.setDebug(true);
//		//通过session得到transport对象
//		Transport transport = session.getTransport("smtp");
//		transport.connect("smtp.163.com", "m15227177568_2@163.com", "dinglu1998");
//		//发送
//		 transport.sendMessage(message, message.getAllRecipients());
//         transport.close(); 
		Properties prop = new Properties();
		prop.setProperty("mail.smtp", "smtp.163.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", true);
		
		//使用JavaMail发送邮件
		//基本的邮件会话
		Session session = Session.getInstance(prop);
		//构建信息体
		MimeMessage message = new MimeMessage(session);
		//发件地址
		Address address = new InternetAddress("m15227177568_2@163.com");
		message.setFrom(address);
		//收件地址
		Address toAddress = new InternetAddress("490709869@qq.com");
		message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
		
		// 设置邮件主题
		message.setSubject("ReportNG测试报告");

		// 创建一个MimeBodyPart的对象，以便添加内容
		BodyPart messageBodyPart1 = new MimeBodyPart();

		// 设置邮件正文内容
		messageBodyPart1.setText("测试报告");

		// 创建另外一个MimeBodyPart对象，以便添加其他内容
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		// 设置邮件中附件文件的路径
		// 创建一个datasource对象，并传递文件
	    String fileName = "F:\\大三（上）\\auto2018\\test-output\\html";
        String zipPath = "F:\\大三（上）\\auto2018\\test-output\\mail.zip";
        FileOutputStream fos = new FileOutputStream(new File(zipPath));
        ZipUtils.toZip(fileName, fos, true);
		DataSource source = new FileDataSource("F:\\大三（上）\\auto2018\\test-output\\mail.zip");

		// 设置handler
		messageBodyPart2.setDataHandler(new DataHandler(source));

		// 加载文件
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String nowDateTime=sdf.format(new Date());
		messageBodyPart2.setFileName(nowDateTime+".zip");

		// 创建一个MimeMultipart类的实例对象
		Multipart multipart = new MimeMultipart();

		// 添加正文1内容
		multipart.addBodyPart(messageBodyPart1);

		// 添加正文2内容
		multipart.addBodyPart(messageBodyPart2);
		// 设置内容
		message.setContent(multipart);
		message.saveChanges();
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		//通过session得到transport对象
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", "m15227177568_2@163.com", "dinglu1998");
		//发送
		
		 transport.sendMessage(message, message.getAllRecipients());
         transport.close(); 
	}
     

	}

