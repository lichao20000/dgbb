package com.dglt.comm.email;

import com.dglt.base.util.ProperUtil;
import com.dglt.comm.util.SmtpUtils;

/**
 * ∑¢ÀÕ” œ‰∞Ô÷˙¿‡
 *
 */
public class EmailSendHelper {
	EmailSendHelper(){}
	
	/**
	 * “«±Ì≈Ã‘§æØ–≈œ¢∑¢ÀÕ
	 * @param userEmail ”√ªß” œ‰
	 * @param nickName  ƒÿ≥∆
	 * @param loginName µ«¬º√˚
	 * @param passWord  √‹¬Î
	 * @return 
	 */
	public static boolean SendDashWarnContent(String userEmail,String kpi_name,
			String month_desc,String product_name,String client_name,String sp_name,String amount_curr,String warn_value){

		if (userEmail == null  || kpi_name == null 
				|| month_desc == null) return false;
		if (userEmail.indexOf("@") == -1) return false;

		try {
			SmtpUtils am = new SmtpUtils();
			String sendEmail = ProperUtil.getSendUser();
			am.setSmtpHost(ProperUtil.getEmailSmtpHost());
			am.setAuth(false);
			am.setUsername(ProperUtil.getSendUserLoginName());
			am.setPassword(ProperUtil.getSendUserPswd());
			am.createSession();
			am.setAuth(true);
			am.setDebug(true);
			am.setFrom(sendEmail,ProperUtil.getSendUserName());
			am.setSubject("“«±Ì≈Ã-"+kpi_name+"‘§æØ–≈œ¢");

			String templateContent = ProperUtil.getEmailContentMap().get("dashWarnContent.txt");
			templateContent = templateContent.replaceAll("<!--kpi_name-->", kpi_name);
			templateContent = templateContent.replaceAll("<!--month_desc-->", month_desc);
			templateContent = templateContent.replaceAll("<!--product_name-->", product_name);
			templateContent = templateContent.replaceAll("<!--client_name-->", client_name);
			templateContent = templateContent.replaceAll("<!--sp_name-->", sp_name);
			templateContent = templateContent.replaceAll("<!--amount_curr-->", amount_curr);
			templateContent = templateContent.replaceAll("<!--warn_value-->", warn_value);
			am.setHtml(templateContent);
			am.setTo(userEmail);
			am.send();

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
}
