package com.dglt.base.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.dglt.comm.base.Globals;

public class ClassUtil {
	public static String getAudience(String grade) {

		String audience = "11";
		try {
			int g = Integer.valueOf(grade);
			switch (g) {
			case 11:
				audience = "11";
				break;
			case 12:
				audience = "12";
				break;
			case 13:
				audience = "13";
				break;
			case 14:
				audience = "14";
				break;
			case 15:
				audience = "15";
				break;
			case 16:
				audience = "16";
				break;
			case 21:
				audience = "21";
				break;
			case 22:
				audience = "22";
				break;
			case 23:
				audience = "23";
				break;
			case 31:
				audience = "31";
				break;
			case 32:
				audience = "32";
				break;
			case 33:
				audience = "33";
				break;
			default:
				break;
			}

			return audience;

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "11";
		}
	}

	public static String getSubject(String subject) {
		String s = "";
		if ("1".equals(subject)) {
			s = "07";
		} else if ("2".equals(subject)) {
			s = "08";
		} else {
			;
		}
		return s;
	}

	public static String getStage(String grade) {
		if (grade.equals("11") || grade.equals("12") || grade.equals("13")
				|| grade.equals("14") || grade.equals("15")
				|| grade.equals("16")) {
			return "1";
		} else if (grade.equals("21") || grade.equals("22")
				|| grade.equals("23")) {
			return "2";
		} else if (grade.equals("31") || grade.equals("32")
				|| grade.equals("33")) {
			return "3";
		}
		return "";
	}

	/**
	 * 比较二个含数字的字符串元素是否相等。如:a="11,22,33",b="11"
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean strcompare(String a, String b) {

		boolean result = true;

		try {
			if (a == null)
				a = "";
			if (b == null)
				b = "";
			if (a.equals(b))
				return true;
			if ((a.equals("") && !b.equals(""))
					|| (!a.equals("") && b.equals("")))
				return false;

			String[] as = a.split(",");
			String[] bs = b.split(",");
			int[] arra;
			int[] arrb;
			List<String> al = new ArrayList<String>();
			List<String> bl = new ArrayList<String>();
			Map<String, String> am = new HashMap<String, String>();
			Map<String, String> bm = new HashMap<String, String>();

			for (int i = 0; i < as.length; i++) {
				if (am.get(as[i]) == null || am.get(as[i]) == "") {
					am.put(as[i], as[i]);
					al.add(as[i]);
				}
			}
			arra = new int[am.size()];
			for (int i = 0; i < al.size(); i++) {
				arra[i] = Integer.valueOf(al.get(i));
			}
			for (int j = 0; j < bs.length; j++) {
				if (bm.get(bs[j]) == null || am.get(bs[j]) == "") {
					bm.put(bs[j], bs[j]);
					bl.add(bs[j]);
				}
			}
			arrb = new int[bm.size()];
			for (int j = 0; j < bl.size(); j++) {
				arrb[j] = Integer.valueOf(bl.get(j));
			}
			arra = Sort(arra);
			arrb = Sort(arrb);
			if (arra.length == arrb.length) {
				for (int i = 0; i < arra.length; i++) {
					if (arra[i] != arrb[i]) {
						result = false;
						break;
					}
				}
			} else {
				return false;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return result;
	}

	private static int[] Sort(int[] t) {
		int it;
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t.length - i - 1; j++) {
				if (t[j] > t[j + 1]) {
					it = t[j];
					t[j] = t[j + 1];
					t[j + 1] = it;
				}
			}
		}
		return t;
	}

	/**
	 * 2014-1-22 zhoujun 将数据库中数据按键值对方式存储到list里并返回
	 * 
	 * @param l
	 *            数据库数据集合list
	 * @param arry
	 *            String数组 键值对key值
	 * @return ArrayList
	 */
	public static ArrayList getReturnList(String sqlNo,List l, String[] arry) {
		ArrayList<HashMap> al = new ArrayList<HashMap>();
		for (int i = 0; i < l.size(); i++) {
			HashMap<String, String> h = new HashMap<String, String>();
			Object[] v = (Object[]) l.get(i);
			
			if(sqlNo.equals("90015"))
			{
				
				for (int j = 0; j < arry.length; j++) {
					
					
					if(arry[j].equals("biztype"))
					{
						
						String biztype=((v[j] == null || "".equals(v[j])) ? "" : v[j]).toString();
						if(!biztype.equals(""))
						{
						if("SDH移机SDH降速增减DID增加IP业务变更数字中继移机数字商易通开机数字商易通停机DIA降速DIA移机IDC移机APN变更VPN变更".contains(biztype))												
						        h.put("bizclassification","业务变更");
						else if("SDH装机SDH升速数字商易通装机VPN装机IDC装机DIA装机DIA升速APN新装".contains(biztype))
							h.put("bizclassification","新装及升速");
						else if("国内跨域专租线本地专租线国际专租线互联网专线数字中继".contains(biztype))
							h.put("bizclassification","业务拆机");
						else
							h.put("bizclassification","其他");
						}
						else
							h.put("bizclassification","其他");

					}
					
					
					
					
					if(arry[j].contains("time"))
					{
						
						h.put(arry[j], ((v[j] == null || "".equals(v[j])) ? "" : " "+v[j])
							.toString()+" ");
					}
					
					
					else
						
					h.put(arry[j], ((v[j] == null || "".equals(v[j])) ? "" : v[j])
							.toString());
				}		
				
			}
			
			else
			{
				for (int j = 0; j < arry.length; j++) {
					
		
					if((arry[j].toString()).contains("time"))
					{
						
						h.put(arry[j], ((v[j] == null || "".equals(v[j])) ? "" : " "+v[j])
							.toString()+" ");
					}
					else
						
					h.put(arry[j], ((v[j] == null || "".equals(v[j])) ? "" : v[j])
							.toString());
				}		
			}
			
			
			al.add(h);
			
		}
		
		//System.out.println("al:="+al);
		
		return al;
	}
	
