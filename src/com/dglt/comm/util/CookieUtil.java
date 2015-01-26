package com.dglt.comm.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dglt.base.util.Base64;

public class CookieUtil
{
	private static String m_domain = "MyUtils";
	private static String m_domain2 = "MyUtils";
	private static String path = "/";

	public static void SetCookies(String CookieName, String CookieValues,HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			Cookie cookie = new Cookie(CookieName, Base64.encode(CookieValues));
			cookie.setDomain(m_domain);
			cookie.setPath(path);
			response.addCookie(cookie);
			
			Cookie cookie1 = new Cookie(CookieName, Base64.encode(CookieValues));
			cookie1.setPath(path);
			response.addCookie(cookie1);
			
			Cookie cookie2 = new Cookie(CookieName, Base64.encode(CookieValues));
			cookie2.setDomain(m_domain2);
			cookie2.setPath(path);
			response.addCookie(cookie2);
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}

	public static String GetCookies(String CookieName,HttpServletRequest request)
	{
		String cookie = "";
		try 
		{
			Cookie cookies[] = request.getCookies();
			if (cookies != null)
			{
				for (Cookie cooky : cookies)
				{
					if (cooky.getName().equals(CookieName))
					{
						cookie = Base64.decode(cooky.getValue());
					}
				}
			}
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		return cookie;
	}

	public static void removeCookie(HttpServletRequest request,HttpServletResponse response, String cookieName)
	{
		try
		{
			Cookie cookie=new Cookie(cookieName, "");
			cookie.setDomain(m_domain);
			cookie.setPath(path);
			response.addCookie(cookie);
			
			Cookie cookie1=new Cookie(cookieName, "");
			cookie1.setPath(path);
			response.addCookie(cookie1);
			
			Cookie cookie2=new Cookie(cookieName, "");
			cookie2.setDomain(m_domain2);
			cookie2.setPath(path);
			response.addCookie(cookie2);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void removeAll(HttpServletRequest request, HttpServletResponse response)
	{
		Cookie cookies[]=request.getCookies();
		try
		{
			for(int i=0;i<cookies.length;i++)
			{
				Cookie sCookie=cookies[i];
				Cookie cookie=new Cookie(sCookie.getName(), "");
				cookie.setDomain(m_domain);
				cookie.setPath(path);
				response.addCookie(cookie);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void SetCookiesAndAge(String CookieName,String CookieValues,Integer MaxAge,HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie cookie = new Cookie(CookieName, Base64.encode(CookieValues));
			cookie.setMaxAge(MaxAge);
			cookie.setDomain(m_domain);
			cookie.setPath(path);//换成别的域名就不能用了。等处理。
			response.addCookie(cookie);
			
			Cookie cookie1 = new Cookie(CookieName, Base64.encode(CookieValues));
			cookie1.setMaxAge(MaxAge);
			cookie1.setPath(path);
			response.addCookie(cookie1);
			
		} catch (Exception err) {
			System.out.println("写入cookie失败！" + err);
		}
	}
	
}
