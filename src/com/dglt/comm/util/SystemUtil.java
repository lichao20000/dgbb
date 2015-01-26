/**
 * author:Â¬±óêÍ
 * date:2011-03-02
 */
package com.dglt.comm.util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SystemUtil 
{
	
	public static  void printHtml(String text,HttpServletResponse response)throws Exception
	{
        response.setContentType("text/html;charset=GBK");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(text);
        out.close();
	}
	
	public static  void printJSONString(String jsonString,HttpServletResponse response)throws Exception
	{
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.close();
	}
	
	public static String convertEncoding(String str,String originEncoding,String encoding) 
	{
		if(str==null || str.length()==0)
			return str;
		if(originEncoding == null)
			originEncoding="GBK";
		if(encoding == null)
			encoding="UTF-8";
		try
		{
			return new String(str.getBytes(originEncoding),encoding);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public static Writer responseHtml(String string,HttpServletResponse response)throws Exception
	{
        return responseHtml(string,response,"UTF-8");
	}
	
	public static Writer responseHtml(String string,HttpServletResponse response,String coding)throws Exception
	{
		responseNoCache(response);
		if(coding==null || coding.length()==0)
			coding="UTF-8";
        response.setCharacterEncoding(coding);
        PrintWriter out = response.getWriter();
        out.write(string);
        out.flush();
        return out;
	}
	
	public static Writer responseXml(String string,HttpServletResponse response)throws Exception
	{
        return responseXml(string,response,"UTF-8");
	}
	
	public static Writer responseXml(String string,HttpServletResponse response,String coding)throws Exception
	{
		responseNoCache(response);
		if(coding==null || coding.length()==0)
			coding="UTF-8";
		response.setContentType("text/xml");
        response.setCharacterEncoding(coding);
        PrintWriter out = response.getWriter();
        out.write("<?xml version=\"1.0\" encoding=\""+coding+"\"?>");
        out.write(string);
        out.flush();
        return out;
	}
	
	public static  void responseJSONString(String jsonString,HttpServletResponse response)throws Exception
	{
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
//        
//        System.out.println();
//        System.out.println(jsonString);
//        System.out.println();
//        
        out.print(jsonString);
        
        out.close();
	}
	
	public static void responseExcel(HttpServletResponse response,HSSFWorkbook workbook,String fileName)
	{
		try
		{
			response.setContentType("application/vnd.ms-excel MSEXCEL");
			response.addHeader("Content-Disposition","attachment; filename=\""+fileName+"\";");
			OutputStream fOut = response.getOutputStream();
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void responseTxt(HttpServletResponse response,String txt,String fileName)
	{
		try
		{
			response.setContentType("text/html;charset=GBK");
			response.setContentType("text/plain");
			response.addHeader("Content-Disposition","attachment; filename=\""+fileName+"\";");
			PrintWriter pw=response.getWriter();
			pw.println(txt);
			pw.flush();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String getWebAppAbsolutPath(HttpServletRequest request)
	{
		return request.getRealPath("/");
	}

	public static String getWebAppAbsolutPath(ServletContext context)
	{
		return context.getRealPath("/");
	}
	
	public static String getContextPath(ServletContext context)
	{
		return (String)context.getAttribute("contextPath");
	}
	
	/**
	 * É¾³ýÒ³Ãæ»º´æ
	 */
	public static void responseNoCache(HttpServletResponse response)
	{
        response.setHeader("Cache-Control", "no-cache");
    	response.setHeader("Pragma", "no-cache"); 
    	response.setDateHeader("Expires", 0);
	}
}
