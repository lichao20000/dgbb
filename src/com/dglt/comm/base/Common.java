package com.dglt.comm.base;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class Common {

        //String תΪ Date
        public static Date String2Date(String date){
                if (!StringUtils.isEmpty(date)) {
                        SimpleDateFormat dateTemp = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                                return dateTemp.parse(date);
                        } catch (ParseException e) {
                                e.printStackTrace();
                                return null;
                        }
                }else{
                        return null;
                }
        }

        /*��ʱ��תΪ����*/
        public static long String2long(String date){
                Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long d = 0L;
                try {
                        d = ((Date)format.parseObject(date)).getTime();
                } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("============ʱ��ת�������=============");
                }
                return d;
        }

        //ȥ��","��" "
        public static String replaceSpace(String s){
                if(StringUtils.isEmpty(s)){
                        return null;
                }
                return s.replaceAll(",","").replaceAll(" ", "");
        }
}
