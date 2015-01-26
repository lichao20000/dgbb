package com.dglt.comm.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;




/**
 * 邮件发送类
 *  @author        tanw  ~tanw@vcmedu.com
 *  @corporation   广东乐教天地网络服务有限公司
 *  @date          2010-4-1,下午03:39:26
 *
 */

public class SmtpUtils {

	private Properties props = System.getProperties();

	private Session session = null;

	private Transport transport = null;

	// private Message msg = null;
	private MimeMessage msg = null;

	private MimeMultipart mm = new MimeMultipart();

	private final static String mailer = "netkiller";

	private String username = null;

	private String password = null;

	private String host = null;

	private String from = null;

	private String to = null;

	private String cc = "";

	private String bcc = "";

	private String subject = "";

	private String text = "";

	private String footer = "";

	private boolean debug = false;

	public SmtpUtils() {
	}

	public void createSession() {
		session = Session.getInstance(props, null);
		/*
		 * Session session = Session.getDefaultInstance(props, new
		 * Authenticator(){ public PasswordAuthentication
		 * getPasswordAuthentication(){ return new
		 * PasswordAuthentication(username, password); } });
		 */
		
		

		session.setDebug(debug);
		try {
			transport = session.getTransport("smtp");
		} catch (NoSuchProviderException ex) {
			System.out.println("---1" + ex.getMessage());
		}
		try {
			transport.connect(host, username, password);
		} catch (MessagingException ex1) {
			System.out.println("---2" + ex1.getMessage());
		}
		msg = new MimeMessage(session);
	}

	public void setDebug(boolean debug) throws Exception {
		this.debug = debug;
	}

	public void setSmtpHost(String str) throws Exception {
		this.host = str;
		props.put("mail.smtp.host", host);
	}

	public void setAuth(boolean auth) {
		if (auth) {
			props.put("mail.smtp.auth", "true");
		}
	}

	public void setUsername(String user) {
		this.username = user;
		props.put("mail.smtp.user", username);
	}

	public void setPassword(String pass) {
		this.password = pass;
		props.put("mail.smtp.password", password);
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setFrom(String str) throws Exception {
		this.from = str;
		msg.setFrom(new InternetAddress(from));
	}

	/**
	 * 
	 * @param str 邮箱地址
	 * @param name　名字
	 * @throws Exception 
	 */
	public void setFrom(String str,String name) throws Exception {
		this.from = str;
		msg.setFrom(new InternetAddress(from,name));
	}
	
	public void setTo(String str) throws Exception {
		this.to = str;
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,
				false));
	}

	public void setCC(String str) throws Exception {
		this.cc = str;
		msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc,
				false));
	}

	public void setBCC(String str) throws Exception {
		this.bcc = str;
		msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc,
				false));
	}

	public void setSubject(String str) throws Exception {
		this.subject = str;
		subject = MimeUtility.encodeText(new String(str.getBytes(), "GB2312"),
				"GB2312", "B");
		msg.setSubject(subject);
	}

	public void setText(String str) throws Exception {
		this.text = str;
		this.text += this.footer;

		// msg.setText(this.text);
		BodyPart bp = new MimeBodyPart();
		bp.setContent(text, "text/plain; charset=gb2312");
		mm.addBodyPart(bp);
		msg.setContent(mm);
	}

	public void setFooter(String str) {
		this.footer = str;
	}

	public void setHtml(String str) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<HTML>\n");
		sb.append("<HEAD>\n");
		sb.append("<TITLE>\n");
		// sb.append(subject + "\n");
		sb.append("</TITLE>\n");
		sb.append("</HEAD>\n");

		sb.append("<BODY>\n");
		// sb.append("<H1>" + subject + "</H1>" + "\n");

		sb.append(str);
		sb.append(this.footer);

		sb.append("\n");

		sb.append("</BODY>\n");
		sb.append("</HTML>\n");

		this.text = sb.toString();

		BodyPart bp = new MimeBodyPart();
		bp.setContent(text, "text/html; charset=gb2312");
		mm.addBodyPart(bp);
		msg.setContent(mm);
	}

	public boolean addFileAffix(String filename) {
		boolean bool = false;
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fds));
			// bp.setFileName(fds.getName());
			bp.setFileName(MimeUtility
					.encodeWord(fds.getName(), "GB2312", null));
			bp.setHeader("Content-ID", fds.getName());
			mm.addBodyPart(bp);
			// msg.setContent(mm);
			bool = true;
		} catch (Exception e) {
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
		}
		// System.out.println("增加邮件附件：" + filename);
		return bool;
	}

	public void send() {

		try {
			msg.setHeader("X-Mailer", mailer);
			msg.setSentDate(new Date());
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Mail [" + to + "] was sent successfully.");

		} catch (MessagingException e) {
			// e.printStackTrace();
			// System.out.println(e.toString());
			System.out.println(e.getMessage());
		}
	}

	public String sendTest() {
		String s = "success";

		try {
			msg.setHeader("X-Mailer", mailer);
			msg.setSentDate(new Date());
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Mail was recorded successfully.");

		} catch (MessagingException e) {
			s = "****" + e.toString();
			// System.out.println(e.toString());
			System.out.println("--------------" + e.getMessage());
		}
		return s;
	}

}