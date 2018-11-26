package testSuite;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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


public class SendEmail {
	public static void sendEmail(String path) throws IOException {
	
		// ����һ��Property�ļ�����
		Properties props = new Properties();
		// �����ʼ�����������Ϣ����������smtp��������
		props.put("mail.smtp.host", "smtp.163.com");
		// ����socket factory �Ķ˿�
		props.put("mail.smtp.socketFactory.port", "465");
		// ����socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		// ������Ҫ�����֤
		props.put("mail.smtp.auth", "true");
		// ����SMTP�Ķ˿ڣ�QQ��smtp�˿���25
		props.put("mail.smtp.port", "25");
		// �����֤ʵ��
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
                        // �ڶ���������������QQ����smtp����Ȩ��
			return new PasswordAuthentication("15226523963@163.com", "dreamy1997");

			}

		});
		try {
			// ����һ��MimeMessage���ʵ������
			Message message = new MimeMessage(session);
			// ���÷����������ַ
			message.setFrom(new InternetAddress("15226523963@163.com"));
			// �����ռ��������ַ
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("2295266653@qq.com"));
            // �����ʼ�����
			message.setSubject("���Է����ʼ�");
			// ����һ��MimeBodyPart�Ķ����Ա��������
			BodyPart messageBodyPart1 = new MimeBodyPart();
			// �����ʼ���������
//			String filePath = path;
//			File file = new File(filePath);
//			InputStream input = new FileInputStream(file);
//			StringBuffer buffer = new StringBuffer();
//			byte[] bytes = new byte[1034];
//			for(int n;(n=input.read(bytes))!=-1;) {
//				buffer.append(new String(bytes,0,n,"GBK"));
//			}
			messageBodyPart1.setText("������ʼ����Ĳ���");
			// ��������һ��MimeBodyPart�����Ա������������
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			// �����ʼ��и����ļ���·��

			String filename = "C:\\Users\\xuan\\eclipse-workspace\\myauto\\test-output\\1.zip";
			// ����һ��datasource���󣬲������ļ�
			
	  
			DataSource source = new FileDataSource(new File(filename));
			// ����handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
			// �����ļ�
			messageBodyPart2.setFileName("1.zip");
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
//	 public static void main(String[] args) {
//		 String filepath= "C:\\Users\\xuan\\eclipse-workspace\\myauto\\test-output\\1.zip";
//		 SendEmail sm = new SendEmail();
//			try {
//				sm.sendEmail(filepath);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//	    }
}
