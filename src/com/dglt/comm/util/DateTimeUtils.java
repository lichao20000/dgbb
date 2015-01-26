package com.dglt.comm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
	  public static final int JANUARY = 0;
	    public static final int FEBRUARY = 1;
	    public static final int MARCH = 2;
	    public static final int APRIL = 3;
	    public static final int MAY = 4;
	    public static final int JUNE = 5;
	    public static final int JULY = 6;
	    public static final int AUGUST = 7;
	    public static final int SEPTEMBER = 8;
	    public static final int OCTOBER = 9;
	    public static final int NOVEMBER = 10;
	    public static final int DECEMBER = 11;
	    private static final String MONTH_CN[] = {
	        "\u4E00\u6708", "\u4E8C\u6708", "\u4E09\u6708", "\u56DB\u6708", "\u4E94\u6708", "\u516D\u6708", "\u4E03\u6708", "\u516B\u6708", "\u4E5D\u6708", "\u5341\u6708", 
	        "\u5341\u4E00\u6708", "\u5341\u4E8C\u6708"
	    };
	    public static final int SUNDAY = 0;
	    public static final int MONDAY = 1;
	    public static final int TUESDAY = 2;
	    public static final int WEDNESDAY = 3;
	    public static final int THURSDAY = 4;
	    public static final int FRIDAY = 5;
	    public static final int SATURDAY = 6;
	    private static final String WEEK_CN[] = {
	        "\u661F\u671F\u5929", "\u661F\u671F\u4E00", "\u661F\u671F\u4E8C", "\u661F\u671F\u4E09", "\u661F\u671F\u56DB", "\u661F\u671F\u4E94", "\u661F\u671F\u516D"
	    };
	    public static final String TIMEUNIT_MINUTE = "M";
	    public static final String TIMEUNIT_HOUR = "H";
	    public static final String TIMEUNIT_DAY = "D";
	    private static final String TIMEUNIT_CN[] = {
	        "\u5206\u949F", "\u5C0F\u65F6", "\u5929"
	    };
	    public static final String PERIODUNIT_DAY = "day";
	    public static final String PERIODUNIT_WEEK = "week";
	    public static final String PERIODUNIT_MONTH = "month";
	    public static final String PERIODUNIT_YEAR = "year";
	    private static final String PERIODUNIT_CN[] = {
	        "\u5929", "\u5468", "\u6708", "\u5E74"
	    };

	    public DateTimeUtils()
	    {
	    }

	    public static String getPeriodUnitCN(String periodUnit)
	    {
	        String temp[] = {
	            "day", "week", "month", "year"
	        };
	        for(int i = 0; i < temp.length; i++)
	            if(periodUnit.equalsIgnoreCase(temp[i]))
	                return PERIODUNIT_CN[i];

	        return periodUnit;
	    }

	    public static String getCurDate()
	    {
	        return formatDate(new Date());
	    }

	    public static String getCurDateTime()
	    {
	        return formatDateTime(new Date());
	    }

	    public static String formatDate(Date date)
	    {
	        if(null == date)
	        {
	            return "";
	        } else
	        {
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            return df.format(date);
	        }
	    }

	    public static String formatDateTime(Date date)
	    {
	        return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
	    }

	    public static String formatDateTime2Minute(Date date)
	    {
	        return formatDateTime(date, "yyyy-MM-dd HH:mm");
	    }

	    public static String formatDateTime(Date date, String format)
	    {
	        if(null == date)
	        {
	            return "";
	        } else
	        {
	            DateFormat df = new SimpleDateFormat(format);
	            return df.format(date);
	        }
	    }

	    public static int getHour(Date date)
	    {
	        if(null == date)
	            return 0;
	        else
	            return date.getHours();
	    }
	    public static int getCurHour()
	    {
	        return getHour(new Date());
	    }

	    public static int getMinute(Date date)
	    {
	        if(null == date)
	            return 0;
	        else
	            return date.getMinutes();
	    }

	    public static Date getDate(String dateTime)
	    {
	        if(null == dateTime || dateTime.length() == 0)
	            return null;
	        DateFormat df = null;
	        if(dateTime.length() == "yyyy-MM-dd HH:mm:ss".length())
	            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        else
	        if(dateTime.length() == "yyyy-MM-dd HH:mm".length())
	            df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        else
	        if(dateTime.length() == "yyyy-MM-dd".length())
	            df = new SimpleDateFormat("yyyy-MM-dd");
	        else
	            return null;
	        try
	        {
	            return df.parse(dateTime);
	        }
	        catch(ParseException pe)
	        {
	            return null;
	        }
	    }

	    public static String buildDateTime(String date, String hour, String minute)
	    {
	        if(null == date || date.length() == 0)
	            return "";
	        if(null == hour)
	            return date;
	        String hm = hour.length() != 1 ? hour : "0" + hour;
	        if(null != minute)
	        {
	            hm = hm + ":";
	            hm = hm + (minute.length() != 1 ? minute : "0" + minute);
	        }
	        return date + " " + hm;
	    }

	    public static Date getStartDate(Date date)
	    {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(11, 0);
	        calendar.set(12, 0);
	        calendar.set(13, 0);
	        return calendar.getTime();
	    }

	    public static Date getEndDate(Date date)
	    {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(11, 23);
	        calendar.set(12, 59);
	        calendar.set(13, 59);
	        return calendar.getTime();
	    }
	    //test
	    public static void main(String[] ars )
	    {
			Date beginDate=new Date();
			Date endDate=new Date();
			
			endDate.setDate(endDate.getDate()-5);
			
			
			endDate.setHours(0);
			endDate.setMinutes(0);
			endDate.setSeconds(0);
			
			System.out.println(getDaysFromDates(beginDate,endDate));
	    }
	    
	    /**
	     * 得到两个Date之间相隔的天数
	     * @param beginDate
	     * @param endDate
	     * @return
	     * @author 卢斌晖
	     * @since 2010-3-3 下午03:16:46
	     */
	    public static final long millisecondPerDay=1000*60*60*24;
		public static long getDaysFromDates(Date beginDate,Date endDate)
		{
			try
			{
		    	long distance=endDate.getTime() - beginDate.getTime();
		    	long days=distance/millisecondPerDay;
		   		return days>=0?days+1:days;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return Long.MIN_VALUE;
			}
	    }
		
		public static String getDayCn(int day)
		{
			if(day<0 || day>6)
				return null;
			return WEEK_CN[day];
		}
		
		/**
		 * 传入日期时间字符串，将其转换为中文的表示
		 * 例如传入2010-08-22 17:43:09转换后得到	08月22日 17:43
		 * @param dateTime	YYYY-MM-DD HH:mm:SS
		 * @param padding	用于分隔转换后的日期和时间
		 * @return
		 * @author 卢斌晖
		 * @since 2010-8-25 上午10:07:44
		 */
		public static String formatDateTimeChi(String dateTime,String padding)
		{
			if(dateTime == null || dateTime.length()<10)
				return "";
			String newDateTime=dateTime.substring(5,7)+"月"+dateTime.substring(8,10)+"日";
			if(dateTime.length()==19)
			{
				padding=StringUtil.defaultString(padding," ");
				newDateTime+=padding+dateTime.substring(11,16);
			}
			return newDateTime;
		}	
}
