package com.aca.springboot.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName TimeUtil
 * @Author HWG
 * @Time 2019/4/26 15:36
 */

public class TimeUtil {

    public static Date getDateByYMD(String p){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df=(DateFormat)sdf;
        Date parse = null;
        try {
            parse = df.parse(p);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    };

    public static Date getDateByYMDHMS(String p){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df=(DateFormat)sdf;
        Date parse = null;
        try {
            parse = df.parse(p);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    };

    public static String getFormatyMd(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        return format;
    }

    public static String getFormatyMd(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }

    public static String getFormatyMdHms(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String getFormatyMdHms(Date d){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }
    public static String getBillNumber(){
        SimpleDateFormat time=new SimpleDateFormat("yyyMMddHHmmsss");
        String result="B"+time.format(new Date());
        return result;
    }
    //获取文件名，不会重复
    public static String getFileID() {
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");//格式化当前时间
        String strDate = sfDate.format(new Date());//得到17位时间
        String random = getRandom620(3);//为了防止高并发重复,再获取3个随机数
        String produceID=strDate+random;//最后得到20位编号。
        return produceID;
    }
    private static String getRandom620(Integer length) {
        StringBuffer result=new StringBuffer();
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
            result.append(randInt);
        }
        return result.toString();
    }
}
