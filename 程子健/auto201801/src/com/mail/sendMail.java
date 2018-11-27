package com.mail;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import com.webtest.utils.ReadProperties;

public class sendMail {
	private String smtp;
	private String sender;
	private String reciever;
	private String code;
	private String title;
	private String NGappendix;
	private String body;
	
	public sendMail() throws Exception{
		//����config.properties����valueֵ��ȡ��Ӧ����
		smtp = ReadProperties.getPropertyValue("smtp.163.com");//
		sender = ReadProperties.getPropertyValue("15226585189@163.com");//�����������ַ
		reciever = ReadProperties.getPropertyValue("709411753@qq.com");//�����������ַ
		code = ReadProperties.getPropertyValue("CZJ666"); //�������ʼ���Ȩ��
		NGappendix = ReadProperties.getPropertyValue("appendix");//����·��
	}
	
	public void setTitle_body(String title,String body) {//�����ʼ��ı��������
		this.title = title;
		this.body = body;
	}
	
    public void Mail() throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp);//ָ���ʼ��ķ��ͷ�������ַ
        props.put("mail.smtp.auth", "true");//�������Ƿ�Ҫ��֤�û��������Ϣ

        Session session = Session.getInstance(props);//�õ�Session
        session.setDebug(true);//��������debugģʽ�������ڿ���̨���smtpЭ��Ӧ��Ĺ���


        //����һ��MimeMessage��ʽ���ʼ�
        MimeMessage message = new MimeMessage(session);

        //���÷�����
        Address fromAddress = new InternetAddress(sender);//�ʼ���ַ
        message.setFrom(fromAddress);//���÷��͵��ʼ���ַ
        //���ý�����
        Address toAddress = new InternetAddress(reciever);//Ҫ�����ʼ�������
        message.setRecipient(RecipientType.TO, toAddress);//���ý����ߵĵ�ַ

        //�����ʼ�������
        message.setSubject(title);
       /* //�����ʼ�������
        message.setText("practice trianing");*/
       
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        //����
        messageBodyPart = new MimeBodyPart();
        String filename = NGappendix;//����·��
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        //�����ʼ�
        message.saveChanges();


       
        
        //�õ������ʼ��ķ�����(�����õ���smtp������)
        Transport transport = session.getTransport("smtp");

        //�����ߵ��˺����ӵ�smtp��������  @163.com���Բ�д
        transport.connect(smtp,sender,code); 
        //������Ϣ
        transport.sendMessage(message, message.getAllRecipients());
        //�رշ�����ͨ��
        transport.close();
    }


}
