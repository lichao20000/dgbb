package com.dglt.comm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	  private DateUtils(){}

	    public static String getYYYY_MM_DD()
	    {
	    	return getYear()+"-"+getMonth()+"-"+getDay();
	    }
	    public static String getYYYYMMDD()
	    {
	    	return getYear()+getMonth()+getDay();
	    }
	    public static String getYYYYMMDD(String d19)
	    {
	        return d19.substring(0,4)+d19.substring(5,7)+d19.substring(8,10);
	    }
	    public static String getYYYYMMDD(String d16,String format)
	    {
	        return d16.substring(0,4)+format+d16.substring(4,6)+format+d16.substring(6,8);
	    }
	    public static String getCCYYMMDDhhmm()
	    {
	    	return getYear()+getMonth()+getDay()+getHour()+getMinute();
	    }
	    public static String getCCYYMMDDhhmmss()
	    {
	    	return getYear()+getMonth()+getDay()+getHour()+getMinute()+getSecond();
	    }
	    public static String getCCYYMMDDhhmmss(String d19)
	    {
	        return d19.substring(0,4)+d19.substring(5,7)+d19.substring(8,10)+d19.substring(11,13)+d19.substring(14,16)+d19.substring(17,19);
	    }

	    public static String getTimeStamp()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        return Long.toString(tm);
	    }

	    public static String getDateTime()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        return ((new java.sql.Date(tm)) + " " + (new java.sql.Time(tm)));
	    }

	    public static String getDate()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        return (new java.sql.Date(tm)).toString();
	    }

	    public static String getDate(String sDate)
	    {
	        return sDate.substring(0,10);
	    }

	    public static String getTime()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        return (new java.sql.Time(tm)).toString();
	    }

	    public static String getTime(String sDate)
	    {
	        return sDate.substring(11);
	    }

	    public static String getYear()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        String sz_date = (new java.sql.Date(tm)).toString();
	        return sz_date.substring(0, 4);
	    }

	    public static String getYear(String sDate)
	    {
	        return sDate.substring(0,4);
	    }

	    public static String getMonth()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        String sz_date = (new java.sql.Date(tm)).toString();
	        return sz_date.substring(5, 7);
	    }

	    public static String getMonth(String sDate)
	    {
	        return sDate.substring(5, 7);
	    }

	    public static String getDay()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        String sz_date = (new java.sql.Date(tm)).toString();
	        return sz_date.substring(8);
	    }

	    public static String getDay(String sDate)
	    {
	        return sDate.substring(8, 10);
	    }

	    public static String getHour()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        String sz_date = (new java.sql.Time(tm)).toString();
	        return sz_date.substring(0, 2);
	    }

	    public static String getHour(String sDate)
	    {
	        return sDate.substring(11, 13);
	    }

	    public static String getMinute()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        String sz_date = (new java.sql.Time(tm)).toString();
	        return sz_date.substring(3, 5);
	    }

	    public static String getMinute(String sDate)
	    {
	        return sDate.substring(14, 16);
	    }

	    public static String getSecond()
	    {
	        Date dt = new Date();
	        long tm = dt.getTime();
	        String sz_date = (new java.sql.Time(tm)).toString();
	        return sz_date.substring(6);
	    }

	    public static String getSecond(String sDate)
	    {
	        return sDate.substring(17);
	    }

	    public static String getPassDay(String d1, String d2)
	    {
	        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

	        try
	        {
	            Date a = parser.parse(d1);
	            Date b = parser.parse(d2);

	            long aa = a.getTime();
	            long bb = b.getTime();

	            long diff = (Math.abs((aa - bb) / 86400000));

	            return Long.toString(diff);
	        }
	        catch(Exception e)
	        {
	            return "-1";
	        }
	    }
	    public static String addDays(String d, int n)
	    {
	        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

	        try
	        {
	            Date a = parser.parse(d);

	            long aa = a.getTime();
	            aa += (long)n * 86400000;

	            java.sql.Date b = new java.sql.Date(aa);

	            return (b.toString());
	        }
	        catch(Exception e)
	        {
	            return d;
	        }
	    }
	    public static String getLastDay(String year,String month)
	    {
	        try
	        {
				String ret = "0";
				int y = Integer.parseInt(year);
				int m = Integer.parseInt(month);
				switch (m){
					case 1:
						ret = "31";
						break;
					case 3:
						ret = "31";
						break;
					case 5:
						ret = "31";
						break;
					case 7:
						ret = "31";
						break;
					case 8:
						ret = "31";
						break;
					case 10:
						ret = "31";
						break;
					case 12:
						ret = "31";
						break;
					case 4:
						ret = "30";
						break;
					case 6:
						ret = "30";
						break;
					case 9:
						ret = "30";
						break;
					case 11:
						ret = "30";
						break;
					case 2:
						if ((y%4==0&&y%100!=0)||y%400==0)
							ret = "29";
						else
							ret = "28";
						break;
				}
				return ret;
	        }
	        catch(Exception e)
	        {
	            return e.toString();
	        }
	    }
	    
	    public static String getStringToDate(String d19)
	    {
	    	if (d19.length()==14)
	    		return d19.substring(0,4)+"-"+d19.substring(4,6)+"-"+d19.substring(6,8)+
	    		" "+d19.substring(8,10)+":"+d19.substring(10,12)+":"+d19.substring(12,14);
	    	else
	    		return d19;
	    }
	    
	    /**   
	     * 得到时间差,以毫秒值返回
	     * 格式:begintime:"20070412134511"
	     */
	    public static long getDiff(String begintime,String endtime) throws Exception{
	    	java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	java.util.Date b_time=sdf.parse(getStringToDate(begintime));
	    	java.util.Date e_time=sdf.parse(getStringToDate(endtime));
	    	return e_time.getTime()-b_time.getTime();   
	    }
	    
	    public static void main(String[] args) throws Exception {
	    	System.out.println(DateTimeUtil.formatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    }
}
