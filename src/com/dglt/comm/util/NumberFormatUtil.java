package com.dglt.comm.util;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class NumberFormatUtil {


	/**
	 * <p>
	 * 格式化双精度数字为“#,##0.00”标准货币格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字
	 * @return 返回格式化过的字符串
	 */
	public static String format(double d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(d);
	}

	/**
	 * <p>
	 * 格式化单精度数字为“#,##0.00”标准货币格式
	 * </p>
	 * 
	 * @param f
	 *            需要格式化的双精度数字
	 * @return 返回格式化过的字符串
	 */
	public static String format(float f) {
		return NumberFormatUtil.format((double) f);
	}

	/**
	 * <p>
	 * 格式化长整形数字为“#,##0”格式
	 * </p>
	 * 
	 * @param l
	 *            需要格式化的长整形数字
	 * @return 返回格式化过的字符串
	 */
	public static String format(long l) {
		DecimalFormat df = new DecimalFormat("#,##0");
		return df.format(l);
	}

	/**
	 * <p>
	 * 格式化整形数字为“#,##0”格式
	 * </p>
	 * 
	 * @param i
	 *            需要格式化的整形数字
	 * @return 返回格式化过的字符串
	 */
	public static String format(int i) {
		return format((long) i);
	}

	/**
	 * <p>
	 * 格式化整形数字为标准格式
	 * </p>
	 * 
	 * @param i
	 *            需要格式化的整形数字
	 * @param style
	 *            具体整形数字格式，如果style为null或为空字符串，则默认为“0000000000”
	 * @return 返回格式化过的字符串
	 */
	public static String format(int i, String style) {
		String styleSelf;
		if (style != null && !style.equals(""))
			styleSelf = style;
		else
			styleSelf = "0000000000";
		DecimalFormat df = new DecimalFormat(styleSelf);
		return df.format(i);
	}

	/**
	 * <p>
	 * 格式化双精度数字类
	 * </p>
	 * 
	 * @param d
	 *            双精度数字类
	 * @return 返回格式化过的字符串
	 */
	public static String format(Double d) {
		if (d == null) {
			return null;
		} else {
			double dd = d.doubleValue();
			return NumberFormatUtil.format(dd);
		}
	}

	/**
	 * <p>
	 * 格式化单精度数字类
	 * </p>
	 * 
	 * @param f
	 *            单精度数字类
	 * @return 返回格式化过的字符串
	 */
	public static String format(Float f) {
		if (f == null) {
			return null;
		} else {
			float ff = f.floatValue();
			return NumberFormatUtil.format(ff);
		}
	}

	/**
	 * <p>
	 * 格式化长整形数字类
	 * </p>
	 * 
	 * @param l
	 *            长整形数字类
	 * @return 返回格式化过的字符串
	 */
	public static String format(Long l) {
		if (l == null) {
			return null;
		} else {
			long ll = l.longValue();
			return NumberFormatUtil.format(ll);
		}
	}

	/**
	 * <p>
	 * 格式化整形数字类
	 * </p>
	 * 
	 * @param i
	 *            整形数字类
	 * @return 返回格式化过的字符串
	 */
	public static String format(Integer i) {
		if (i == null) {
			return null;
		} else {
			int ii = i.intValue();
			return NumberFormatUtil.format(ii);
		}
	}

	/**
	 * <p>
	 * 格式化双精度数字，保留一定的小数位数
	 * </p>
	 * 
	 * @param d
	 *            双精度数字
	 * @param decimalDigits
	 *            如果为正数，则为小数位数；如果为负数，则舍弃小数部分并向右舍弃相应位数值为0
	 * @return 返回格式化过的字符串
	 */
	public static String format(double d, int decimalDigits) {
		String strNumber, symbol;
		if (decimalDigits >= 0) {
			symbol = "###0.";
			for (int i = 0; i < decimalDigits; i++) {
				symbol = symbol + "0";
			}
			DecimalFormat df = new DecimalFormat(symbol);
			strNumber = df.format(d);
		} else {
			String strNum = NumberFormatUtil.format(d);
			int inDot = strNum.indexOf('.');
			if (inDot + decimalDigits > 1) {
				strNumber = strNum.substring(0, inDot + decimalDigits - 1);
				for (int i = decimalDigits; i < 0; i++) {
					strNumber = strNumber + "0";
				}
			} else {
				strNumber = null;
			}
		}
		return strNumber;
	}

	/**
	 * <p>
	 * 格式化双精度数字，保留一定的小数位数
	 * </p>
	 * 
	 * @param d
	 *            双精度数字
	 * @param decimalDigits
	 *            如果为正数，则为小数位数；如果为负数，则舍弃小数部分并向右舍弃相应位数值为0
	 * @return 格式化之后的字符串
	 */
	public static String formatNoComma(double d, int decimalDigits) {
		String strNumber, symbol;
		if (decimalDigits >= 0) {
			symbol = "###0.";
			for (int i = 0; i < decimalDigits; i++) {
				symbol = symbol + "0";
			}
			DecimalFormat df = new DecimalFormat(symbol);
			strNumber = df.format(d);
		} else {
			String strNum = NumberFormatUtil.format(d);
			int inDot = strNum.indexOf('.');
			if (inDot + decimalDigits > 1) {
				strNumber = strNum.substring(0, inDot + decimalDigits - 1);
				for (int i = decimalDigits; i < 0; i++) {
					strNumber = strNumber + "0";
				}
			} else {
				strNumber = null;
			}
		}
		return strNumber;
	}

	/**
	 * <p>
	 * 格式化单精度数字，保留一定的小数位数
	 * </p>
	 * 
	 * @param f
	 *            单精度数字
	 * @param decimalDigits
	 *            如果为正数，则为小数位数；如果为负数，则舍弃小数部分并向右舍弃相应位数值为0
	 * @return 返回格式化过的字符串
	 */
	public static String format(float f, int decimalDigits) {
		return NumberFormatUtil.format((double) f, decimalDigits);
	}

	/**
	 * <p>
	 * 格式化双精度数字类，保留一定的小数位数
	 * </p>
	 * <p>
	 * 比如<br>
	 * NumberFormatUtil.format(1234567890.123456789012345678901d, -5)=1234500000
	 * </p>
	 * 
	 * @param d
	 *            双精度数字类
	 * @param decimalDigits
	 *            如果为正数，则为小数位数；如果为负数，则舍弃小数部分并向右舍弃相应位数值为0
	 * @return 返回格式化过的字符串
	 */

	public static String format(Double d, int decimalDigits) {
		if (d == null) {
			return null;
		} else {
			double dd = d.doubleValue();
			return NumberFormatUtil.format(dd, decimalDigits);
		}
	}

	/**
	 * <p>
	 * 格式化双精度数字类，保留一定的小数位数
	 * </p>
	 * 
	 * @param d
	 *            双精度数字类
	 * @param decimalDigits
	 *            如果为正数，则为小数位数；如果为负数，则舍弃小数部分并向右舍弃相应位数值为0
	 * @return 返回格式化过的字符串
	 */
	public static String formatNoComma(Double d, int decimalDigits) {
		if (d == null) {
			return null;
		} else {
			double dd = d.doubleValue();
			return NumberFormatUtil.formatNoComma(dd, decimalDigits);
		}
	}

	/**
	 * <p>
	 * 格式化单精度数字类，保留一定的小数位数
	 * </p>
	 * 
	 * @param f
	 *            单精度数字类
	 * @param decimalDigits
	 *            如果为正数，则为小数位数；如果为负数，则舍弃小数部分并向右舍弃相应位数值为0
	 * @return 返回格式化过的字符串
	 */
	public static String format(Float f, int decimalDigits) {
		if (f == null) {
			return null;
		} else {
			float ff = f.floatValue();
			return NumberFormatUtil.format(ff, decimalDigits);
		}
	}
	
	/**
	 * 传入数字的字符串，保留小数点后几位
	 * @param f
	 * @param decimalDigits
	 * @return
	 * @author 卢斌晖
	 * @since 2010-6-12 下午02:15:05
	 */
	public static String format(String f, int decimalDigits)
	{
		if (f == null || "".equals(f))
		{
			return "";
		} 
		else
		{
			float ff;
			try
			{
				ff=Float.parseFloat(f);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			return NumberFormatUtil.format(ff, decimalDigits);
		}
	}

	/**
	 * <p>
	 * 除去字符串中的所有逗号分割符（,）
	 * </p>
	 * 
	 * @param s
	 *            需要处理的字符串
	 * @return 不含有逗号的字符串
	 */
	public static String toToken(String s) {
		String newStr = new String("");
		StringTokenizer st = new StringTokenizer(s, ",");
		while (st.hasMoreTokens()) {
			newStr = newStr + st.nextToken();
		}
		return newStr;
	}

	/**
	 * <p>
	 * 把字符串转换成为双精度数字
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 双精度数字
	 */
	public static double toDouble(String s) {
		return toCDouble(s).doubleValue();
	}

	/**
	 * <p>
	 * 把字符串转换成为双精度数字类
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 双精度数字类
	 */
	public static Double toCDouble(String s) {
		if (s == null) {
			return null;
		} else {
			String str = toToken(s);
			return Double.valueOf(str);
		}
	}

	/**
	 * <p>
	 * 把字符串转换成为单精度数字
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 单精度数字
	 */
	public static float toFloat(String s) {
		return toCFloat(s).floatValue();
	}

	/**
	 * <p>
	 * 把字符串转换成为单精度数字类
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 单精度数字类
	 */
	public static Float toCFloat(String s) {
		if (s == null) {
			return null;
		} else {
			String str = toToken(s);
			return Float.valueOf(str);
		}
	}

	/**
	 * <p>
	 * 把字符串转换成为长整形数字
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 长整形数字
	 */
	public static long toLong(String s) {
		return toClong(s).longValue();
	}

	/**
	 * <p>
	 * 把字符串转换成为长整形数字类
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 长整形数字类
	 */
	public static Long toClong(String s) {
		if (s == null) {
			return null;
		} else {
			String str = toToken(s);
			return Long.valueOf(str);
		}
	}

	/**
	 * <p>
	 * 把字符串转换成为整形数字
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 整形数字
	 */
	public static int toInt(String s) {
		return toCInt(s).intValue();
	}

	/**
	 * <p>
	 * 把字符串转换成为整形数字类
	 * </p>
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return 整形数字类
	 */
	public static Integer toCInt(String s) {
		if (s == null) {
			return null;
		} else {
			String str = toToken(s);
			return Integer.valueOf(str);
		}
	}

	/**
	 * <p>
	 * 格式化双精度数字为“###0.00”标准货币格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字
	 * @return 返回格式化过的字符串
	 */
	public static String formatCurr(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###0.00");
		strNumber = df.format(d);
		return strNumber;
	}

	/**
	 * <p>
	 * 格式化双精度数字类为“###0.00”标准格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字类
	 * @return 返回格式化过的字符串
	 */
	public static String formatCurr(Double d) {
		if (d == null) {
			return null;
		} else {
			double dd = d.doubleValue();
			return NumberFormatUtil.formatCurr(dd);
		}
	}

	/**
	 * <p>
	 * 格式化双精度数字为“###0.0###”标准格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字
	 * @return 返回格式化过的字符串
	 */
	public static String formatData(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###0.0###");
		strNumber = df.format(d);
		return strNumber;
	}

	/**
	 * <p>
	 * 格式化双精度数字为“###0.00”标准货币格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字
	 * @return 返回格式化过的字符串
	 * @see #format(double)
	 * @see #formatCurr(double)
	 * @see #formatCurr(Double)
	 * @see #formatData(double)
	 * @see #formatPrice(Double)
	 */
	public static String formatPrice(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###0.00");
		strNumber = df.format(d);
		return strNumber;
	}

	/**
	 * <p>
	 * 格式化双精度数字类为“###0.00”标准货币格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字类
	 * @return 返回格式化过的字符串
	 * @see #format(double)
	 * @see #formatCurr(double)
	 * @see #formatCurr(Double)
	 * @see #formatData(double)
	 * @see #formatPrice(double)
	 */
	public static String formatPrice(Double d) {
		if (d == null) {
			return null;
		} else {
			double dd = d.doubleValue();
			return NumberFormatUtil.formatPrice(dd);
		}
	}

	/**
	 * <p>
	 * 格式化双精度数字为“###0.###”标准数字格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字
	 * @return 返回格式化过的字符串
	 */
	public static String formatNum(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###0.###");
		strNumber = df.format(d);
		return strNumber;
	}

	/**
	 * <p>
	 * 格式化双精度数字类为“###0.###”标准数字格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字类
	 * @return 返回格式化过的字符串
	 */
	public static String formatNum(Double d) {
		if (d == null) {
			return null;
		} else {
			double dd = d.doubleValue();
			return NumberFormatUtil.formatNum(dd);
		}
	}

	/**
	 * <p>
	 * 格式化双精度数字为数据库可以接受的标准数字格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字
	 * @param i
	 *            如果为正数，则为小数位数；如果为负数，则舍弃小数部分并向右舍弃相应位数值为0
	 * @return 返回格式化过的字符串
	 */
	public static double formatToDb(double d, int i) {
		String dd = formatNoComma(d, i);
		return Double.parseDouble(dd);

	}

	/**
	 * <p>
	 * 格式化双精度数字为###########0.###################数字格式
	 * </p>
	 * 
	 * @param d
	 *            需要格式化的双精度数字
	 * @return 返回格式化过的字符串
	 */
	public static String formatRate(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###########0.###################");
		strNumber = df.format(d);
		return strNumber;
	}
	
   /**
    * 	编码构造  例：输入 10000044  返回 10000045
    * 				
    * @param str     
    * @return
    */
   public static String covertString(String str){
    	int _len = str.length();
    	int _start = _len - 4;
    	String _str1 = str.substring(0,_start);
    	String _str2 = str.substring(_start);
    	int value = Integer.parseInt(_str2)+1;
    	return _str1+format(value, "0000");
    }

	/**
	 * IP地址转化为标准格式：
	 * 如："59.38.144.0" 转化为"059.038.144.000"
	 * @param ipAddress
	 * @return
	 */
	public static String convertIp(String ipAddress){
		if (ipAddress != null && ipAddress.endsWith("")) {
			String style="000",split = ".";
			String[] ip = StringUtil.split(ipAddress,split);
			if (ip.length == 4){
				return format(Integer.parseInt(ip[0]),style)+split+
				       format(Integer.parseInt(ip[1]),style)+split+
				       format(Integer.parseInt(ip[2]),style)+split+
				       format(Integer.parseInt(ip[3]),style);
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	
   //test
  public static void  main(String[] args) throws Exception{
	  System.out.println(covertString("0"));
   }
}
