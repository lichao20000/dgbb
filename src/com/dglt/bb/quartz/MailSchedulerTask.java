package com.dglt.bb.quartz;

import com.dglt.comm.email.EmailSendHelper;

/**
 * 定时发送预警邮件
 * 
 * @author Administrator
 * 
 */
public class MailSchedulerTask {

	private int num = 0;// 测试用

	public void sendWarnEmailScheduler() {
		   //发送邮件代码
		   
		   if (num==0){
			   String userEmail = "tanwei@hsmd.com.cn";
			   String kpi_name = "收入进度预算完成率";
			   String month_desc = "2013年08月";
			   String product_name = "(OCS)UP新势力";
			   String client_name = "集客";
			   String sp_name = "3G";
			   String amount_curr = "0.666";
			   String warnValue = "0.9";
			   EmailSendHelper.SendDashWarnContent(userEmail, kpi_name, month_desc,
					   product_name, client_name, sp_name, amount_curr,warnValue);
			   System.out.println("sendMail + success ......");
			   num++;
		   }else{
			   System.out.println("sendMail + repeat  ......");
		   }
	   }
}
