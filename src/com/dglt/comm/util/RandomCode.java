package com.dglt.comm.util;

import java.util.Random;

public class RandomCode {
	static String codeSequence = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static String numSequence = "0123456789";
	
	/**
	 * �����ȡ�����ַ���ϵ��ַ���
	 * @param len
	 * @return
	 * @author ����ǿ
	 * @since 2010-3-4 ����03:36:02
	 */
	public static String get(int len){
		Random random = new Random(); 
		StringBuffer randomCode = new StringBuffer(); 
		for (int i = 0; i < len; i++) {
			int start = random.nextInt(codeSequence.length()); 
		    String strRand=codeSequence.substring(start,start+1); 
			randomCode.append(strRand); 
		}
		return randomCode.toString();
	}
	
	/**
	 * �����ȡ������ϵ��ַ���
	 * @param len
	 * @return
	 * @author ������
	 * @since Sep 1, 2010 1:56:03 PM
	 */
	public static String getNum(int len) {
		Random random = new Random(); 
		StringBuffer randomCode = new StringBuffer(); 
		for (int i = 0; i < len; i++) {
			int start = random.nextInt(numSequence.length()); 
		    String strRand=numSequence.substring(start,start+1); 
			randomCode.append(strRand); 
		}
		return randomCode.toString();
	}
}
