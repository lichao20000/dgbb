/* ========================================
 * System Name„Ä??ÔºöDGCUVM 
 * SubSystem Name ÔºöDGCUVM System
 * ----------------------------------------
 * Create Date/Change History 
 *
 * ----------------------------------------
 * 2014Âπ?Êú?Êó?„Ä?u Gang   Create
 * 
 * 
 * ----------------------------------------
 * Copyright 2013 Global Delivery Center,Deloitte Consulting All Rights Reserved.
 */
package com.dglt.statement.service;

import java.util.List;

import com.dglt.bb.pojo.TAppStation;
import com.dglt.comm.base.BaseService;



/**
 * <pre>
 * Êûö‰∏æÊé•Âè£
 * </pre>
 * 
 * @author Wu Gang
 * @version 1.00
 */
public interface AppStationService extends BaseService{

	 public TAppStation getAppStationNameByCode(String code);
	 
	 public List<TAppStation> getAppStations();
	
    public List<Object> getRecordByHql(String hql);
    
}
