package com.dglt.comm.message;

import com.vcm.VcmSMSClient;


/**
 * �ֻ����ŷ�����
 *  @author        tanw  ~tanw@vcmedu.com
 *  @corporation   �㶫�ֽ��������������޹�˾
 *  @date          2011 4 6,10:00:42
 *
 */
public class MessageSendHelper {

	MessageSendHelper(){}
	//test
	public static void main(String[] args) throws Exception {
		String mobiles = new String("111111111");
		String smsContent = "VCMEDU���Զ��ţ���---ggggggg";
		String addSerial = "520";
		String srcCharset = "GBK";
		String smsPriority = "3";
		long smsID = 12345678;
		String sendTime = "20110408142030";
		System.out.println(VcmSMSClient.sendSMS(mobiles, smsContent, smsPriority));
	}
	
	//ע��������ŷ���
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
	
	//Ѱ��������ŷ���
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
	 * �����Ͷ��Žӿ�
	 * @param mobiles  �ֻ����루���������","���������Ϊ1000���ֻ����룩
	 * @param smsContent ��������(���500�����ֻ�1000����Ӣ��)
	 * @param smsPriority ���ȼ�(�����1��5������Խ�����ȼ�Խ�ߣ�Խ�ȱ�����)��Ϊ�յĻ���ʹ��Ĭ�����ȼ�3
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
