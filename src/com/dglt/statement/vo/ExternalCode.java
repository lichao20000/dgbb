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
package com.dglt.statement.vo;

/**
 * <pre>
 * 枚举�?
 * </pre>
 * 
 * @author Wu Gang
 * @version 1.00
 */
public class ExternalCode {
    public enum CodeTypeCode {
        /** 区域/部门 */
    	DEPARTMENTAREA("departmentArea");

        private String typeName;

        private CodeTypeCode(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /** 区域/部门 */
    public enum DepartmentArea implements CodeValueEnumIF {
        /** 城区分公�?*/
        CHENT_QU("00441730100");

        private String value;

        private DepartmentArea(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
