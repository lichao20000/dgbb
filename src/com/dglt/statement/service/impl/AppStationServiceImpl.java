package com.dglt.statement.service.impl;
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

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dglt.bb.pojo.TAppStation;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.statement.service.AppStationService;


/**
 * <pre>
 * Âü∫Á´ôÁ±?
 * </pre>
 * 
 * @author lufing
 * @version 1.00
 */
@Service(value = "appStationService")
public class AppStationServiceImpl extends BaseServiceImpl implements AppStationService {

    /**
     * Ê†πÊçÆcodeTypeÊ£?¥¢Êûö‰∏æ
     * 
     * @param codeType
     * @return
     */
    public TAppStation getAppStationNameByCode(String code) {
    	TAppStation result = null;

        // Ê£?¥¢Êù°‰ª∂
        String hql = "from TAppStation where stationCode = '"
                + code + "'";
        List<TAppStation> list = this.find(hql);
        if (list != null && list.size() > 0) {
            result = list.get(0);
        }

        return result;
    }

    /**
     * Ê†πÊçÆcodeTypeÊ£?¥¢Êûö‰∏æÂÄºÂØπË±?
     * 
     * @param codeType
     * @return
     */
    public List<TAppStation> getAppStations() {

        String hql = "from TAppStation";
        List<TAppStation> result = this.find(hql);
        return result;
    }

    


	@Override
	public List<Object> getRecordByHql(String hql) {
		return null;
	}


}
