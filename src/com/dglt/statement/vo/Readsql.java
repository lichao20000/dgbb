package com.dglt.statement.vo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
 
/**
 * @author ��ũС��
 * H20121012.java
 * 2012-10-12����11:40:21
 */
public class Readsql {
    /**
     * ���ܣ�Java��ȡtxt�ļ�������
     * ���裺1���Ȼ���ļ����
     * 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
     * 3����ȡ������������Ҫ��ȡ�����ֽ���
     * 4��һ��һ�е������readline()��
     * ��ע����Ҫ���ǵ����쳣���
     * @param filePath
     */
    public static void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//���ǵ������ʽ
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String sql="";
                    String lineTxt = "";
                    
                    ArrayList arr = new ArrayList();
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	sql+=lineTxt;
                    	arr.add(lineTxt);
                    }
                    //System.out.println(sql);
                    
                    String temp="";
                    for(int i=0;i<arr.size();i++)
                    {
                    	//temp="\""+arr.get(i)+"\",";
                    	System.out.println("+"+"\""+arr.get(i)+"\"");

                    }
                    //System.out.print(temp);
                    
                    read.close();
                    
        }else{
            System.out.println("�Ҳ���ָ�����ļ�");
        }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }
     
    }
     
    public static void main(String argv[]){
        String filePath = "D:\\dgcuvm\\dgbb\\src\\com\\dglt\\statement\\vo\\sql90015.sql";
//      "res/";
        readTxtFile(filePath);
    }
    
	//��һ���ַ����еĴ�дתΪСд��Сдת��Ϊ��д��˼·1
	public static String exChange(String str){
		StringBuffer sb = new StringBuffer();
		if(str!=null){
			for(int i=0;i<str.length();i++){
				char c = str.charAt(i);
				if(c!='_')
				{
				if(Character.isUpperCase(c)){
					sb.append(Character.toLowerCase(c));
				}else if(Character.isLowerCase(c)){
					sb.append(Character.toUpperCase(c)); 
				}
				}
				else
					sb.append(c);
			}
		}
		
		return sb.toString();
	}
 
    
	//��һ���ַ����еĴ�дתΪСд��Сдת��Ϊ��д��˼·2
	public static String exChange2(String str){
		for(int i=0;i<str.length();i++){

				str.substring(i, i+1).toLowerCase();
		
		}
		return str;
	}
 
 
}