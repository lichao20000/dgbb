package com.dglt.bb.quartz;

import com.dglt.comm.email.EmailSendHelper;

/**
 * ��ʱ����Ԥ���ʼ�
 * 
 * @author Administrator
 * 
 */
public class MailSchedulerTask {

	private int num = 0;// ������

	public void sendWarnEmailScheduler() {
		   //�����ʼ�����
		   
		   if (num==0){
			   String userEmail = "tanwei@hsmd.com.cn";
			   String kpi_name = "�������Ԥ�������";
			   String month_desc = "2013��08��";
			   String product_name = "(OCS)UP������";
			   String client_name = "����";
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
