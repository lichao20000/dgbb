package com.dglt.statement.service.impl;
/* ========================================
 * System Name�??：DGCUVM 
 * SubSystem Name ：DGCUVM System
 * ----------------------------------------
 * Create Date/Change History 
 *
 * ----------------------------------------
 * 2014�?�?�?�?u Gang   Create
 * 
 * 
 * ----------------------------------------
 * Copyright 2013 Global Delivery Center,Deloitte Consulting All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dglt.bb.pojo.TAppEnumItem;
import com.dglt.bb.pojo.TAppEnumValue;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.statement.service.AppEnumService;
import com.dglt.statement.vo.ExternalCode;
import com.eos.system.utility.StringUtil;


/**
 * <pre>
 * 枚举管理�?
 * </pre>
 * 
 * @author Wu Gang
 * @version 1.00
 */
@Service(value = "appEnumService")
public class AppEnumServiceImpl  extends BaseServiceImpl implements AppEnumService {

    /**
     * 根据codeType�?��枚举
     * 
     * @param codeType
     * @return
     */
    public TAppEnumItem getAppEnumItemByCodeType(String codeType) {
        TAppEnumItem result = null;

        // �?��条件
        String hql = "from TAppEnumItem where enabledFlag=1 and deletedFlag=0 and startActiveDate < sysdate and enumItemCode = '"
                + codeType + "'";
        List<TAppEnumItem> list = this.find(hql);
        if (list != null && list.size() > 0) {
            result = list.get(0);
        }

        return result;
    }

    /**
     * 根据codeType和codeValue�?��枚举值对�?
     * 
     * @param codeType
     * @param codeValue
     * @return
     */
    public TAppEnumValue getAppEnumValue(String codeType, String codeValue, String... scenes) {

        TAppEnumValue result = null;

        // �?��条件
        String hql = "from TAppEnumValue where enabledFlag=1 and deletedFlag=0 and startActiveDate < sysdate and enumItemCode = '"
                + codeType + "' and enumValue = '" + codeValue + "'";
        List<TAppEnumValue> list = this.find(hql);

        // 判断是否过滤数据
        if (scenes != null && scenes.length > 0) {
            list = getByScene(list, scenes);
        }

        if (list != null && list.size() > 0) {
            result = list.get(0);
        }

        return result;
    }

    /**
     * 根据codeType�?��枚举值对�?
     * 
     * @param codeType
     * @return
     */
    public List<TAppEnumValue> getAppEnumValues(String codeType, String... scenes) {

        // �?��条件
        String hql = "from TAppEnumValue where enabledFlag=1 and deletedFlag=0 and startActiveDate < sysdate and enumItemCode = '"
                + codeType + "' order by sortNum";
        List<TAppEnumValue> result = this.find(hql);

        if (scenes != null && scenes.length > 0) {
            result = getByScene(result, scenes);
        }

        return result;
    }

    
    /**
     * 根据codeType�?��和父值查询枚举�?对象
     * 
     * @param codeType
     * @param pValue 上级节点�?
     * @return
     */
    public List<TAppEnumValue> getAppEnumByParent(String codeType, String pValue) {
        // �?��条件
        String hql = "from TAppEnumValue where enabledFlag=1 and deletedFlag=0 and startActiveDate < sysdate and enumItemCode = '"
                + codeType + "' and  parentEnumValue ='"+pValue+"' order by sortNum";
        List<TAppEnumValue> result = this.find(hql);


        return result;
    }
    /**
     * 根据场景过滤
     * @param result
     * @param scenes
     * @return
     */
    private List<TAppEnumValue> getByScene(List<TAppEnumValue> result, String... scenes) {
        List<TAppEnumValue> newResult = new ArrayList<TAppEnumValue>();
        // 判断是否要根据parentValues过滤
        if (result != null && result.size() > 0 && scenes != null && scenes.length > 0) {

            String[] dbScenes = null;
            for (TAppEnumValue info : result) {
                if (!StringUtil.isEmpty(info.getScene())) {
                    if ("all".equals(info.getScene())) {
                        newResult.add(info);
                    } else {
                        dbScenes = info.getScene().split(",");
                        for (String dbScene : dbScenes) {
                            for (String scene : scenes) {
                                if (scene.equals(dbScene)) {
                                    newResult.add(info);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return newResult;
    }

    /**
     * 根据codeType�?��枚举项拼接出来的map
     * 
     * @param codeType
     * @return
     */
    public Map<String, String> getEnumValueMap(String codeType, String... scenes) {
        Map<String, String> result = new LinkedHashMap<String, String>();

        List<TAppEnumValue> list = this.getAppEnumValues(codeType, scenes);
        // 添加map�?
        if (list != null && list.size() > 0) {
            for (TAppEnumValue info : list) {
                result.put(info.getEnumValue(), info.getEnumValueMeaning());
            }
        }

        return result;
    }

    /**
     * 根据部门区域获取�?��的区域类�?
     * 
     * @param departmentArea
     * @return
     */
    public String getAreaType(String departmentArea) {
        String result = null;

        if (departmentArea == null) {
            departmentArea = "";
        }
        // �?��条件
        String hql = "from TAppEnumValue where enabledFlag=1 and deletedFlag=0 and startActiveDate < sysdate and enumItemCode = '"
                + ExternalCode.CodeTypeCode.DEPARTMENTAREA.getTypeName() + "' and enumValue = '" + departmentArea + "'";
        List<TAppEnumValue> list = this.find(hql);
        if (list != null && list.size() > 0) {
            result = list.get(0).getAttribute1();
        }

        return result;
    }

	@Override
	public List<Object> getRecordByHql(String hql) {
		// TODO Auto-generated method stub
		return null;
	}



}
