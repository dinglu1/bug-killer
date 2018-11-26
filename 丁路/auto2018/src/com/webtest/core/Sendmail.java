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
	public void Sendmail(String addressname,String toAddressname) throws  Exception
	{

		Properties prop = new Properties();
		prop.setProperty("mail.smtp", "smtp.163.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", true);
		
		//ʹ��JavaMail�����ʼ�
		//�������ʼ��Ự
		Session session = Session.getInstance(prop);
		//������Ϣ��
		MimeMessage message = new MimeMessage(session);
		//������ַ
		Address address = new InternetAddress(addressname);
		message.setFrom(address);
		//�ռ���ַ
		Address toAddress = new InternetAddress(toAddressname);
		message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
		
		// �����ʼ�����
		message.setSubject("ReportNG���Ա���");

		// ����һ��MimeBodyPart�Ķ����Ա��������
		BodyPart messageBodyPart1 = new MimeBodyPart();

		// �����ʼ���������
		messageBodyPart1.setText("���Ա���");

		// ��������һ��MimeBodyPart�����Ա������������
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		// �����ʼ��и����ļ���·��
		// ����һ��datasource���󣬲������ļ�
	    String fileName = "F:\\�������ϣ�\\auto2018\\test-output\\html";
        String zipPath = "F:\\�������ϣ�\\auto2018\\test-output\\mail.zip";
        FileOutputStream fos = new FileOutputStream(new File(zipPath));
        ZipUtils.toZip(fileName, fos, true);
		DataSource source = new FileDataSource("F:\\�������ϣ�\\auto2018\\test-output\\mail.zip");

		// ����handler
		messageBodyPart2.setDataHandler(new DataHandler(source));

		// �����ļ�
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String nowDateTime=sdf.format(new Date());
		messageBodyPart2.setFileName(nowDateTime+".zip");

		// ����һ��MimeMultipart���ʵ������
		Multipart multipart = new MimeMultipart();

		// �������1����
		multipart.addBodyPart(messageBodyPart1);

		// �������2����
		multipart.addBodyPart(messageBodyPart2);
		// ��������
		message.setContent(multipart);
		message.saveChanges();
		//����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
		session.setDebug(true);
		//ͨ��session�õ�transport����
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", "m15227177568_2@163.com", "dinglu1998");
		//����
		
		 transport.sendMessage(message, message.getAllRecipients());
         transport.close(); 
	}
     

	}

