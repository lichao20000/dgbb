package com.dglt.bb.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SchedulerJob extends QuartzJobBean {
	private MailSchedulerTask mailSchedulerTask;
	 
	public void setMailSchedulerTask(MailSchedulerTask mailSchedulerTask) {
		this.mailSchedulerTask = mailSchedulerTask;
	}
 
	//·¢ËÍÔ¤¾¯ÓÊ¼þ
	protected void executeInternal(JobExecutionContext context)
	throws JobExecutionException {
		mailSchedulerTask.sendWarnEmailScheduler();
 
	}
}
