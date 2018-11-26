package com.webtest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

public class SendMailUtils {
	/**
	 * @author wangdonghui
	 */
	
	static String server = "smtp.qq.com";
	//code="uijvgyalzszfcgef"  QQ��Ȩ��
	public static void mail(String filename,String from,String code,String to) throws Exception {
		
		// ����һ��Property�ļ�����
		Properties props = new Properties();
		// �����ʼ�����������Ϣ����������smtp��������
		props.put("mail.smtp.host", server);
		// ������Ҫ�����֤
		props.put("mail.smtp.auth", "true");
		// �����֤ʵ��
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// �ڶ���������������QQ����smtp����Ȩ��
				return new PasswordAuthentication(from, code + "");
			}
		});
		try {
			// ����һ��MimeMessage���ʵ������
			Message message = new MimeMessage(session);

			// ���÷����������ַ
			message.setFrom(new InternetAddress(from));

			// �����ռ��������ַ
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// �����ʼ�����
			message.setSubject("���Է����ʼ�");

			// ����һ��MimeBodyPart�Ķ����Ա��������
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// �����ʼ���������
			messageBodyPart1.setText("���Ա���");

			// ��������һ��MimeBodyPart�����Ա������������
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// �����ʼ��и����ļ���·��


			// ����һ��datasource���󣬲������ļ�
			DataSource source = new FileDataSource(filename);

			// ����handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// �����ļ�
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
			String nowDateTime=sdf.format(new Date());
			messageBodyPart2.setFileName(nowDateTime+"���Ա���.zip");

			// ����һ��MimeMultipart���ʵ������
			Multipart multipart = new MimeMultipart();

			// �������1����
			multipart.addBodyPart(messageBodyPart1);

			// �������2����
			multipart.addBodyPart(messageBodyPart2);
			// ��������
			message.setContent(multipart);
			// ���շ����ʼ�
			Transport.send(message);
			System.out.println("=====�ʼ��Ѿ�����=====");
		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}
	}
}
