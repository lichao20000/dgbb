package com.dglt.comm.util;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class NumberFormatUtil {


	/**
	 * <p>
	 * ��ʽ��˫��������Ϊ��#,##0.00����׼���Ҹ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫��������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String format(double d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(d);
	}

	/**
	 * <p>
	 * ��ʽ������������Ϊ��#,##0.00����׼���Ҹ�ʽ
	 * </p>
	 * 
	 * @param f
	 *            ��Ҫ��ʽ����˫��������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String format(float f) {
		return NumberFormatUtil.format((double) f);
	}

	/**
	 * <p>
	 * ��ʽ������������Ϊ��#,##0����ʽ
	 * </p>
	 * 
	 * @param l
	 *            ��Ҫ��ʽ���ĳ���������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String format(long l) {
		DecimalFormat df = new DecimalFormat("#,##0");
		return df.format(l);
	}

	/**
	 * <p>
	 * ��ʽ����������Ϊ��#,##0����ʽ
	 * </p>
	 * 
	 * @param i
	 *            ��Ҫ��ʽ������������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String format(int i) {
		return format((long) i);
	}

	/**
	 * <p>
	 * ��ʽ����������Ϊ��׼��ʽ
	 * </p>
	 * 
	 * @param i
	 *            ��Ҫ��ʽ������������
	 * @param style
	 *            �����������ָ�ʽ�����styleΪnull��Ϊ���ַ�������Ĭ��Ϊ��0000000000��
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫����������
	 * </p>
	 * 
	 * @param d
	 *            ˫����������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��������������
	 * </p>
	 * 
	 * @param f
	 *            ������������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��������������
	 * </p>
	 * 
	 * @param l
	 *            ������������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ������������
	 * </p>
	 * 
	 * @param i
	 *            ����������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫�������֣�����һ����С��λ��
	 * </p>
	 * 
	 * @param d
	 *            ˫��������
	 * @param decimalDigits
	 *            ���Ϊ��������ΪС��λ�������Ϊ������������С�����ֲ�����������Ӧλ��ֵΪ0
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫�������֣�����һ����С��λ��
	 * </p>
	 * 
	 * @param d
	 *            ˫��������
	 * @param decimalDigits
	 *            ���Ϊ��������ΪС��λ�������Ϊ������������С�����ֲ�����������Ӧλ��ֵΪ0
	 * @return ��ʽ��֮����ַ���
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
	 * ��ʽ�����������֣�����һ����С��λ��
	 * </p>
	 * 
	 * @param f
	 *            ����������
	 * @param decimalDigits
	 *            ���Ϊ��������ΪС��λ�������Ϊ������������С�����ֲ�����������Ӧλ��ֵΪ0
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String format(float f, int decimalDigits) {
		return NumberFormatUtil.format((double) f, decimalDigits);
	}

	/**
	 * <p>
	 * ��ʽ��˫���������࣬����һ����С��λ��
	 * </p>
	 * <p>
	 * ����<br>
	 * NumberFormatUtil.format(1234567890.123456789012345678901d, -5)=1234500000
	 * </p>
	 * 
	 * @param d
	 *            ˫����������
	 * @param decimalDigits
	 *            ���Ϊ��������ΪС��λ�������Ϊ������������С�����ֲ�����������Ӧλ��ֵΪ0
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫���������࣬����һ����С��λ��
	 * </p>
	 * 
	 * @param d
	 *            ˫����������
	 * @param decimalDigits
	 *            ���Ϊ��������ΪС��λ�������Ϊ������������С�����ֲ�����������Ӧλ��ֵΪ0
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ�������������࣬����һ����С��λ��
	 * </p>
	 * 
	 * @param f
	 *            ������������
	 * @param decimalDigits
	 *            ���Ϊ��������ΪС��λ�������Ϊ������������С�����ֲ�����������Ӧλ��ֵΪ0
	 * @return ���ظ�ʽ�������ַ���
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
	 * �������ֵ��ַ���������С�����λ
	 * @param f
	 * @param decimalDigits
	 * @return
	 * @author ¬����
	 * @since 2010-6-12 ����02:15:05
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
	 * ��ȥ�ַ����е����ж��ŷָ����,��
	 * </p>
	 * 
	 * @param s
	 *            ��Ҫ������ַ���
	 * @return �����ж��ŵ��ַ���
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
	 * ���ַ���ת����Ϊ˫��������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ˫��������
	 */
	public static double toDouble(String s) {
		return toCDouble(s).doubleValue();
	}

	/**
	 * <p>
	 * ���ַ���ת����Ϊ˫����������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ˫����������
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
	 * ���ַ���ת����Ϊ����������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ����������
	 */
	public static float toFloat(String s) {
		return toCFloat(s).floatValue();
	}

	/**
	 * <p>
	 * ���ַ���ת����Ϊ������������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ������������
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
	 * ���ַ���ת����Ϊ����������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ����������
	 */
	public static long toLong(String s) {
		return toClong(s).longValue();
	}

	/**
	 * <p>
	 * ���ַ���ת����Ϊ������������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ������������
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
	 * ���ַ���ת����Ϊ��������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ��������
	 */
	public static int toInt(String s) {
		return toCInt(s).intValue();
	}

	/**
	 * <p>
	 * ���ַ���ת����Ϊ����������
	 * </p>
	 * 
	 * @param s
	 *            Ҫת�����ַ���
	 * @return ����������
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
	 * ��ʽ��˫��������Ϊ��###0.00����׼���Ҹ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫��������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String formatCurr(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###0.00");
		strNumber = df.format(d);
		return strNumber;
	}

	/**
	 * <p>
	 * ��ʽ��˫����������Ϊ��###0.00����׼��ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫����������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫��������Ϊ��###0.0###����׼��ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫��������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String formatData(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###0.0###");
		strNumber = df.format(d);
		return strNumber;
	}

	/**
	 * <p>
	 * ��ʽ��˫��������Ϊ��###0.00����׼���Ҹ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫��������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫����������Ϊ��###0.00����׼���Ҹ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫����������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫��������Ϊ��###0.###����׼���ָ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫��������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String formatNum(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###0.###");
		strNumber = df.format(d);
		return strNumber;
	}

	/**
	 * <p>
	 * ��ʽ��˫����������Ϊ��###0.###����׼���ָ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫����������
	 * @return ���ظ�ʽ�������ַ���
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
	 * ��ʽ��˫��������Ϊ���ݿ���Խ��ܵı�׼���ָ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫��������
	 * @param i
	 *            ���Ϊ��������ΪС��λ�������Ϊ������������С�����ֲ�����������Ӧλ��ֵΪ0
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static double formatToDb(double d, int i) {
		String dd = formatNoComma(d, i);
		return Double.parseDouble(dd);

	}

	/**
	 * <p>
	 * ��ʽ��˫��������Ϊ###########0.###################���ָ�ʽ
	 * </p>
	 * 
	 * @param d
	 *            ��Ҫ��ʽ����˫��������
	 * @return ���ظ�ʽ�������ַ���
	 */
	public static String formatRate(double d) {
		String strNumber = null;
		DecimalFormat df = new DecimalFormat("###########0.###################");
		strNumber = df.format(d);
		return strNumber;
	}
	
   /**
    * 	���빹��  �������� 10000044  ���� 10000045
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
	 * IP��ַת��Ϊ��׼��ʽ��
	 * �磺"59.38.144.0" ת��Ϊ"059.038.144.000"
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
