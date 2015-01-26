package com.dglt.comm.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;

/**
 * 读取XML文件的方�?
 * 
 *  
 * 
 *
 */
public class XMLProperties {


	
	
	public static String basePath = "";
	public static String xmlpath = "";
	
	static {
		basePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		System.out.println(basePath);
	}
	
	/**
	 * 解析字符串的方法,以分号分�?
	 * @param str
	 * @return
	 */
   public static String[] parshString(String str){
		if(str!=null&&!str.equals("")){
			String[] result = str.split(";");
			return result;
		}else
			return null;
	}
   
   /**
    * 
    * @param ParentElement
    * @param xPath
    * @param flag
    * @return
    */
   public static String getProperty(Element ParentElement,String[] xPath,int flag){
		String result = "";
		int index = xPath.length-flag;
		String s = xPath[index];
		List parentList=ParentElement.getChildren(xPath[index]);
		for (Iterator iter = parentList.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			//List list=element.getChildren(xPath[index]);
			if(flag!=1){
				flag--;
				result = getProperty(element,xPath,flag);
			}else{
				result=element.getText();
			}
		}
		return result;
	}
	
   /**
    * 
    * @param file xml 文件�?
    * @param path �?��取得的路�?
    * @return
    */
   public static String getProperty(String file,String path){
	   xmlpath =basePath+file;
	   String[] xPath = parshString(path);
	   String result = "";
	   SAXBuilder builder=new SAXBuilder(false);
			try {
				Document doc= builder.build(xmlpath);
				Element element=doc.getRootElement();
				result = getProperty(element,xPath,xPath.length);
			} catch (JDOMParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JDOMException e) {
				e.printStackTrace();
			}
		return result;
   }
   
   /**
    * 
    * @param file xml 文件�?
    * @param path �?��取得的路�?
    * @return
    */
   public static String getAttribute(String file,String path,String id,String attributeName){
	   xmlpath =basePath+ file;
	   String[] xPath = parshString(path);
		String result = "";
		SAXBuilder builder=new SAXBuilder(false);
			try {
				Document doc= builder.build(xmlpath);
				Element element=doc.getRootElement();
				Element element1 = element.getChild(id);
				result=element1.getAttributeValue(attributeName);
			} catch (JDOMParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JDOMException e) {
				e.printStackTrace();
			}
		return result;
   }   
   
  
	
	 /**
	    * 读取�?��节点下面�?��子节点中的属性�?
	    * @param fileXML文件�?
	    * @param path�?��以分号分割的字符�?
	    * @param name�?��以分号分割的字符�?
	    * @return
	    */
	   public static List getProperty(String fileName,String path,String name){
		   String[] xPath = parshString(path);
		   String[] keys = parshString(name);
	
		   xmlpath = basePath+fileName;
			List list = new ArrayList(); 
			SAXBuilder builder=new SAXBuilder(false);
				try {
					Document doc= builder.build(xmlpath);
					Element element=doc.getRootElement();
					list = getProperty(element,xPath,xPath.length,keys,keys.length);
				} catch (JDOMParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//System.out.println(list);
			
			return list;
	   }
	   /**
	    * 取指定节点下面所有节点的属�?的方�?
	    * @param ParentElement 根节�?
	    * @param xPath �?��取的路径的长�?
	    * @param flag 路径字符串的长度
	    * @param keys �?��取的属�?值的字符�?
	    * @param keyLength 属�?值的长度
	    * @return
	    */
	   public static List getProperty(Element ParentElement,String[] xPath,int flag,String[] keys,int keyLength){
			String result = "";
			List list = new ArrayList();
			int index = xPath.length-flag;
			String s = xPath[index];
			List parentList=ParentElement.getChildren(xPath[index]);
			for (Iterator iter = parentList.iterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				//List list=element.getChildren(xPath[index]);
				if(flag!=1){
					flag--;
					list = getProperty(element,xPath,flag,keys,keyLength);
				}else{
					Map map = new HashMap();
					for(int i=0;i<keyLength;i++){
						result = "";
						result=element.getAttributeValue(keys[i]);
						map.put(keys[i], result);
					}
					list.add(map);
				}
				
			}
			return list;
		}
	   
	   
}
