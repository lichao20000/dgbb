package com.dglt.comm.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 短信内容配置文件读取类
 *  @author        tanw  ~tanw@vcmedu.com
 *  @corporation   广东乐教天地网络服务有限公司
 *  @date          2011 4 6,09:52:22
 *
 */
public class MessageConfigProp {
	private static MessageConfigProp instance = new MessageConfigProp();
	
	private static String regMessage;
	private static String findpwdMessage;

	
	public static void setInstance(MessageConfigProp instance) {
		MessageConfigProp.instance = instance;
	}
	
	public static MessageConfigProp getInstance(){
		return instance;
	}
	private MessageConfigProp(){}
	static{
    	try{
			InputStream inputstream = getInstance().getClass().getResourceAsStream("/messageConfig.properties");
			Properties properties = new Properties();
			properties.load(inputstream);
			
			regMessage     = properties.getProperty("reg.message");
			findpwdMessage = properties.getProperty("findpwd.message");
			
			inputstream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
    }
	public static String getRegMessage() {
		return regMessage;
	}

	public static void setRegMessage(String regMessage) {
		MessageConfigProp.regMessage = regMessage;
	}

	public static String getFindpwdMessage() {
		return findpwdMessage;
	}

	public static void setFindpwdMessage(String findpwdMessage) {
		MessageConfigProp.findpwdMessage = findpwdMessage;
	}
}