	public static ArrayList getReturnList(List l, String[] arry) {
		ArrayList<HashMap> al = new ArrayList<HashMap>();
		for (int i = 0; i < l.size(); i++) {
			HashMap<String, String> h = new HashMap<String, String>();
			Object[] v = (Object[]) l.get(i);

				for (int j = 0; j < arry.length; j++) {
					
		
					if((arry[j].toString()).contains("time"))
					{
						
						h.put(arry[j], ((v[j] == null || "".equals(v[j])) ? "" : " "+v[j])
							.toString()+" ");
						System.out.println(arry[j].toString());
					}
					else
						
					h.put(arry[j], ((v[j] == null || "".equals(v[j])) ? "" : v[j])
							.toString());
				}	
				
				al.add(h);
			}
			
			
			

		
		//System.out.println("al:="+al);
		
		return al;
	}
	
	
	
	/**
	 * 2014-1-22 zhoujun 将数据库中数据按键值对方式存储到list里并返回
	 * 
	 * @param l
	 *            数据库数据集合list
	 * @param arry
	 *            String数组 键值对key值
	 * @return ArrayList
	 */
	public static ArrayList getReturnListArea(List l, String[] arry) {
		ArrayList<HashMap> al = new ArrayList<HashMap>();
		for (int i = 0; i < l.size(); i++) {
			HashMap<String, String> h = new HashMap<String, String>();
			Object[] v = (Object[]) l.get(i);
			if (Globals.getMapIdByCode(((v[0] == null || "".equals(v[0])) ? "0"
					: v[0]).toString()) == null
					|| "".equals(Globals.getMapIdByCode(((v[0] == null || ""
							.equals(v[0])) ? "0" : v[0]).toString()))) {
				continue;
			}
			for (int j = 0; j < arry.length; j++) {

				if (j == arry.length - 1) {
					h.put(arry[j], Globals.getMapIdByCode(((v[0] == null || ""
							.equals(v[0])) ? "0" : v[0]).toString()));
				} else {
					h.put(arry[j], ((v[j] == null || "".equals(v[j])) ? "0"
							: v[j]).toString());
				}
			}
			al.add(h);
		}
		return al;
	}

	/**
	 * 2014-1-27 zhoujun 取当前日期前12个月月份,返回int数组，数组元素格式为201307
	 * 
	 * @return int[]
	 */
	public static int[] getMonthId(int ymonth) {
		Calendar ca = Calendar.getInstance();

		int year = 0;// 获取年份
		int month = 0;// 获取月份
		int monthId = 0;
		if (ymonth == 0) {
			year = ca.get(Calendar.YEAR);
			month = ca.get(Calendar.MONTH) + 1;
		} else {
			month = ymonth % 100;
			year = (ymonth - month) / 100;
		}
		int[] arry = new int[12];
		
		for (int i = 0; i < 12; i++) {
			if (month == 0) {
				year--;
				month = 12;
			}
			monthId = year * 100 + month;
			arry[i] = monthId;
			month--;
		}
		return arry;
	}

