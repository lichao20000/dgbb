package com.dglt.statement.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.stereotype.Controller;
import com.dglt.base.util.SpringUtil;
import com.dglt.bb.pojo.TAppEnumValue;
import com.dglt.bb.pojo.TAppStation;
import com.dglt.comm.util.XMLProperties;
import com.dglt.statement.service.AppEnumService;
import com.dglt.statement.service.AppStationService;
import com.eos.system.utility.StringUtil;
import com.unicom.ucloud.eom.base.util.tagutil.StaticWorksheetMethod;


/**
 * <p>
 * Title:字典标签
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-12-18 10:12:00
 * </p>
 * 
 * @author
 * @version 1.0
 * 
 */
@Controller
public class XMLDictTagLocal extends TagSupport {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 列表显示用（迭带的）
     */
    protected String id = null;

    /**
     * 字典名称
     */
    protected String dictId = null;
    /**
     * 属�?名称
     */
    protected String name = null;

    /**
     * �?��CommonMain、Commonlink
     */
    protected String field = null;

    /**
     * �?��域名
     */
    protected String domain = null;

    /**
     * 显示还是输入标示（DETAIL显示 NEW输入 DRAFT修改�?
     */
    protected String showFlag = null;

    /**
     * 字典文件�?
     */
    protected String fileName = null;

    /**
     * 属�?�?
     */
    protected String value = null;
    protected String onChange = null;

    /**
     * 是否必填
     */
    protected String mustFlag = null;
    protected String childId = null;

    /**
     * hint
     */
    protected String hint = null;

    /**
     * 是否必填
     */
    protected String hiddenValue = null;

    /**
     * 数据源�?�?
     */
    protected String dataFrom = null;

    /**
     * 父下拉框相关
     */
    protected String parentId = null;
    protected String parentValue = null;

    // protected String contextId = WebDriver.REQUEST_REQUEST_CONTEXT;
    protected String contextId = "";

    /**
     * 场景
     */
    protected String scene = null;
    
    protected String childCode = null;


    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(generateHtml());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public String generateHtml() {
        // 取数判断
        if ("XML".equals(dataFrom)) {
            return generateHtml4XML();
        }else if("BS".equals(dataFrom)){//特殊基站
            return generateHtml4BS();
        }else {
            return generateHtml4DB();
        }
    }

