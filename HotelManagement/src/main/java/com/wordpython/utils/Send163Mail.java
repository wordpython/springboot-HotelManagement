package com.wordpython.utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class Send163Mail {
	private String from="wordpython@163.com";
	private String user="wordpython@163.com";
	private String password="wggpqy120429";
	
	public boolean sendMail(String to,String text,String title) {
		Properties props=new Properties();
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");
		//阿里云服务器禁用25端口，所以服务器上改为465端口,加入了SSL验证
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.setProperty("mail.smtp.port", "465");
	    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.setProperty("mail.smtp.socketFactory.fallback", "false");
		Session session=Session.getDefaultInstance(props);
		session.setDebug(true);
		MimeMessage message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(title);
			Multipart multipart=new MimeMultipart();
			BodyPart contentPart=new MimeBodyPart();
			contentPart.setContent(text,"text/html;charset=utf-8");
			multipart.addBodyPart(contentPart);
			message.setContent(multipart);
			message.saveChanges();
			Transport transport=session.getTransport("smtp");
			transport.connect("smtp.163.com",user,password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}catch(MessagingException e) {
			e.printStackTrace();
			System.out.println("111111111111 邮箱发送报错  Send163Mail.java");
			return false;
		}
		return true;
	}
}
