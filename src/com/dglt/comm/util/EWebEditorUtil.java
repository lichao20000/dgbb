package com.dglt.comm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
public class EWebEditorUtil
{
	/**
	 * 保存EWebEditor输入的内容
	 * @param absPath		html文件的绝对路径
	 * @param content		内容
	 * @return
	 * @author 卢斌晖
	 * @since Nov 15, 2010 9:57:57 AM
	 */
	public static File saveHtmlContent(String absPath,String content)
	{
		if(content==null || content.length()==0 || absPath == null || absPath.length()==0)
			return null;
		File file=new File(absPath);
		try
		{
			OutputStream bos = new FileOutputStream(absPath);
			bos.write(content.getBytes("GB2312"));
			try
			{
				bos.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return file;
	}

	/**
	 * 删除文件
	 * @param absPath
	 * @return
	 * @author 卢斌晖
	 * @since Nov 15, 2010 9:58:21 AM
	 */
	public static int deleteHtml(String absPath)
	{
		if(null == absPath || 0 == absPath.length())
			return -1;
		File file=new File(absPath);
		if(file.exists())
			file.delete();
		return 0;
	}

	/**
	 * 获得html文件的内容
	 * @param absPath
	 * @return
	 * @author 卢斌晖
	 * @since Nov 15, 2010 9:59:54 AM
	 */
	public static String getFileContent(String absPath)
    {
		if(null==StringUtil.defaultString(absPath,null))
			return null;
		File file=new File(absPath);
		String content="";
		if(file.exists() && file.isFile())
		{
			try
			{
				BufferedReader input=new BufferedReader(new FileReader(file)); 
				String text;
				StringBuilder temp = new StringBuilder();
				while((text=input.readLine())!=null)
					temp.append(text);
				content=temp.toString();
				input.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
    	return content;
    }
}
