package com.webtest.core;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	static int port = 25;

    static String server = "smtp.126.com";

    static String from = "¶ÎÏþÔÂ";

    static String user = "test52462@126.com";
    static String password = "duan1001";

    



    public static void sendEmail(String email, String subject, String body) throws UnsupportedEncodingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", server);
            props.put("mail.smtp.port", String.valueOf(port));
            props.put("mail.smtp.auth", "true");
            Transport transport = null;
            Session session = Session.getDefaultInstance(props,null);
            transport = session.getTransport("smtp");
            transport.connect(server, user, password);
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            
            InternetAddress fromAddress = new InternetAddress(user,from,"UTF-8");
            msg.setFrom(fromAddress);
            String emailList[]=email.split(",");
            InternetAddress[] toAddress = new InternetAddress[emailList.length];
            
            for(int i=0;i<emailList.length;i++)
            {
            	toAddress[i]=new InternetAddress(emailList[i]);
            }
            //msg.setRecipients(Message.RecipientType.TO, toAddress);
            msg.addRecipients(Message.RecipientType.TO, toAddress);
            msg.setSubject(subject, "UTF-8");   
            msg.setContent(body, "text/html;charset=utf-8");
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws UnsupportedEncodingException
    {
    	
    	MailUtil.sendEmail("test52462@126.com", "ceshi", "ce");
//		if(System.getProperty("os.name").contains("w"))
//		{
//			System.out.println(System.getProperty("os.name"));
//			System.out.println("ok");
//		} 
        
    }

}
