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
		//均从config.properties根据value值读取相应内容
		smtp = ReadProperties.getPropertyValue("smtp.163.com");//
		sender = ReadProperties.getPropertyValue("15226585189@163.com");//发送者邮箱地址
		reciever = ReadProperties.getPropertyValue("709411753@qq.com");//接收者邮箱地址
		code = ReadProperties.getPropertyValue("CZJ666"); //发送者邮件授权码
		NGappendix = ReadProperties.getPropertyValue("appendix");//附件路径
	}
	
	public void setTitle_body(String title,String body) {//设置邮件的标题和正文
		this.title = title;
		this.body = body;
	}
	
    public void Mail() throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp);//指定邮件的发送服务器地址
        props.put("mail.smtp.auth", "true");//服务器是否要验证用户的身份信息

        Session session = Session.getInstance(props);//得到Session
        session.setDebug(true);//代表启用debug模式，可以在控制台输出smtp协议应答的过程


        //创建一个MimeMessage格式的邮件
        MimeMessage message = new MimeMessage(session);

        //设置发送者
        Address fromAddress = new InternetAddress(sender);//邮件地址
        message.setFrom(fromAddress);//设置发送的邮件地址
        //设置接收者
        Address toAddress = new InternetAddress(reciever);//要接收邮件的邮箱
        message.setRecipient(RecipientType.TO, toAddress);//设置接收者的地址

        //设置邮件的主题
        message.setSubject(title);
       /* //设置邮件的内容
        message.setText("practice trianing");*/
       
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        //附件
        messageBodyPart = new MimeBodyPart();
        String filename = NGappendix;//附件路径
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        //保存邮件
        message.saveChanges();


       
        
        //得到发送邮件的服务器(这里用的是smtp服务器)
        Transport transport = session.getTransport("smtp");

        //发送者的账号连接到smtp服务器上  @163.com可以不写
        transport.connect(smtp,sender,code); 
        //发送信息
        transport.sendMessage(message, message.getAllRecipients());
        //关闭服务器通道
        transport.close();
    }


}
