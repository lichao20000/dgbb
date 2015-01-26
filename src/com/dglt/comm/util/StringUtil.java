/**
 * author:卢斌晖
 * date:2011-03-02
 */
package com.dglt.comm.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil 
{
    public StringUtil()
    {
    }

    public static String formater(String sourceString, Object args[])
    {
        return MessageFormat.format(sourceString, args);
    }

    public static String nullToString(String string)
    {
        return string != null ? string : "";
    }

    public static String boolean2String(boolean b)
    {
        return b ? "1" : "0";
    }

    public static boolean string2Boolean(String string)
    {
        if(null == string || string.length() == 0)
            return true;
        return string.equalsIgnoreCase("1");
    }

    public static String list2String(List sourceList, String separator)
    {
        if(null == sourceList || sourceList.size() < 1)
            return "";
        StringBuffer result = new StringBuffer();
        String element;
        for(Iterator iter = sourceList.iterator(); iter.hasNext(); result.append(element + separator))
            element = (String)iter.next();

        return result.substring(0, result.length() - separator.length());
    }

    public static String array2String(String sourceString[], String separator, boolean removeBlank)
    {
        if(sourceString == null || sourceString.length == 0)
            return "";
        StringBuffer buffer = new StringBuffer();
        if(removeBlank)
        {
            for(int i = 0; i < sourceString.length; i++)
            {
                if(sourceString[i]!=null || sourceString[i].trim().length()>0)
                {
                    buffer.append(sourceString[i]);
                    buffer.append(separator);
                }
            }
        } else
        {
            for(int i = 0; i < sourceString.length; i++)
            {
                buffer.append(sourceString[i]);
                buffer.append(separator);
            }

        }
        String result = buffer.toString();
        if(result.length() > separator.length())
            result = result.substring(0, result.length() - separator.length());
        return result;
    }

    public static String firstCharToLowCase(String sourceString)
    {
        if(null == sourceString || sourceString.length() == 0)
            return sourceString;
        if(sourceString.length() == 1)
            return sourceString.toLowerCase();
        else
            return sourceString.substring(0, 1).toLowerCase() + sourceString.substring(1, sourceString.length());
    }
    
    public static String firstCharUpperLowCase(String sourceString)
    {
        if(null == sourceString || sourceString.length() == 0)
            return sourceString;
        if(sourceString.length() == 1)
            return sourceString.toUpperCase();
        else
            return sourceString.substring(0, 1).toUpperCase() + sourceString.substring(1, sourceString.length());
    }

    public static String trim(String sourceString, String trimString)
    {
        if(null == sourceString || sourceString.length() == 0)
            return "";
        if(null == trimString || trimString.length() == 0)
            return sourceString;
        String temp[] = sourceString.split(trimString);
        if(null == temp || temp.length == 0)
            return sourceString;
        String result = "";
        for(int i = 0; i < temp.length; i++)
            result = result + temp[i];
        return result;
    }

    public static boolean isNumeric(String value)
    {
    	if(value == null || value.length()==0)
    		return false;
        try
        {
            Double.parseDouble(value);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    public static String String2Hex(int num)
    {
    	return String2Hex(String.valueOf(num));
    }
    
    public static String String2Hex(long num)
    {
    	return String2Hex(String.valueOf(num));
    }
    
    public static String String2Hex(float num)
    {
    	return String2Hex(String.valueOf(num));
    }
    
    public static String String2Hex(double num)
    {
    	return String2Hex(String.valueOf(num));
    }
    
    public static String String2Hex(String str)
    {
        byte bytes[] = str.getBytes();
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for(int i = 0; i < bytes.length; i++)
        {
            sb.append(hexString.charAt((bytes[i] & 240) >> 4));
            sb.append(hexString.charAt((bytes[i] & 15) >> 0));
        }

        return sb.toString();
    }

    public static String Hex2String(String bytes)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        for(int i = 0; i < bytes.length(); i += 2)
            baos.write(hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1)));

        return new String(baos.toByteArray());
    }

    public static long[] stringArray2LongArray(String sArray[])
    {
        if(null == sArray || sArray.length == 0)
            return null;
        long longArray[] = new long[sArray.length];
        for(int i = 0; i < sArray.length; i++)
        {
            try
            {
                longArray[i] = Long.parseLong(sArray[i]);
            }
            catch(NumberFormatException e)
            {
                longArray[i] = 0L;
            }
        }
        return longArray;
    }

    public static int[] stringArray2IntArray(String sArray[])
    {
        if(null == sArray || sArray.length == 0)
            return null;
        int intArray[] = new int[sArray.length];
        for(int i = 0; i < sArray.length; i++)
            try
            {
                intArray[i] = Integer.parseInt(sArray[i]);
            }
            catch(NumberFormatException e)
            {
                intArray[i] = 0;
            }

        return intArray;
    }

    public static boolean[] stringArray2BooleanArray(String sArray[])
    {
        if(null == sArray || sArray.length == 0)
            return null;
        boolean boolArray[] = new boolean[sArray.length];
        for(int i = 0; i < sArray.length; i++)
            boolArray[i] = sArray[i].equalsIgnoreCase("true");
        return boolArray;
    }

    private static String hexString = "0123456789ABCDEF";
    
	public static String lpad(String str,int repeatTime,String repeatItem)
	{
		if(str==null)
			str="";
		StringBuffer sb=new StringBuffer(str);
		for(int i=0;i<repeatTime;i++)
		{
			sb.insert(0,repeatItem);
		}
		return sb.toString();
	}
	
	public static String rpad(String str,int repeatTime,String repeatItem)
	{
		if(str==null)
			str="";
		StringBuffer sb=new StringBuffer(str);
		for(int i=0;i<repeatTime;i++)
		{
			sb.append(repeatItem);
		}
		return sb.toString();
	}
	
	public static String lpad(String str,int totalLength,char repeatItem)
	{
		if(str==null)
			str="";
		StringBuffer sb=new StringBuffer(str);
		for(int i=sb.length();i<totalLength;i++)
		{
			sb.insert(0,repeatItem);
		}
		return sb.toString();
	}
	
	public static String rpad(String str,int totalLength,char repeatItem)
	{
		if(str==null)
			str="";
		StringBuffer sb=new StringBuffer(str);
		for(int i=sb.length();i<totalLength;i++)
		{
			sb.append(repeatItem);
		}
		return sb.toString();
	}
	
	public static String xmlEncode(String xml)
	{
		xml=xml.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;").replaceAll("<", "&lt;").
		replaceAll(">", "&gt;").replaceAll("\r", "").replaceAll("\n", "");
		return xml;
	}

	public static String xmlDecode(String xml)
	{
		xml=xml.replaceAll("&amp;", "&").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&lt;", "<").
		replaceAll("&gt;", ">");
		return xml;
	}
	public static String getISOString(String l_S_Source)
	throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals("")) {
			byte l_b_Proc[] = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}

	public static String getGBKString(String l_S_Source)
	throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals("")) {
			byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}

	public static String showFormatDate(Date date, String s) {
		String s1 = "";
		if (date != null) {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
			s1 = simpledateformat.format(date);
		}
		return s1;
	}

	public static String replace(String s, String s1, String s2) {
		String s3 = "";
		int j = s1.length();
		if (s == null || s.equals(""))
			return s;
		int i;
		while ((i = s.indexOf(s1)) != -1) {
			s3 = String.valueOf(s3) + String.valueOf(s.substring(0, i));
			s3 = String.valueOf(s3) + String.valueOf(s2);
			s = s.substring(i + j);
		}

		s3 = String.valueOf(s3) + String.valueOf(s);
		return s3;
	}

	public static String htmlEncode(String s) {
		String s1 = s;
		s1 = replace(s1, "&", "&amp;");
		s1 = replace(s1, "\"", "&quot;");
		s1 = replace(s1, "'", "&#039;");
		s1 = replace(s1, "<", "&lt;");
		s1 = replace(s1, ">", "&gt;");
		return s1;
	}

	public static String htmlDecode(String s) {
		String s1 = s;
		s1 = replace(s1, "&amp;", "&");
		s1 = replace(s1, "&quot;", "\"");
		s1 = replace(s1, "&#039;", "'");
		s1 = replace(s1, "&lt;", "<");
		s1 = replace(s1, "&gt;", ">");
		return s1;
	}

	public static String SQLQuote(String s) {
		return replace(s, "'", "''");
	}

	public static String return2Br(String s) {
		return replace(s, "\n", "<br>");
	}

	public static String analyseCondition(String s) {
		if (s == null)
			return "";
		String s1 = s.trim();
		if (s1.equals(""))
			return "";
		if (s1.toUpperCase().indexOf("WHERE") != 0
				&& s1.toUpperCase().indexOf("ORDER") != 0)
			s1 = "where ".concat(String.valueOf(String.valueOf(s1)));
		return s1;
	}

	public static boolean isInt(String s) {
		try {
			(new Integer(s)).intValue();
		} catch (NumberFormatException numberformatexception) {
			boolean flag = false;
			return flag;
		}
		return true;
	}

	public static String blank2NBSP(String s) {
		return replace(s, " ", "&nbsp;");
	}

	public static String defaultString(String str) {
		return StringUtils.defaultString(str);
	}

	public static String defaultString(String str, String defaultStr) {
		if (str == null || str.equals("")) {
			return defaultStr;
		} else {
			return str;
		}
	}

	public static String[] split(String str, char separatorChar) {
		return StringUtils.split(str, separatorChar);
	}

	public static String[] split(String str, String separatorChars) {
		return StringUtils.split(str, separatorChars);
	}

	public static boolean isNumeric2(String str) {
		return StringUtils.isNumeric(str);
	}
	public static boolean isAlpha(String str) {
		return StringUtils.isAlpha(str);
	}
	public static String htmlCutShort(String str, int maxWidth) {
		return htmlEncode(cutShort(str, maxWidth));
	}
	public static String cutShort(String str, int maxWidth) {
		return StringUtils.abbreviate(str, 0, maxWidth);
	}
	public static String cutDot(String str) {
		int dotpo = str.indexOf(".");
		int len = (dotpo == -1)? str.length(): dotpo;
		return StringUtils.substring(str, 0, len);
	}
	/**
	 * update: 2006-11-22
	 * 
	 * eg:
	 * String sGB = "GB2312字符串";
	 * byte[] sChars = sGB.getBytes();
	 * StringUtil.isGB2312(sChars[0],sChars[1]);
	 */
	public static boolean isGB2312(byte head, byte tail) {
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return ((iHead >= 0xa1 && iHead <= 0xf7 && iTail >= 0xa1 && iTail <= 0xfe) ? true: false);
	}

	/**
	 * update: 2006-11-22
	 * 
	 * eg:
	 * String sGBK = "GBK字符串";
	 * byte[] sChars = sGBK.getBytes();
	 * StringUtil.isGB2312(sChars[0],sChars[1]);
	 */
	public static boolean isGBK(byte head, byte tail) {
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return ((iHead >= 0x81 && iHead <= 0xfe && (iTail >= 0x40
				&& iTail <= 0x7e || iTail >= 0x80 && iTail <= 0xfe)) ? true: false);
	}

	/**
	 * update: 2006-11-22
	 * 
	 * eg:
	 * String sBIG5 = "BIG5字符串";
	 * byte[] sChars = sBIG5.getBytes();
	 * StringUtil.isGB2312(sChars[0],sChars[1]);
	 */
	public static boolean isBIG5(byte head, byte tail) {
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return ((iHead >= 0xa1 && iHead <= 0xf9 && (iTail >= 0x40
				&& iTail <= 0x7e || iTail >= 0xa1 && iTail <= 0xfe)) ? true: false);
	}

	/**
	 * 截取定长中文字符串，一个汉字二个字节，若“中1国2”取4个字符 将输出“中1”
	 * @param str
	 * @param len
	 * @return
	 */

	public static String cutString(String str, int len) {
		if (str == null) {
			return "";
		}
		byte[] strByte = str.getBytes();
		int strLen = strByte.length;
		if (len >= strLen || len < 1) {
			return str;
		}
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			len = (len == 1) ? len + 1 : len - 1;
		}
		return new String(strByte, 0, len) ;
	}

	public static String xmlNode(String name,String val) {
		StringBuffer sbNode=new StringBuffer();
		sbNode.append("<").append(name).append("><![CDATA[");
		sbNode.append(val);
		sbNode.append("]]></").append(name).append(">");
		return sbNode.toString();

	}

	/**
	 * 
	 * @param tx
	 * @return
	 * @author zhouwq
	 * @since 2010-4-8 下午01:49:10
	 */
	public static String formatNum(String tx){
		if(!"".equals(StringUtil.defaultString(tx))){
			java.text.DecimalFormat   df=new   java.text.DecimalFormat("#0.00");
			double num = Double.parseDouble(tx);;
			tx = df.format(num);
		}
		return tx;
	}

	/**
	 * 判断是否这邮件格式
	 * @param email
	 * @return 　　　true :为有效邮箱地址
	 *  @author        tanw  ~tanw@vcmedu.com
	 *  @date          2010-5-24,上午10:30:35
	 */
	public static boolean isEmail(String email){
		if (email == null || "".equals(email)) return false;
		Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 得到梯形编码的父亲级别的编码
	 * 如果已经是最上级了，则返回null.
	 * 例如得到01_02_55的上级分类code，则返回01_02
	 * @param code			编码
	 * @param spliter		各级质检分隔的字符,例如  _   ,  .
	 * @return
	 * @author 卢斌晖
	 * @since 2010-7-1 下午02:57:39
	 */
	public static String getUpperCode(String code,String spliter)
	{
		code=StringUtil.defaultString(code,null);
		spliter=StringUtil.defaultString(spliter,null);
		if(code==null || spliter==null)
			return null;
		int lastIndex=code.lastIndexOf(spliter);
		if(-1==lastIndex)
			return null;
		code=code.substring(0,lastIndex);
		return code;
	}
}
