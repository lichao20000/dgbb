package com.dglt.comm.util;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;


public class SpringTools
{

	/**
	 * 
	 * @param inpath  被拷贝文件路径
	 * @param outpath 拷贝到目标路径
	 * @return
	 * @author tanw
	 * @since 2010-12-8 下午04:26:15
	 */
	public static boolean fileCopy(String inpath ,String outpath)
	{
		try
		{
	    	Resource rin = new FileSystemResource(inpath); 
	        Resource rout = new FileSystemResource(outpath);
			return fileCopy(rin.getFile(), rout.getFile());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 
	 * @param in  被拷贝的文件
	 * @param out 拷贝到目标文件
	 * @return
	 * @author tanw
	 * @since 2010-12-8 下午04:20:30
	 */
	public static boolean fileCopy(File in ,File out)
	{
		try
		{
			if( FileCopyUtils.copy(in, out) > 0 ) return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param text 字符串 
	 * @return 对象不为null 且有一个非空字符 返回true。 否则 false
	 * @author tanw
	 * @since 2010-12-8 下午05:11:14
	 */
	public static boolean hasText(String text)
	{
		try
		{
			Assert.hasText(text);
		}
		catch ( Exception e)
		{
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args)
	{
		System.out.println(SpringTools.hasText(null));
	}
	
}