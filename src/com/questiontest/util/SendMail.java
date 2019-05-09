package com.questiontest.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail implements Runnable{
	public static String myEmailAccount = "619202755@qq.com";
	public static String myEmailPassword = "lqedxujcvuldbcaa";
	public static String myEmailSMTPHost = "smtp.qq.com";
	private String email;
	private String message2;
	public SendMail(String email,String message2) {
		this.email=email;
		this.message2=message2;
	}
	/** 创建一封邮件邮件 */

	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String message2) throws Exception {

		MimeMessage message = new MimeMessage(session);
		// From: 发件人
		message.setFrom(new InternetAddress(sendMail, "问卷调查系统", "UTF-8"));
		// To: 收件人
		message.addRecipient(RecipientType.TO, new InternetAddress(receiveMail, "UTF-8"));
		message.setSubject("邮箱验证", "UTF-8");
		message.setContent(message2, "text/html;charset=UTF-8");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;

	}

	@Override
	public void run() {
		try {
			//收件人邮箱
			String receiveMailAccount = email;
			Properties props = new Properties(); // 参数配置
			props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
			props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
			final String smtpPort = "465";
			props.setProperty("mail.smtp.port", smtpPort);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.socketFactory.port", smtpPort);
			Session session = Session.getInstance(props);
			session.setDebug(true);
			MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, message2);
			Transport transport = session.getTransport();
			transport.connect(myEmailAccount, myEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("发送邮件失败");
		}

		
	}

}
