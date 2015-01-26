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
package com.dglt.statement.service;

import java.util.List;
import java.util.Map;

import com.dglt.bb.pojo.TAppEnumItem;
import com.dglt.bb.pojo.TAppEnumValue;
import com.dglt.comm.base.BaseService;



/**
 * <pre>
 * 枚举接口
 * </pre>
 * 
 * @author Wu Gang
 * @version 1.00
 */
public interface AppEnumService extends BaseService{

    /**
     * 根据codeType�?��枚举�?
     * 
     * @param codeType
     * @return
     */
    public TAppEnumItem getAppEnumItemByCodeType(String codeType);

    /**
     * 根据codeType和codeValue�?��枚举值对�?
     * @param codeType
     * @param codeValue
     * @return
     */
    public TAppEnumValue getAppEnumValue(String codeType, String codeValue, String... scenes);

    /**
     * 根据codeType�?��枚举项拼接出来的map
     * 
     * @param codeType
     * @return
     */
    public Map<String, String> getEnumValueMap(String codeType, String... scenes);

    /**
     * 根据codeType�?��枚举值对�?
     * 
     * @param codeType
     * @return
     */
    public List<TAppEnumValue> getAppEnumValues(String codeType, String... scenes);

    /**
     * 根据部门区域获取�?��的区域类�?
     * 
     * @param departmentArea
     * @return
     */
    public String getAreaType(String departmentArea);
    
    /***
     * 根据Hql来查询下拉菜单数�?
     * 
     */
    public List<Object> getRecordByHql(String hql);
    
    public List<TAppEnumValue> getAppEnumByParent(String codeType, String pValue);
}