	/**
	 * 2014-1-27 zhoujun 取当前日期前1个月月份,返回int型变量
	 * 
	 * @return int[]
	 */
	public static int getMonthIdpre() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		month--;
		if (month == 0) {
			year--;
			month = 12;
		}
		int monthId = year * 100 + month;
		// monthId = 201308;
		return monthId;
	}

	public static String getMonthIdStr(int[] arry) {
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < arry.length; i++) {
			Object a = (Integer) arry[i];
			if (i == 0) {
				str.append(a.toString());
			} else {
				str.append("," + a.toString());
			}

		}
		return str.toString();
	}

	// 获取当前日期往前倒推12个月记录的数组[{id:"201210",text:"2012年10月"}]
	public static List<HashMap<String, String>> getDashMonthList() {
		ArrayList<HashMap<String, String>> l = new ArrayList<HashMap<String, String>>();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int monthId = 0;
		for (int i = 0; i < 12; i++) {
			month--;
			if (month == 0) {
				year--;
				month = 12;
			}
			monthId = year * 100 + month;
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("id", monthId + "");
			hm.put("text", (monthId + "").substring(0, 4) + "年"
					+ (monthId + "").substring(4) + "月");
			l.add(hm);
			monthId--;
		}
		return l;
	}
	public static List<HashMap<String, String>> getLastYears(int number) {
		ArrayList<HashMap<String, String>> l = new ArrayList<HashMap<String, String>>();
		Calendar ca = Calendar.getInstance();
		int month = ca.get(Calendar.MONTH);// 获取年份
		for (int i = 0; i < number; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("id", month + "");
			hm.put("text", month+ "月");
			l.add(hm);
			month--;
		}
		return l;
	}
	public static List<HashMap<String, String>> getLastMonths(int number) {
		ArrayList<HashMap<String, String>> l = new ArrayList<HashMap<String, String>>();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		for (int i = 0; i < number; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("id", year + "");
			hm.put("text", year+ "年");
			l.add(hm);
			year--;
		}
		return l;
	}
	//获取当前上一个月
	public static int getLastMonth() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		month--;
		if (month == 0) {
			year--;
			month = 12;
		}
		return month;
	}
	
	
	public static List<Object> jsonStingToList(String jsonString,String [] arr){
		List<Object> list = new ArrayList<Object>();
		String [] tempList = null;
		if(jsonString==null) return null;
		if(0==jsonString.indexOf("[")){
			jsonString = jsonString.substring(1,jsonString.length()-1);
		}
		if(0==jsonString.indexOf("{")){
			tempList = jsonString.split("}");
			String tempString;
			String [] tempStringArr;
			for(int i=0;i<tempList.length;i++){
				tempString = tempList[i].substring(1);
				//tempStringArr = tempString.split(",");
				HashMap<String, String> eo = getmap(tempString,arr);
				list.add(eo);
			}
		}
		
		
		return list;
	}
	public static HashMap<String, String> getmap(String objectString,String [] arr){
		HashMap<String, String> hm = new HashMap<String, String>();
		for(int i=0;i<arr.length;i++){
			hm = getvalue(hm,objectString,arr[i]);
		}
		return hm;
	}
	private static HashMap getvalue(HashMap<String, String> hs, String objectString,String targetString){
		int n = objectString.indexOf("\""+targetString+"\"")+targetString.length();
		int n1 = objectString.indexOf(":",n);
		String f = objectString.substring(n1+1,n1+2);
		if("\"".equals(f) || "\'".equals(f)){
			n1+=2;
			int n2 = objectString.indexOf("\",",n1);
			String s = objectString.substring(n1, n2);
			hs.put(targetString, s);
		}else{
			n1+=1;
			int n2 = objectString.indexOf(",",n1);
			String s;
			if(n2==-1){
				s=objectString.substring(n1);
			}else{
				s = objectString.substring(n1, n2);
			}
			hs.put(targetString, s);
		}
		
		return hs;
	}
	// test
	public static void main(String[] arg) {
		/*List<HashMap<String, String>> cc = getDashMonthList();
		for (HashMap<String, String> i : cc)
			System.out.println("id:"+i.get("id")+" text:"+i.get("text"));*/
	}
}