    /**
     * 从xml取数
     * 
     * @return
     */
    public String generateHtml4XML() {
        StringBuffer html = new StringBuffer();
        XMLProperties xmlPro = new XMLProperties();
        // 要取的节�?
        String dictStr = dictId + ";item";
        List dictList = xmlPro.getProperty(fileName + ".xml", dictStr, "id;name;pid");
        // 组织属�?名称
        String propName = "";
        String propId = "";
        if (onChange == null) {
            onChange = "";
        }

        if (childId == null) {
            childId = "";
        }

        if (showFlag != null && !showFlag.equals("") && showFlag.equals("NEW")) {// 输入�?
            propName = name;
            if (id == null || "".equals(id)) {
                propId = dictId;
            } else {
                propId = id;
            }
            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">--��ѡ��-</span>");
            if (null != mustFlag && !"".equals(mustFlag.trim())) {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\"  name=\"" + propName
                        + "\" onChange=\"" + onChange + "\"  dfValidate=\"" + mustFlag + "\" childId=\"" + childId
                        + "\" scene=\""+StaticWorksheetMethod.nullObject2String(scene) + "\"");
            } else {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\"  name=\"" + name + "\" onChange=\""
                        + onChange + "\" childId=\"" + childId + "\" scene=\""+StaticWorksheetMethod.nullObject2String(scene)+"\"");
            }
            if(hint != null){                	
            	html.append(" title=\"" + hint + "\" ");
            }
            html.append(">");
            html.append("<option value=\"" + "\">" + "</option>");

            for (int i = 0; i < dictList.size(); i++) {
                HashMap map = (HashMap) dictList.get(i);
                String parentValues = StaticWorksheetMethod.nullObject2String(map.get("pid"));
                if (canAdd(parentValues)) {
                    html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                }
            }

            html.append("</select>");
            html.append("</div>");
        } else if (showFlag != null && !showFlag.equals("") && showFlag.equals("DETAIL")) {// 显示�?
            if (id == null) {
                for (int j = 0; j < dictList.size(); j++) {
                    HashMap map = (HashMap) dictList.get(j);
                    if (map != null && map.get("id") != null && map.get("name") != null) { // 修改
                        if (map.get("id").equals(value)) {
                            html.append(map.get("name").toString());
                            break;
                        }
                    }
                }
            } else {
                for (int j = 0; j < dictList.size(); j++) {
                    String dictPage = "";
                    HashMap map = (HashMap) dictList.get(j);
                    if (map != null && map.get("id") != null && map.get("name") != null) {
                        if (map.get("id").equals(value)) {
                            html.append(map.get("name").toString());
                            break;
                        }
                    }
                }

            }
        } else {// 草稿

            propName = name;
            if (id == null || "".equals(id)) {
                propId = dictId;
            } else {
                propId = id;
            }

            String dname = "--��ѡ��--";
            for (int k = 0; k < dictList.size(); k++) {
                HashMap map1 = (HashMap) dictList.get(k);
                if (map1 != null && map1.get("id") != null && map1.get("name") != null) {
                    if (map1.get("id").equals(value)) {
                        dname = (String) map1.get("name");
                    }
                }
            }

            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">" + dname + "</span>");
            if (null != mustFlag && !"".equals(mustFlag.trim())) {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\" name=\"" + propName + "\" onChange=\""
                        + onChange + "\" dfValidate=\"" + mustFlag + "\" childId=\"" + childId + "\"" +
                        		" scene=\""+StaticWorksheetMethod.nullObject2String(scene)+"\"");
            } else {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\"  name=\"" + name + "\" onChange=\""
                        + onChange + "\" childId=\"" + childId + "\"" +
                        		" scene=\""+StaticWorksheetMethod.nullObject2String(scene)+"\"");
            }
            if(hint != null){                	
            	html.append(" title=\"" + hint + "\" ");
            }
            html.append(">");
            html.append("<option value=\"" + "\">" + "</option>");
            for (int j = 0; j < dictList.size(); j++) {
                HashMap map = (HashMap) dictList.get(j);
                String parentValues = StaticWorksheetMethod.nullObject2String(map.get("pid"));
                if (canAdd(parentValues)) {
                    if (map != null && map.get("id") != null && map.get("name") != null) {
                        if (map.get("id").equals(value)) {
                            html.append("<option value=\"" + map.get("id") + "\" selected>" + map.get("name")
                                    + "</option>");
                        } else {
                            html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                        }
                    }
                }
            }
            html.append("</select>");
            html.append("</div>");
        }

        String result = html.toString();
        if (field != null && field.equals("Common")) {// 动�?域中的字典标签，�?��将单引号'转换成\'
            result = result.replaceAll("\'", "\\\\'");
        }

        return result;
    }

    /**
     * 判断是否可以添加
     * @param parentValue
     * @param parentValues
     * @return
     */
    protected boolean canAdd(String parentValues){
        boolean can = false;
        
        // 没有父的时�?--可以
        if(StringUtil.isEmpty(parentId)){
            can = true;
        }

        // 有父并且父有值的时�?--显示配置有父的项
        if (!StringUtil.isEmpty(parentId) && !StringUtil.isEmpty(parentValue)) {
            if ("all".equals(parentValues)) {
                can = true;
            } else {
                String[] pids = parentValues.split(",");
                for (String id : pids) {
                    if (parentValue.equals(id)) {
                        can = true;
                        break;
                    }
                }
            }
        }

        return can;
    }

    /**
     * 从数据库取数
     * 
     * @return
     */
    public String generateHtml4DB() {
        StringBuffer html = new StringBuffer();
        // XMLProperties xmlPro = new XMLProperties();
        // 要取的节�?
        // String dictStr = dictId + ";item";
        // List dictList = xmlPro.getProperty(fileName + ".xml", dictStr,
        // "id;name;pid");
        // 枚举�?
        ServletContext servletContext = this.pageContext.getServletContext();
        AppEnumService appEnumService = (AppEnumService)SpringUtil.getBean("appEnumService");
//        appEnumService = (AppEnumService) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getBean("appEnumService");
        // 枚举�?
        List<TAppEnumValue> appEnumValueList = null;
        if (StringUtil.isEmpty(scene)) {
            appEnumValueList = appEnumService.getAppEnumValues(dictId);
        } else {
            String[] strs = scene.split(",");
            appEnumValueList = appEnumService.getAppEnumValues(dictId, strs);
        }

        if (appEnumValueList == null) {
            appEnumValueList = new ArrayList<TAppEnumValue>();
        }
        TAppEnumValue appEnumValue = null;
        // 组织属�?名称
        String propName = "";
        String propId = "";
        if (onChange == null) {
            onChange = "";
        }

        if (childId == null) {
            childId = "";
        }
        if(childCode == null){
        	childCode = "";
        }

        if (showFlag != null && !showFlag.equals("") && showFlag.equals("NEW")) {// 输入�?
            propName = name;
            if (id == null || "".equals(id)) {
                propId = dictId;
            } else {
                propId = id;
            }
            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">--��ѡ��-</span>");
            if (null != mustFlag && !"".equals(mustFlag.trim())) {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\"  name=\"" + propName
                        + "\" onChange=\"" + onChange + "\"  dfValidate=\"" + mustFlag + "\" childId=\"" + childId
                        + "\" childCode=\""+childCode+"\" scene=\""+StaticWorksheetMethod.nullObject2String(scene) + "\"");
            } else {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\"  name=\"" + name + "\" onChange=\""
                        + onChange + "\" childId=\"" + childId + "\" childCode=\""+childCode+"\" scene=\""+StaticWorksheetMethod.nullObject2String(scene)+"\"");
            }
            if(hint != null){                	
            	html.append(" title=\"" + hint + "\" ");
            }
            html.append(">");
            

            html.append("<option value=\"" + "\">" + "</option>");
            for (int i = 0; i < appEnumValueList.size(); i++) {
                appEnumValue = appEnumValueList.get(i);

                String parentValues = StaticWorksheetMethod.nullObject2String(appEnumValue.getParentEnumValue());
                if (canAdd(parentValues)) {
                    html.append("<option value=\"" + appEnumValue.getEnumValue() + "\">"
                            + appEnumValue.getEnumValueMeaning() + "</option>");
                }
            }
            html.append("</select>");
            html.append("</div>");
        } else if (showFlag != null && !showFlag.equals("") && showFlag.equals("DETAIL")) {// 显示�?

            for (int j = 0; j < appEnumValueList.size(); j++) {
                appEnumValue = appEnumValueList.get(j);
                // 修改
                if (appEnumValue != null && appEnumValue.getEnumValue().equals(value)) {
                    html.append(appEnumValue.getEnumValueMeaning());
                    break;
                }
            }

        } else {// 草稿

            propName = name;
            if (id == null || "".equals(id)) {
                propId = dictId;
            } else {
                propId = id;
            }

            String dname = "--��ѡ��--";
            for (int k = 0; k < appEnumValueList.size(); k++) {
                appEnumValue = appEnumValueList.get(k);
                if (appEnumValue != null && appEnumValue.getEnumValue() != null
                        && appEnumValue.getEnumValueMeaning() != null) {
                    if (appEnumValue.getEnumValue().equals(value)) {
                        dname = appEnumValue.getEnumValueMeaning();
                    }
                }
            }

            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">" + dname + "</span>");
            if (null != mustFlag && !"".equals(mustFlag.trim())) {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\" name=\"" + propName + "\" onChange=\""
                        + onChange + "\" dfValidate=\"" + mustFlag + "\" childId=\"" + childId + "\"" +
                        		" childCode=\""+childCode+"\" scene=\""+StaticWorksheetMethod.nullObject2String(scene)+"\"");
            } else {
                html.append("<select class=\"inputselect\" id=\"" + propId + "\"  name=\"" + name + "\" onChange=\""
                        + onChange + "\" childId=\"" + childId + "\"" +
                        		" childCode=\""+childCode+"\" scene=\""+StaticWorksheetMethod.nullObject2String(scene)+"\"");
            }
            if(hint != null){                	
            	html.append(" title=\"" + hint + "\" ");
            }
            html.append(">");
            html.append("<option value=\"" + "\">" + "</option>");
            for (int j = 0; j < appEnumValueList.size(); j++) {
                appEnumValue = appEnumValueList.get(j);
                String parentValues = StaticWorksheetMethod.nullObject2String(appEnumValue.getParentEnumValue());
                if (canAdd(parentValues)) {
                    if (appEnumValue != null && appEnumValue.getEnumValue() != null
                            && appEnumValue.getEnumValueMeaning() != null) {
                        if (appEnumValue.getEnumValue().equals(value)) {
                            html.append("<option value=\"" + appEnumValue.getEnumValue() + "\" selected>"
                                    + appEnumValue.getEnumValueMeaning() + "</option>");
                        } else {
                            html.append("<option value=\"" + appEnumValue.getEnumValue() + "\">"
                                    + appEnumValue.getEnumValueMeaning() + "</option>");
                        }
                    }
                }
            }
            html.append("</select>");
            html.append("</div>");
        }

        String result = html.toString();
        if (field != null && field.equals("Common")) {// 动�?域中的字典标签，�?��将单引号'转换成\'
            result = result.replaceAll("\'", "\\\\'");
        }

        return result;
    }
    
    /**
     * 从数据库中的基站表取�?
     * 
     * @return
     */
    public String generateHtml4BS() {
        StringBuffer html = new StringBuffer();
        
//        appStationService = (AppStationService) WebApplicationContextUtils.getRequiredWebApplicationContext(
//                this.pageContext.getServletContext()).getBean("appStationService");
        AppStationService appStationService = (AppStationService)SpringUtil.getBean("appStationService");
        // 枚举�?
        List<TAppStation> appStationValueList = appStationService.getAppStations();

        if (appStationValueList == null) {
        	appStationValueList = new ArrayList<TAppStation>();
        }
        TAppStation appStation = null;

        if (showFlag != null && !showFlag.equals("") && showFlag.equals("DETAIL")) {// 显示�?

            for (int j = 0; j < appStationValueList.size(); j++) {
            	appStation = appStationValueList.get(j);
                // 修改
                if (appStation != null && appStation.getStationCode().equals(value)) {
                    html.append(appStation.getStationName());
                    break;
                }
            }

        }

        String result = html.toString();

        return result;
    }
    
    

    public static void main(String[] args) {
        XMLProperties xmlPro = new XMLProperties();
        List dictList = xmlPro.getProperty("dict-wfworksheet.xml", "faultClass", "id;name");
        // System.out.println(dictList.size());
    }

    public String getDictString4DynamicDomain(String fileName, String dictId, String showFlag, String field, String value, int index, String name, String dataFrom, AppEnumService appEnumService) {
     // 取数判断
        if ("XML".equals(dataFrom)) {
            return this.getXMLDictString(fileName, dictId, showFlag, field, value, index, name);
        } else {
            return this.getDBDictString(fileName, dictId, showFlag, field, value, index, name, appEnumService);
        }
    }

    /**
     * 用于动�?域中
     * 
     * @return
     */
    public String getXMLDictString(String fileName, String dictId, String showFlag, String field, String value, int index, String name) {

        StringBuffer html = new StringBuffer();
        XMLProperties xmlPro = new XMLProperties();
        // 要取的节�?
        String dictStr = dictId + ";item";
        List dictList = xmlPro.getProperty(fileName + ".xml", dictStr, "id;name;pid");
        // 组织属�?名称
        String propName = "";

        if (showFlag != null && !showFlag.equals("") && showFlag.equalsIgnoreCase("NEW")) {// 输入�?
            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">--��ѡ��-</span>");

            html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "["+index+"]." + name
                    + "\" onblur=\"inputnotnull(this)\">");
            html.append("<option value=\"" + "\">" + "</option>");
            for (int i = 0; i < dictList.size(); i++) {
                HashMap map = (HashMap) dictList.get(i);

                String parentValues = StaticWorksheetMethod.nullObject2String(map.get("pid"));
                if (canAdd(parentValues)) {
                    html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                    // System.out.println(html.toString());
                }
            }
            html.append("</select>");
            html.append("</div>");
        } else if (showFlag != null && !showFlag.equals("") && showFlag.equalsIgnoreCase("DETAIL")) {// 显示�?
            if (id == null) {
                for (int j = 0; j < dictList.size(); j++) {
                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }
            } else {
                for (int j = 0; j < dictList.size(); j++) {

                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }

            }
        } else {// 草稿
            String dname = "--��ѡ��-";
            for (int k = 0; k < dictList.size(); k++) {
                HashMap map1 = (HashMap) dictList.get(k);
                if (map1.get("id").equals(value)) {
                    dname = (String) map1.get("name");
                }
            }
            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">" + dname + "</span>");
            html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "["+index+"]." + name + "\">");
            html.append("<option value=\"" + "\">" + "</option>");
            for (int j = 0; j < dictList.size(); j++) {

                HashMap map = (HashMap) dictList.get(j);
                String parentValues = StaticWorksheetMethod.nullObject2String(map.get("pid"));
                if (canAdd(parentValues)) {
                    if (map.get("id").equals(value)) {
                        // html.append(map.get("name").toString());
                        html.append("<option value=\"" + map.get("id") + "\" selected>" + map.get("name") + "</option>");

                    } else {
                        html.append("<option value=\"" + map.get("id") + "\" >" + map.get("name") + "</option>");
                    }
                }
            }
            html.append("</select>");

        }
        // 动�?域中的字典标签，�?��将单引号'转换成\'
        String result = html.toString();

        return result;
    }

    /**
     * 用于动�?域中
     * 
     * @return
     */
    public String getDBDictString(String fileName, String dictId, String showFlag, String field, String value, int index, String name, AppEnumService appEnumService) {

        StringBuffer html = new StringBuffer();
        // 枚举�?
        List<TAppEnumValue> appEnumValueList = null;
        if (StringUtil.isEmpty(scene)) {
            appEnumValueList = appEnumService.getAppEnumValues(dictId);
        } else {
            String[] strs = scene.split(",");
            appEnumValueList = appEnumService.getAppEnumValues(dictId, strs);
        }

        if (appEnumValueList == null) {
            appEnumValueList = new ArrayList<TAppEnumValue>();
        }
        TAppEnumValue appEnumValue = null;

        if (showFlag != null && !showFlag.equals("") && showFlag.equalsIgnoreCase("NEW")) {// 输入�?
            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">--��ѡ��-</span>");

            html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "["+index+"]." + name
                    + "\" onblur=\"inputnotnull(this)\">");
            html.append("<option value=\"" + "\">" + "</option>");
            for (int i = 0; i < appEnumValueList.size(); i++) {
                appEnumValue = appEnumValueList.get(i);

                String parentValues = StaticWorksheetMethod.nullObject2String(appEnumValue.getParentEnumValue());
                if (canAdd(parentValues)) {
                    html.append("<option value=\"" + appEnumValue.getEnumValue() + "\">"
                            + appEnumValue.getEnumValueMeaning() + "</option>");
                    // System.out.println(html.toString());
                }
            }
            html.append("</select>");
            html.append("</div>");
        } else if (showFlag != null && !showFlag.equals("") && showFlag.equalsIgnoreCase("DETAIL")) {// 显示�?
            if (id == null) {
                for (int i = 0; i < appEnumValueList.size(); i++) {
                    appEnumValue = appEnumValueList.get(i);
                    if (appEnumValue.getEnumValue().equals(value)) {
                        html.append(appEnumValue.getEnumValueMeaning().toString());
                        break;
                    }
                }
            } else {
                for (int i = 0; i < appEnumValueList.size(); i++) {
                    appEnumValue = appEnumValueList.get(i);
                    if (appEnumValue.getEnumValue().equals(value)) {
                        html.append(appEnumValue.getEnumValueMeaning().toString());
                        break;
                    }
                }

            }
        } else {// 草稿
            String dname = "--��ѡ��-";
            for (int i = 0; i < appEnumValueList.size(); i++) {
                appEnumValue = appEnumValueList.get(i);
                if (appEnumValue.getEnumValue().equals(value)) {
                    dname = (String) appEnumValue.getEnumValueMeaning();
                }
            }
            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">" + dname + "</span>");
            html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "["+index+"]." + name + "\">");
            html.append("<option value=\"" + "\">" + "</option>");
            for (int i = 0; i < appEnumValueList.size(); i++) {
                appEnumValue = appEnumValueList.get(i);

                String parentValues = StaticWorksheetMethod.nullObject2String(appEnumValue.getParentEnumValue());
                if (canAdd(parentValues)) {
                    if (appEnumValue.getEnumValue().equals(value)) {
                        // html.append(map.get("name").toString());
                        html.append("<option value=\"" + appEnumValue.getEnumValue() + "\" selected>"
                                + appEnumValue.getEnumValueMeaning() + "</option>");

                    } else {
                        html.append("<option value=\"" + appEnumValue.getEnumValue() + "\" >"
                                + appEnumValue.getEnumValueMeaning() + "</option>");
                    }
                }
            }
            html.append("</select>");

        }
        // 动�?域中的字典标签，�?��将单引号'转换成\'
        String result = html.toString();

        return result;
    }

    /**
     * 用于动�?域中，主要是用在多组动�?域拼接成的js
     * 
     * @return
     * @author zhyoy
     */
    public String getXMLDictStringToJs(String fileName, String dictId, String showFlag, String field, String value,
            String[] cnname) {

        StringBuffer html = new StringBuffer();
        XMLProperties xmlPro = new XMLProperties();
        // 要取的节�?
        String dictStr = dictId + ";item";
        List dictList = xmlPro.getProperty(fileName + ".xml", dictStr, "id;name;pid");
        // 组织属�?名称
        String propName = "";

        if (showFlag != null && !showFlag.equals("") && showFlag.equalsIgnoreCase("NEW")) {// 输入�?
            html.append("<div class=\"sel_wrap\">");
            html.append("<span class=\"selectspan\">--��ѡ��-</span>");
            if (cnname.length > 9) {// 四列动�?�?
                html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "[\'+i+\']."
                        + dictId + "\" onblur=\"inputnotnull(this)\">");
                html.append("<option value=\"" + "\">" + "</option>");
            } else {
                if (field.equals("tEomCmNeInfoVONew")) {
                    html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "[\'+n+\']."
                            + dictId + "\" onblur=\"inputnotnull(this)\">");
                } else if (field.equals("tEomCmNeInfoVOPre")) {
                    html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "[\'+p+\']."
                            + dictId + "\" onblur=\"inputnotnull(this)\">");
                } else {
                    html.append("<select class=\"inputselect\" id=\"" + dictId + "\" name=\"" + field + "[\'+k+\']."
                            + dictId + "\" onblur=\"inputnotnull(this)\">");
                }

                html.append("<option value=\"" + "\">" + "</option>");
            }

            for (int i = 0; i < dictList.size(); i++) {
                HashMap map = (HashMap) dictList.get(i);

                String parentValues = StaticWorksheetMethod.nullObject2String(map.get("pid"));
                if (canAdd(parentValues)) {
                    html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                }
            }
            html.append("</select>");
            html.append("</div>");
        } else if (showFlag != null && !showFlag.equals("") && showFlag.equals("DETAIL")) {// 显示�?
            if (id == null) {
                for (int j = 0; j < dictList.size(); j++) {
                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }
            } else {
                for (int j = 0; j < dictList.size(); j++) {

                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }

            }
        } else {// 草稿

            html.append("<select id=\"" + dictId + "\" name=\"" + field + "\">");
            for (int j = 0; j < dictList.size(); j++) {

                HashMap map = (HashMap) dictList.get(j);
                String parentValues = StaticWorksheetMethod.nullObject2String(map.get("pid"));
                if (canAdd(parentValues)) {
                    if (map.get("id").equals(value)) {
                        // html.append(map.get("name").toString());
                        html.append("<option value=\"" + map.get("id") + "\" selected>" + map.get("name") + "</option>");

                    } else {
                        html.append("<option value=\"" + map.get("id") + "\" >" + map.get("name") + "</option>");
                    }
                }
            }
            html.append("</select>");

        }
        // 动�?域中的字典标签，�?��将单引号'转换成\'
        String result = html.toString();

        return result;
    }

    /**
     * 用于动�?域中,支持onChange函数[uf_checkbox(value,i)]
     * 
     * @return
     */
    public String getXMLDictString(String fileName, String dictId, String showFlag, String field, String value,
            int index) {

        StringBuffer html = new StringBuffer();
        XMLProperties xmlPro = new XMLProperties();
        // 要取的节�?
        String dictStr = dictId + ";item";
        List dictList = xmlPro.getProperty(fileName + ".xml", dictStr, "id;name");
        // 组织属�?名称
        String propName = "";

        if (showFlag != null && !showFlag.equals("") && showFlag.equals("NEW")) {// 输入�?

            html.append("<select name=\"" + field + "\" onChange=\"uf_checkbox(this.value,'" + index + "')\">");
            for (int i = 0; i < dictList.size(); i++) {
                HashMap map = (HashMap) dictList.get(i);

                html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                // System.out.println(html.toString());
            }
            html.append("</select>");

        } else if (showFlag != null && !showFlag.equals("") && showFlag.equals("DETAIL")) {// 显示�?
            if (id == null) {
                for (int j = 0; j < dictList.size(); j++) {
                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }
            } else {
                for (int j = 0; j < dictList.size(); j++) {

                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }

            }
        } else {// 草稿

            html.append("<select name=\"" + field + "\" onChange=\"uf_checkbox(this.value,'" + index + "')\">");
            for (int j = 0; j < dictList.size(); j++) {

                HashMap map = (HashMap) dictList.get(j);
                if (map.get("id").equals(value)) {
                    // html.append(map.get("name").toString());
                    html.append("<option value=\"" + map.get("id") + "\" selected>" + map.get("name") + "</option>");

                } else {
                    html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                }
            }
            html.append("</select>");
            // System.out.println(html.toString());
        }
        // 动�?域中的字典标签，�?��将单引号'转换成\'
        String result = html.toString();

        return result;
    }

    /**
     * 用于动�?域中,支持字典联动
     * 
     * @return
     */
    public String getXMLDictString(String fileName, String dictId, String showFlag, String field, String value,
            String childId, String propName, String domainEntity, String dictLevel, int index) {

        StringBuffer html = new StringBuffer();
        XMLProperties xmlPro = new XMLProperties();
        // 要取的节�?
        String dictStr = dictId + ";item";
        List dictList = xmlPro.getProperty(fileName + ".xml", dictStr, "id;name");
        // 组织属�?名称
        // String propName = "";

        if (showFlag != null && !showFlag.equals("") && showFlag.equals("NEW")) {// 输入�?
            if (dictLevel.equals("root")) {
                html.append("<select id=\""
                        + field
                        + "\"  name=\""
                        + field
                        + "\" bizAction=\"eoms_comm.bizDict.bizQueryRootXmlDict\""
                        + "paramXML=\"<xmlDict><filename>"
                        + fileName
                        + ".xml"
                        + "</filename><dictpath>"
                        + dictId
                        + ";item</dictpath><subdictpath>"
                        + childId
                        + ";item</subdictpath></xmlDict>\""
                        + "listXpath=\"list\" entityName=\"item\" valueField=\"id\" textField=\"name\" childID=\"CommonLink/dyn/sub[@num='"
                        + propName + "'][@entity='" + domainEntity + "']/group[@num='" + index + "']/" + domainEntity
                        + "/" + childId + "\"  nullLable=\"false\"" + "nullLableText=\"--------��ѡ��-------\""
                        + "nullLableValue=\"-1\" styleClass=\"txt\"" + "onChange=\"refreshChild_DS(this)\"></select>");
                html.append("<script type=\"text/javascript\">initSel_DS($(\"" + field + "\"));</script>");
            } else if (dictLevel.equals("sub")) {
                html.append("<select id=\""
                        + field
                        + "\"  name=\""
                        + field
                        + "\" bizAction=\"eoms_comm.bizDict.bizQuerySubXmlDict\""
                        + "paramXML=\"<xmlDict><filename>"
                        + fileName
                        + ".xml"
                        + "</filename><subdictpath>"
                        + childId
                        + ";item</subdictpath></xmlDict>\""
                        + "listXpath=\"list\" entityName=\"item\" valueField=\"id\" textField=\"name\" childID=\"CommonLink/dyn/sub[@num='"
                        + propName + "'][@entity='" + domainEntity + "']/group[@num='" + index + "']/" + domainEntity
                        + "/" + childId + "\"  mainSelectID=\"\"  nullLable=\"true\""
                        + "nullLableText=\"--------��ѡ��-------\"" + "nullLableValue=\"\" styleClass=\"txt\""
                        + "onChange=\"refreshChild_DS(this);otherChange(this.value,'" + index + "')\"></select>");
                html.append("<script type=\"text/javascript\">initSel_DS($(\"" + field + "\"));</script>");
            } else if (dictLevel.equals("end")) {
                html.append("<select id=\""
                        + field
                        + "\"  name=\""
                        + field
                        + "\" bizAction=\"eoms_comm.bizDict.bizQuerySubXmlDict\""
                        + "paramXML=\"\""
                        + "listXpath=\"list\" entityName=\"item\" valueField=\"id\" textField=\"name\" childID=\"\"  mainSelectID=\"\"  nullLable=\"true\""
                        + "nullLableText=\"--------��ѡ��-------\"" + "nullLableValue=\"\" styleClass=\"txt\""
                        + "onChange=\"\"></select>");
                html.append("<script type=\"text/javascript\">initSel_DS($(\"" + field + "\"));</script>");
            } else {
                html.append("<select name=\"" + field + "\">");

                for (int i = 0; i < dictList.size(); i++) {
                    HashMap map = (HashMap) dictList.get(i);

                    html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                    // System.out.println(html.toString());
                }
                html.append("</select>");
            }

        } else if (showFlag != null && !showFlag.equals("") && showFlag.equals("DETAIL")) {// 显示�?
            if (id == null) {
                for (int j = 0; j < dictList.size(); j++) {
                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }
            } else {
                for (int j = 0; j < dictList.size(); j++) {

                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        html.append(map.get("name").toString());
                        break;
                    }
                }

            }
        } else {// 草稿
            if (dictLevel.equals("root")) {
                html.append("<select id=\""
                        + field
                        + "\"  name=\""
                        + field
                        + "\" bizAction=\"eoms_comm.bizDict.bizQueryRootXmlDict\""
                        + "paramXML=\"<xmlDict><filename>"
                        + fileName
                        + ".xml"
                        + "</filename><dictpath>"
                        + dictId
                        + ";item</dictpath><subdictpath>"
                        + childId
                        + ";item</subdictpath></xmlDict>\""
                        + "listXpath=\"list\" entityName=\"item\" valueField=\"id\" textField=\"name\" childID=\"CommonLink/dyn/sub[@num='"
                        + propName + "'][@entity='" + domainEntity + "']/group[@num='" + index + "']/" + domainEntity
                        + "/" + childId + "\"  nullLable=\"false\"" + "nullLableText=\"--------��ѡ��-------\""
                        + "nullLableValue=\"-1\" styleClass=\"txt\" " + "onChange=\"refreshChild_DS(this)\"></select>");
                html.append("<script type=\"text/javascript\">initSel_DS($(\"" + field + "\"));"
                        + "document.getElementById(\"" + field + "\").value=\"" + value + "\";"
                        + "refreshChild_DS(document.getElementById(\"" + field + "\"));</script>");
            } else if (dictLevel.equals("sub")) {
                html.append("<select id=\""
                        + field
                        + "\"  name=\""
                        + field
                        + "\" bizAction=\"eoms_comm.bizDict.bizQuerySubXmlDict\""
                        + "paramXML=\"<xmlDict><filename>"
                        + fileName
                        + ".xml"
                        + "</filename><subdictpath>"
                        + childId
                        + ";item</subdictpath></xmlDict>\""
                        + "listXpath=\"list\" entityName=\"item\" valueField=\"id\" textField=\"name\" childID=\"CommonLink/dyn/sub[@num='"
                        + propName + "'][@entity='" + domainEntity + "']/group[@num='" + index + "']/" + domainEntity
                        + "/" + childId + "\"  mainSelectID=\"\"  nullLable=\"true\""
                        + "nullLableText=\"--------��ѡ��-------\"" + "nullLableValue=\"\" styleClass=\"txt\" "
                        + "onChange=\"refreshChild_DS(this);otherChange(this.value,'" + index + "')\"></select>");
                html.append("<script type=\"text/javascript\">document.getElementById(\"" + field + "\").value=\""
                        + value + "\";refreshChild_DS(document.getElementById(\"" + field + "\"));</script>");
            } else if (dictLevel.equals("end")) {
                html.append("<select id=\""
                        + field
                        + "\"  name=\""
                        + field
                        + "\" bizAction=\"eoms_comm.bizDict.bizQuerySubXmlDict\""
                        + "paramXML=\"\""
                        + "listXpath=\"list\" entityName=\"item\" valueField=\"id\" textField=\"name\" childID=\"\"  mainSelectID=\"\"  nullLable=\"true\""
                        + "nullLableText=\"--------��ѡ��-------\"" + "nullLableValue=\"\" styleClass=\"txt\" "
                        + "onChange=\"\"></select>");
                html.append("<script type=\"text/javascript\">document.getElementById(\"" + field + "\").value=\""
                        + value + "\";</script>");
            } else {
                html.append("<select name=\"" + field + "\">");
                for (int j = 0; j < dictList.size(); j++) {

                    HashMap map = (HashMap) dictList.get(j);
                    if (map.get("id").equals(value)) {
                        // html.append(map.get("name").toString());
                        html.append("<option value=\"" + map.get("id") + "\" selected>" + map.get("name") + "</option>");

                    } else {
                        html.append("<option value=\"" + map.get("id") + "\">" + map.get("name") + "</option>");
                    }
                }
                html.append("</select>");
                // System.out.println(html.toString());
            }
        }
        // 动�?域中的字典标签，�?��将单引号'转换成\'
        String result = html.toString();

        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    /**
     * @return the childId
     */
    public String getChildId() {
        return childId;
    }

    /**
     * @param childId
     *            the childId to set
     */
    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    /**
     * <pre>
     * parentId的取�?
     * </pre>
     * @return parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * <pre>
     * parentId的设�?
     * </pre>
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * <pre>
     * parentValue的取�?
     * </pre>
     * @return parentValue
     */
    public String getParentValue() {
        return parentValue;
    }

    /**
     * <pre>
     * parentValue的设�?
     * </pre>
     * @param parentValue the parentValue to set
     */
    public void setParentValue(String parentValue) {
        this.parentValue = parentValue;
    }

    /**
     * <pre>
     * mustFlag的取�?
     * </pre>
     * @return mustFlag
     */
    public String getMustFlag() {
        return mustFlag;
    }

    /**
     * <pre>
     * mustFlag的设�?
     * </pre>
     * @param mustFlag the mustFlag to set
     */
    public void setMustFlag(String mustFlag) {
        this.mustFlag = mustFlag;
    }

    /**
     * <pre>
     * hiddenValue的取�?
     * </pre>
     * @return hiddenValue
     */
    public String getHiddenValue() {
        return hiddenValue;
    }

    /**
     * <pre>
     * hiddenValue的设�?
     * </pre>
     * @param hiddenValue the hiddenValue to set
     */
    public void setHiddenValue(String hiddenValue) {
        this.hiddenValue = hiddenValue;
    }

    /**
     * <pre>
     * contextId的取�?
     * </pre>
     * @return contextId
     */
    public String getContextId() {
        return contextId;
    }

    /**
     * <pre>
     * contextId的设�?
     * </pre>
     * @param contextId the contextId to set
     */
    public void setContextId(String contextId) {
        this.contextId = contextId;
    }



    /**
     * <pre>
     * scene的取�?
     * </pre>
     * @return scene
     */
    public String getScene() {
        return scene;
    }

    /**
     * <pre>
     * scene的设�?
     * </pre>
     * @param scene the scene to set
     */
    public void setScene(String scene) {
        this.scene = scene;
    }

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

}
