package com.dglt.base.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.dglt.comm.base.Globals;
import com.dglt.comm.util.StringUtil;

/**
 * 属性文件读取类
 * 
 * @author Administrator
 * 
 */
public class ProperUtil {

	public ProperUtil() {
	}

	private static ProperUtil instance = new ProperUtil();

	public static ProperUtil getInstance() {
		return instance;
	}

	private static String emailSmtpHost;
	private static String sendUser;
	private static String sendUserLoginName;
	private static String sendUserName;
	private static String sendUserPswd;
	private static String isproceduGetData;//仪表盘1:存储过程取数,0：SQL取数

	private static Map<String, String> emailContentMap = new HashMap<String, String>();

	static {
		try {
			InputStream inputstream = getInstance().getClass()
					.getResourceAsStream("/platformConfig.properties");
			Properties properties = new Properties();
			properties.load(inputstream);

			emailSmtpHost = properties.getProperty("email.send.smtpHost");
			sendUser = properties.getProperty("email.send.user");
			sendUserName = properties.getProperty("email.send.user.name");
			sendUserLoginName = properties
					.getProperty("email.send.user.loginName");
			sendUserPswd = properties.getProperty("email.send.user.pswd");
			isproceduGetData =  properties.getProperty("is.procedu.get.data");
			String emailContentAbsolutePath = Globals.CONTEXT_ROOT
					+ properties.getProperty("email.Content.Relative.path");
			String emailContentfile = properties
					.getProperty("email.Content.file");
			String[] content = StringUtil.split(emailContentfile, '|');

			emailContentAbsolutePath = StringUtil.replace(
					emailContentAbsolutePath, "\\", "/");
			if (content.length > 0) {
				for (int i = 0; i < content.length; i++) {
					String fileName = emailContentAbsolutePath + content[i];
					FileInputStream fileinputstream = new FileInputStream(
							fileName);
					int lenght = fileinputstream.available();
					byte bytes[] = new byte[lenght];
					fileinputstream.read(bytes);
					fileinputstream.close();
					String templateContent = new String(bytes);
					emailContentMap.put(content[i], templateContent);
				}
			}

			inputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("-----------读取属性文件platformConfig.properties出错,请查检!!!");
		}
	}

	public static String getEmailSmtpHost() {
		return emailSmtpHost;
	}

	public static String getSendUser() {
		return sendUser;
	}

	public static String getSendUserLoginName() {
		return sendUserLoginName;
	}

	public static String getSendUserName() {
		return sendUserName;
	}

	public static String getSendUserPswd() {
		return sendUserPswd;
	}

	public static Map<String, String> getEmailContentMap() {
		return emailContentMap;
	}
	public static String getIsproceduGetData() {
		return isproceduGetData;
	}


}
