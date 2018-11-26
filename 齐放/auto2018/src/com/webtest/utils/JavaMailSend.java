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
	//����
	//�ռ�������
	String to;
	//����������
	String from;
	//ָ�������ʼ�����(QQ����)
	String host = "smtp.qq.com";
	
	//����
	public JavaMailSend(String to,String from) {
		this.to = to;
		this.from = from;
	}
	
	//�����ʼ�����
	public void send() throws FileNotFoundException {
		//��ȡϵͳ����
		Properties properties = System.getProperties();
		//�����ʼ�������
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		//����Authenticator����
		Authenticator auth =new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("739242114@qq.com","paisqczeyuzobedc");	
			}	
		};
		//����session����
		Session session = Session.getDefaultInstance(properties,auth);
		//���ò��Ա����ʽ
		try {
			//����Ĭ��MimeMessage����
			MimeMessage message = new MimeMessage(session);
			//����
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject("���Ա���");
			//��������
			MimeBodyPart text = new MimeBodyPart();
			text.setContent("����Ϊ���β��Ա���", "text/html;charset=UTF-8");//��������
			//ѹ�����Ա���
			TestZip tzip = new TestZip();
			String srcDir = "D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\html";
			String outsrc = "D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\tzip.zip";
			FileOutputStream fos = new FileOutputStream(new File(outsrc));
	        TestZip.toZip(srcDir, fos, true);
	        DataSource source = new FileDataSource("D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\tzip.zip");
			//�����ʼ�����
			MimeBodyPart attach = new MimeBodyPart();
			DataHandler dh = new DataHandler(new FileDataSource("D:\\Program Files (x86)\\Java\\javaproject\\auto2018\\test-output\\tzip.zip"));
			attach.setDataHandler(dh);
			attach.setFileName(dh.getName());
			//���������������ݹ�ϵ
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(text);
			mp.addBodyPart(attach);
			mp.setSubType("mixed");        
			message.setContent(mp);
			message.saveChanges();
			//������Ϣ
			Transport.send(message);
			System.out.println("���ͳɹ�");
		}catch(MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
