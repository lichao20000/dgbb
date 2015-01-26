package com.dglt.comm.message;

import com.vcm.VcmSMSClient;


/**
 * 手机短信发送类
 *  @author        tanw  ~tanw@vcmedu.com
 *  @corporation   广东乐教天地网络服务有限公司
 *  @date          2011 4 6,10:00:42
 *
 */
public class MessageSendHelper {

	MessageSendHelper(){}
	//test
	public static void main(String[] args) throws Exception {
		String mobiles = new String("111111111");
		String smsContent = "VCMEDU测试短信！！---ggggggg";
		String addSerial = "520";
		String srcCharset = "GBK";
		String smsPriority = "3";
		long smsID = 12345678;
		String sendTime = "20110408142030";
		System.out.println(VcmSMSClient.sendSMS(mobiles, smsContent, smsPriority));
	}
	
	//注册随机短信发送
	public static boolean  regMsgSend(String moblie, String randCode,String signature) {
		String content = java.text.MessageFormat.format(MessageConfigProp.getInstance().getRegMessage(),randCode)+signature;
		//System.out.println("::MessageSendHelper-regMsgSend-content:"+content);
		try
		{
			return VcmSMSClient.sendSMS(moblie, content, "5");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//寻找密码短信发送
	public static boolean  findpwdMsgSend(String moblie, String pwd,String domain,String signature) {
		String content = java.text.MessageFormat.format(MessageConfigProp.getInstance().getFindpwdMessage(),domain,pwd)+signature;
		//System.out.println("::MessageSendHelper-findpwdMsgSend-content:"+content);
		try
		{
			return VcmSMSClient.sendSMS(moblie, content, "5");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 调发送短信接口
	 * @param mobiles  手机号码（多个号码以","隔开，最多为1000个手机号码）
	 * @param smsContent 短信内容(最多500个汉字或1000个纯英文)
	 * @param smsPriority 优先级(级别从1到5，数字越大优先级越高，越先被发送)，为空的话就使用默认优先级3
	 * @return 
	 *  @author        tanw  ~tanw@vcmedu.com
	 *  @date          2011 4 8,11:54:03
	 */
/*	private static  String send(String mobiles, String smsContent, String smsPriority) {
		
		URL url = null;
	    HttpURLConnection httpurlconnection = null;
	    String responseContent = "1";
	    try
	    {
	      StringBuffer str = new StringBuffer();
	      str.append("method=sendSMS&mobiles=").append(mobiles)
	          .append("&smsContent=").append(StringUtil.replace(smsContent, "&", ""))
	          .append("&smsPriority=").append(smsPriority);
	      url = new URL(MessageConfigProp.getInstance().getSendMessageUrl());
	      httpurlconnection = (HttpURLConnection) url.openConnection();
	      httpurlconnection.setDoOutput(true);
	      httpurlconnection.setRequestMethod("POST");
	      httpurlconnection.getOutputStream().write(str.toString().getBytes());
	      httpurlconnection.getOutputStream().flush();
	      httpurlconnection.getOutputStream().close();
	      
          StringBuffer temp = new StringBuffer();
          int c;
          InputStream    in1 = new BufferedInputStream(httpurlconnection.getInputStream());
		  Reader         r1 = new InputStreamReader(in1);
		  while ((c = r1.read()) != -1)
			temp.append((char) c);
		  in1.close();
			
          responseContent = temp.toString();

	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	      if(httpurlconnection!=null)
	        httpurlconnection.disconnect();
	    }
		return responseContent;
	}*/
}
