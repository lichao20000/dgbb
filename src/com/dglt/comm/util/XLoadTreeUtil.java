/**
 * author:Â¬±óêÍ
 * date:2011-03-02
 */
package com.dglt.comm.util;

import org.json.JSONObject;
public class XLoadTreeUtil
{
    public static StringBuffer makeItemXML(String id,String code,JSONObject data,String text, String action, String src, String icon, String openIcon, String target)
    {
        StringBuffer strXML = new StringBuffer();
        strXML.append("<tree text=\"");strXML.append(text);strXML.append("\"");
        strXML.append(" id=\"");strXML.append(id);strXML.append("\"");
        strXML.append(" code=\"");strXML.append(code);strXML.append("\"");
        if(data!=null)
        {
        	strXML.append(" data=\"");strXML.append(StringUtil.xmlEncode(data.toString()));strXML.append("\"");
        }
        if(action!=null && action.length()>0)
        {
        	strXML.append(" action=\"");strXML.append(action);strXML.append("\"");
        }
        if (src != null)
        {
        	strXML.append(" src=\"");strXML.append(StringUtil.xmlEncode(src));strXML.append("\"");
        }
        if (icon != null) {
        	strXML.append(" icon=\"");strXML.append(icon);strXML.append("\"");
        }
        if (openIcon != null) {
        	strXML.append(" openIcon=\"");strXML.append(openIcon);strXML.append("\"");
        }
        if (target!=null){
        	strXML.append(" target=\"");strXML.append(target);strXML.append("\"");
        }
        strXML.append("/>");
        return strXML;
    }
    
    public static StringBuffer Wrap(StringBuffer strXml)
    {
    	strXml.insert(0,"<tree>");
    	strXml.append("</tree>");
    	return strXml;
    }
    
    public static String Wrap(String strXml)
    {
    	strXml="<tree>"+strXml+"</tree>";
    	return strXml;
    }
}
