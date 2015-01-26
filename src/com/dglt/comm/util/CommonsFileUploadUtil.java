/**
 * author:Â¬±óêÍ
 * date:2011-03-02
 */
package com.dglt.comm.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CommonsFileUploadUtil
{
	public static final int KB=1024;
	public static final int MB=1024*1024;
	public static final int DEFAULT_CACHE=2*MB;
	public static final String DEFAULT_HEADER_ENCODING="UTF-8";
	
	private CommonsFileUploadUtil()
	{
		
	}
	
	public List uploadFile(HttpServletRequest request,String absPath)
	{
		return uploadFile(request,absPath,null);
	}
	
	public List uploadFile(HttpServletRequest request,String absPath,List fileNames)
	{
		absPath=absPath.endsWith("/")?absPath:absPath+"/";
		File file = new File(absPath);
		if (!file.exists())
			file.mkdirs();
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		String headerEncoding=StringUtil.defaultString(request.getParameter("headerEncoding"),DEFAULT_HEADER_ENCODING);
		upload.setHeaderEncoding(headerEncoding);
		List fileList = null;
		try
		{
			fileList = upload.parseRequest(request);
		}
		catch(FileUploadException e)
		{
			e.printStackTrace();
			return null;
		}
		List files=new ArrayList();
		Iterator it = fileList.iterator();
		String name=null;
		long totalSize=0;
		while (it.hasNext())
		{
			FileItem item = (FileItem)it.next();
		    if (!item.isFormField())
		    {   
		        name = StringUtil.defaultString(item.getName(),null);
		        totalSize += item.getSize();
		    }
		}
		if(totalSize>2*MB && totalSize<=10*MB)
			fac.setSizeThreshold(512*KB);
		else
		{
			if(totalSize>10*MB && totalSize<=50*MB)
				fac.setSizeThreshold(1*MB);
			else
			{
				if(totalSize>50*MB)
					fac.setSizeThreshold(2*MB);
			}
		}
		it=fileList.iterator();
		while (it.hasNext())
		{
		    FileItem item = (FileItem)it.next();
		    if (!item.isFormField())
		    {
		    	name = StringUtil.defaultString(item.getName(),null);
		    	if (name == null)
		    		continue;
//		        String type = item.getContentType();
		    	
		    	if(fileNames!=null && files.size()<fileNames.size())
		    		file = new File(absPath + fileNames.get(files.size()));
		    	else
		    		file = new File(absPath + name);
		        if(file.exists())
		        	file.delete();
		        try
		        {
		            item.write(file);
		        }
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        	continue;
		        }
		        files.add(file);
		    }
		}
		System.gc();
		return files;
	}
	
	public List getUploadFileName(HttpServletRequest request,String headerEncoding,Map otherParams)
	{
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		headerEncoding=StringUtil.defaultString(headerEncoding,DEFAULT_HEADER_ENCODING);
		upload.setHeaderEncoding(headerEncoding);
		List fileList = null;
		try
		{
			fileList = upload.parseRequest(request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		List names=new ArrayList();
		String name;
		for(int i=0;i<fileList.size();i++)
		{
			FileItem item = (FileItem)fileList.get(i);
		    if (!item.isFormField())
		    {
		    	name = StringUtil.defaultString(item.getName(),null);
		    	if(null != name)
		    		names.add(name);
		    }
		}
		return names;
	}
	
	public static CommonsFileUploadUtil getInstance()
	{
		return new CommonsFileUploadUtil();
	}
}
