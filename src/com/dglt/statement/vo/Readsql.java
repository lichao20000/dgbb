package com.dglt.statement.vo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
 
/**
 * @author 码农小江
 * H20121012.java
 * 2012-10-12下午11:40:21
 */
public class Readsql {
    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
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
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }
     
    public static void main(String argv[]){
        String filePath = "D:\\dgcuvm\\dgbb\\src\\com\\dglt\\statement\\vo\\sql90015.sql";
//      "res/";
        readTxtFile(filePath);
    }
    
	//把一个字符串中的大写转为小写，小写转换为大写：思路1
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
 
    
	//把一个字符串中的大写转为小写，小写转换为大写：思路2
	public static String exChange2(String str){
		for(int i=0;i<str.length();i++){

				str.substring(i, i+1).toLowerCase();
		
		}
		return str;
	}
 
 
}